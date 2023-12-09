package org.foi.uzdiz.mkovac.zadaca_2.template_method;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Osoba;
import org.foi.uzdiz.mkovac.zadaca_2.builder.OsobaBuildDirector;
import org.foi.uzdiz.mkovac.zadaca_2.builder.OsobaBuilder;
import org.foi.uzdiz.mkovac.zadaca_2.builder.OsobaBuilderImpl;
import org.foi.uzdiz.mkovac.zadaca_2.composite.MjestoComposite;
import org.foi.uzdiz.mkovac.zadaca_2.composite.UlicaLeaf;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.GreskeSingleton;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.TvrtkaSingleton;

public class CitacOsoba implements CitacDatoteke<Osoba> {

  private GreskeSingleton greske = GreskeSingleton.getInstance();

  @Override
  public List<Osoba> citajDatoteku(String nazivDatoteke) throws IOException {

    var putanja = Path.of(nazivDatoteke);
    if (!Files.exists(putanja) || Files.isDirectory(putanja) || !Files.isReadable(putanja)) {
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' nije datoteka ili nije moguće otvoriti!");
    }

    List<MjestoComposite> mjesta = TvrtkaSingleton.getInstance().getMjesta();

    List<Osoba> osobe = new ArrayList<>();

    var citac = Files.newBufferedReader(putanja, Charset.forName("UTF-8"));

    int rbr = 0;

    while (true) {
      var red = citac.readLine();
      if (red == null)
        break;

      if (++rbr == 1)
        continue;

      var atributi = red.split(";");
      if (atributi.length != 4) {
        System.out.println(greske.novaGreska(red, "Red nema 4 atributa!"));
      } else {
        Arrays.setAll(atributi, i -> atributi[i].trim());

        String osobaId = atributi[0];
        int gradId;
        int ulicaId;
        int kbr;

        try {
          gradId = Integer.parseInt(atributi[1]);
          ulicaId = Integer.parseInt(atributi[2]);
          kbr = Integer.parseInt(atributi[3]);
        } catch (NumberFormatException e) {
          System.out.println(greske.novaGreska(red, e.getMessage()));
          continue;
        }

        if (osobe.stream().anyMatch(o -> o.getOsoba().equals(osobaId))) {
          System.out.println(greske.novaGreska(red, "Osoba s tim imenom već postoji u sustavu!"));
          continue;
        }

        if (kbr < 1) {
          System.out.println(greske.novaGreska(red, "Neispravan kućni broj!"));
          continue;
        }

        MjestoComposite grad;
        UlicaLeaf ulica;

        // ako mjesto s mjestoID postoji u sustavu i ako to mjesto sadrzi ulicu s ulicaID
        if (mjesta.stream().anyMatch(mjesto -> mjesto.getId() == gradId
            && mjesto.dajLokacije().stream().anyMatch(u -> ((UlicaLeaf) u).getId() == ulicaId))) {
          grad = mjesta.stream().filter(mjesto -> mjesto.getId() == gradId).findFirst().get();
          ulica = (UlicaLeaf) grad.dajLokacije().stream()
              .filter(ul -> ((UlicaLeaf) ul).getId() == ulicaId).findFirst().get();
        } else {
          System.out.println(greske.novaGreska(red,
              "U sustavu ne postoji odgovarajući grad ili ulica, ili ulica nije dio grada!"));
          continue;
        }

        // TODO provjera je li kucni broj osobe veci od najveceg kucnog broja ulice - NE TREBA??
        /*
         * if (kbr > ulica.getNajveciKucniBroj()) { System.out.println( greske.novaGreska(red,
         * "Kućni broj osobe veći je od najvećeg kućnog broja ulice!")); continue; }
         */

        // TODO build osobe i add to list

        OsobaBuilder builder = new OsobaBuilderImpl();
        OsobaBuildDirector osobaBuildDirector = new OsobaBuildDirector(builder);

        var osoba = osobaBuildDirector.construct(osobaId, grad, ulica, kbr);

        osobe.add(osoba);

      }
    } // while

    if (osobe.isEmpty())
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' je prazna ili ne sadrži odgovarajuće podatke!");

    return osobe;
  }

}
