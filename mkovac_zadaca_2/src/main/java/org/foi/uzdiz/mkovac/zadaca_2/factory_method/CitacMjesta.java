package org.foi.uzdiz.mkovac.zadaca_2.factory_method;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_2.builder.MjestoBuildDirector;
import org.foi.uzdiz.mkovac.zadaca_2.builder.MjestoBuilder;
import org.foi.uzdiz.mkovac.zadaca_2.builder.MjestoBuilderImpl;
import org.foi.uzdiz.mkovac.zadaca_2.composite.MjestoComposite;
import org.foi.uzdiz.mkovac.zadaca_2.composite.UlicaLeaf;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.GreskeSingleton;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.TvrtkaSingleton;

public class CitacMjesta implements CitacDatoteke<MjestoComposite> {

  private GreskeSingleton greske = GreskeSingleton.getInstance();

  @Override
  public List<MjestoComposite> citajDatoteku(String nazivDatoteke) throws IOException {

    var putanja = Path.of(nazivDatoteke);
    if (!Files.exists(putanja) || Files.isDirectory(putanja) || !Files.isReadable(putanja)) {
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' nije datoteka ili nije moguće otvoriti!");
    }

    List<MjestoComposite> mjesta = new ArrayList<>();

    List<UlicaLeaf> ulice = TvrtkaSingleton.getInstance().getUlice();

    var citac = Files.newBufferedReader(putanja, Charset.forName("UTF-8"));

    int rbr = 0;

    while (true) {
      var red = citac.readLine();
      if (red == null)
        break;

      if (++rbr == 1)
        continue;

      var atributi = red.split(";");
      if (atributi.length != 3) {
        System.out.println(greske.novaGreska(red, "Red nema 3 atributa!"));
      } else {
        Arrays.setAll(atributi, i -> atributi[i].trim());

        int id;
        String naziv = atributi[1];
        int[] uliceId;

        try {
          id = Integer.parseInt(atributi[0]);
          uliceId = Arrays.stream(atributi[2].split(",")).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException e) {
          System.out.println(greske.novaGreska(red, e.getMessage()));
          continue;
        }

        if (mjesta.stream().anyMatch(mjesto -> mjesto.getId() == id)) {
          System.out.println(greske.novaGreska(red, "Duplikat ID mjesta!"));
          continue;
        }

        // TODO provjera postoje li u sustavu sve ulice koje se pridodaju mjestu

        if (Arrays.stream(uliceId)
            .anyMatch(ulicaId -> !ulice.stream().anyMatch(ulica -> ulica.getId() == ulicaId))) {
          System.out.println(greske.novaGreska(red,
              "[UPOZORENJE] Mjesto u popisu ulica sadrži ID ulice koja ne postoji u sustavu!"));

        }

        MjestoBuilder builder = new MjestoBuilderImpl();
        MjestoBuildDirector mjestoBuildDirector = new MjestoBuildDirector(builder);

        var mjesto = mjestoBuildDirector.construct(id, naziv);

        for (int ulicaId : uliceId) {
          if (ulice.stream().anyMatch(ulica -> ulica.getId() == ulicaId)) {
            mjesto.dodajLokaciju(
                ulice.stream().filter(ulica -> ulica.getId() == ulicaId).findFirst().get());
          }
        }

        mjesta.add(mjesto);

      }
    }

    return mjesta;
  }

}
