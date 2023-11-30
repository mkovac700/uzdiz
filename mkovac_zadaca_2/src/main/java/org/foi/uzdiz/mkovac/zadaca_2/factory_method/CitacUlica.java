package org.foi.uzdiz.mkovac.zadaca_2.factory_method;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Ulica;
import org.foi.uzdiz.mkovac.zadaca_2.builder.UlicaBuildDirector;
import org.foi.uzdiz.mkovac.zadaca_2.builder.UlicaBuilder;
import org.foi.uzdiz.mkovac.zadaca_2.builder.UlicaBuilderImpl;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.GreskeSingleton;

public class CitacUlica implements CitacDatoteke<Ulica> {
  private GreskeSingleton greske = GreskeSingleton.getInstance();

  @Override
  public List<Ulica> citajDatoteku(String nazivDatoteke) throws IOException {

    var putanja = Path.of(nazivDatoteke);
    if (!Files.exists(putanja) || Files.isDirectory(putanja) || !Files.isReadable(putanja)) {
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' nije datoteka ili nije moguće otvoriti!");
    }

    List<Ulica> ulice = new ArrayList<>();

    var citac = Files.newBufferedReader(putanja, Charset.forName("UTF-8"));

    int rbr = 0;

    while (true) {
      var red = citac.readLine();
      if (red == null)
        break;

      if (++rbr == 1)
        continue;

      var atributi = red.replace(",", ".").split(";");
      if (atributi.length != 7) {
        System.out.println(greske.novaGreska(red, "Red nema 7 atributa!"));
      } else {
        int id;
        String naziv;
        float gpsLat1;
        float gpsLon1;
        float gpsLat2;
        float gpsLon2;
        int najveciKucniBroj;

        naziv = atributi[1];

        try {
          id = Integer.parseInt(atributi[0]);
          gpsLat1 = Float.parseFloat(atributi[2]);
          gpsLon1 = Float.parseFloat(atributi[3]);
          gpsLat2 = Float.parseFloat(atributi[4]);
          gpsLon2 = Float.parseFloat(atributi[5]);
          najveciKucniBroj = Integer.parseInt(atributi[6]);

        } catch (NumberFormatException e) {
          System.out.println(greske.novaGreska(red, e.getMessage()));
          continue;
        }

        // TODO BUILDER
        UlicaBuilder builder = new UlicaBuilderImpl();
        UlicaBuildDirector ulicaBuildDirector = new UlicaBuildDirector(builder);

        var ulica = ulicaBuildDirector.construct(id, naziv, gpsLat1, gpsLon1, gpsLat2, gpsLon2,
            najveciKucniBroj);

        ulice.add(ulica);
      }
    }

    return ulice;
  }
}
