package org.foi.uzdiz.mkovac.zadaca_2.factory_method;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Podrucje;
import org.foi.uzdiz.mkovac.zadaca_2.builder.PodrucjeBuildDirector;
import org.foi.uzdiz.mkovac.zadaca_2.builder.PodrucjeBuilder;
import org.foi.uzdiz.mkovac.zadaca_2.builder.PodrucjeBuilderImpl;
import org.foi.uzdiz.mkovac.zadaca_2.composite.LokacijaComponent;
import org.foi.uzdiz.mkovac.zadaca_2.composite.MjestoComposite;
import org.foi.uzdiz.mkovac.zadaca_2.composite.UlicaLeaf;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.GreskeSingleton;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.TvrtkaSingleton;

public class CitacPodrucja implements CitacDatoteke<Podrucje> {

  private GreskeSingleton greske = GreskeSingleton.getInstance();

  @Override
  public List<Podrucje> citajDatoteku(String nazivDatoteke) throws IOException {

    var putanja = Path.of(nazivDatoteke);
    if (!Files.exists(putanja) || Files.isDirectory(putanja) || !Files.isReadable(putanja)) {
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' nije datoteka ili nije moguće otvoriti!");
    }

    List<MjestoComposite> svaMjestaUSustavu = TvrtkaSingleton.getInstance().getMjesta();

    List<Podrucje> podrucja = new ArrayList<>();

    var citac = Files.newBufferedReader(putanja, Charset.forName("UTF-8"));

    int rbr = 0;

    while (true) {
      var red = citac.readLine();
      if (red == null)
        break;

      if (++rbr == 1)
        continue;

      var atributi = red.split(";");
      if (atributi.length != 2) {
        System.out.println(greske.novaGreska(red, "Red nema 2 atributa!"));
      } else {
        Arrays.setAll(atributi, i -> atributi[i].trim());

        int id;
        String[] paroviGradUlica;

        try {
          id = Integer.parseInt(atributi[0]);
        } catch (NumberFormatException e) {
          System.out.println(greske.novaGreska(red, e.getMessage()));
          continue;
        }

        if (podrucja.stream().anyMatch(podrucje -> podrucje.getId() == id)) {
          System.out.println(greske.novaGreska(red, "Duplikat ID podrucja!"));
          continue;
        }

        paroviGradUlica = atributi[1].replace(" ", "").split(",");

        Map<Integer, MjestoComposite> mjestaUPodrucjuMap = new HashMap<>();

        for (String par : paroviGradUlica) {
          String[] parovi = par.split(":");
          int mjestoID;

          try {
            mjestoID = Integer.parseInt(parovi[0]);
          } catch (NumberFormatException e1) {
            System.out.println(greske.novaGreska(red, e1.getMessage()));
            continue;
          }

          MjestoComposite pronadenoMjesto = null; // mjesto pronadeno u sustavu (za usporedbu)

          if (svaMjestaUSustavu.stream().anyMatch(mjesto -> mjesto.getId() == mjestoID)) {
            pronadenoMjesto = svaMjestaUSustavu.stream()
                .filter(mjesto -> mjesto.getId() == mjestoID).findFirst().get();
          } else {
            System.out.println(greske.novaGreska(red, "[UPOZORENJE] Mjesto ne postoji u sustavu!"));
            continue;
          }

          if (!mjestaUPodrucjuMap.containsKey(mjestoID)) { // ako u mapi nema mjesta s idjem
            // kreiraj novo mjesto na bazi postojeceg i dodaj u mapu

            if (pronadenoMjesto != null)
              mjestaUPodrucjuMap.put(mjestoID,
                  new MjestoComposite(pronadenoMjesto.getId(), pronadenoMjesto.getNaziv()));
          }

          if (parovi[1].equals("*")) {
            // TODO dodaj sve ulice pronadenog mjesta
            for (LokacijaComponent u : pronadenoMjesto.dajLokacije()) {
              UlicaLeaf ulica = (UlicaLeaf) u;

              LokacijaComponent novaUlica = new UlicaLeaf(ulica.getId(), ulica.getNaziv(),
                  ulica.getGpsLat1(), ulica.getGpsLon1(), ulica.getGpsLat2(), ulica.getGpsLon2(),
                  ulica.getNajveciKucniBroj());

              mjestaUPodrucjuMap.get(mjestoID).dodajLokaciju(novaUlica);
            }

          } else {
            // TODO dodaj u mjesto po jednu ulicu
            int ulicaID;
            try {
              ulicaID = Integer.parseInt(parovi[1]);
            } catch (NumberFormatException e) {
              System.out.println(greske.novaGreska(red, e.getMessage()));
              continue;
            }

            UlicaLeaf pronadenaUlica = null;

            if (pronadenoMjesto.dajLokacije().stream()
                .anyMatch(ulica -> ((UlicaLeaf) ulica).getId() == ulicaID)) {
              pronadenaUlica = (UlicaLeaf) pronadenoMjesto.dajLokacije().stream()
                  .filter(ulica -> ((UlicaLeaf) ulica).getId() == ulicaID).findFirst().get();
            }

            if (pronadenaUlica != null)
              mjestaUPodrucjuMap.get(mjestoID)
                  .dodajLokaciju(new UlicaLeaf(pronadenaUlica.getId(), pronadenaUlica.getNaziv(),
                      pronadenaUlica.getGpsLat1(), pronadenaUlica.getGpsLon1(),
                      pronadenaUlica.getGpsLat2(), pronadenaUlica.getGpsLon2(),
                      pronadenaUlica.getNajveciKucniBroj()));
          }


        } // for

        PodrucjeBuilder builder = new PodrucjeBuilderImpl();
        PodrucjeBuildDirector podrucjeBuildDirector = new PodrucjeBuildDirector(builder);

        var podrucje = podrucjeBuildDirector.construct(id);

        for (MjestoComposite mup : mjestaUPodrucjuMap.values()) {
          podrucje.getSvaMjesta().dodajLokaciju(mup);
        }

        podrucja.add(podrucje);
      } // else


    } // while

    return podrucja;
  }

}
