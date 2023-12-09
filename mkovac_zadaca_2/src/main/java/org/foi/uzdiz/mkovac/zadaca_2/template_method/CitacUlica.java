package org.foi.uzdiz.mkovac.zadaca_2.template_method;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_2.builder.UlicaBuildDirector;
import org.foi.uzdiz.mkovac.zadaca_2.builder.UlicaBuilder;
import org.foi.uzdiz.mkovac.zadaca_2.builder.UlicaBuilderImpl;
import org.foi.uzdiz.mkovac.zadaca_2.composite.UlicaLeaf;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.GreskeSingleton;

public class CitacUlica implements CitacDatoteke<UlicaLeaf> {
  private GreskeSingleton greske = GreskeSingleton.getInstance();

  @Override
  public List<UlicaLeaf> citajDatoteku(String nazivDatoteke) throws IOException {

    var putanja = Path.of(nazivDatoteke);
    if (!Files.exists(putanja) || Files.isDirectory(putanja) || !Files.isReadable(putanja)) {
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' nije datoteka ili nije moguće otvoriti!");
    }

    List<UlicaLeaf> ulice = new ArrayList<>();

    var citac = Files.newBufferedReader(putanja, Charset.forName("UTF-8"));

    int rbr = 0;

    while (true) {
      var red = citac.readLine();
      if (red == null)
        break;

      if (++rbr == 1)
        continue;

      var atributi = red.split(";");
      if (atributi.length != 7) {
        System.out.println(greske.novaGreska(red, "Red nema 7 atributa!"));
      } else {
        Arrays.setAll(atributi, i -> atributi[i].trim());

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
          gpsLat1 = Float.parseFloat(atributi[2].replace(",", "."));
          gpsLon1 = Float.parseFloat(atributi[3].replace(",", "."));
          gpsLat2 = Float.parseFloat(atributi[4].replace(",", "."));
          gpsLon2 = Float.parseFloat(atributi[5].replace(",", "."));
          najveciKucniBroj = Integer.parseInt(atributi[6]);

        } catch (NumberFormatException e) {
          System.out.println(greske.novaGreska(red, e.getMessage()));
          continue;
        }

        if (ulice.stream().anyMatch(ulica -> ulica.getId() == id)) {
          System.out.println(greske.novaGreska(red, "Duplikat ID ulice!"));
          continue;
        }

        if (gpsLat1 > 90.0f || gpsLat1 < -90.0f || gpsLat2 > 90.0f || gpsLat2 < -90.0f) {
          System.out.println(greske.novaGreska(red, "Geografska širina nije u rasponu -90 do 90!"));
          continue;
        }

        if (gpsLon1 > 180.0f || gpsLon1 < -180.0f || gpsLon2 > 180.0f || gpsLon2 < -180.0f) {
          System.out
              .println(greske.novaGreska(red, "Geografska dužina nije u rasponu -180 do 180!"));
          continue;
        }

        UlicaBuilder builder = new UlicaBuilderImpl();
        UlicaBuildDirector ulicaBuildDirector = new UlicaBuildDirector(builder);

        var ulica = ulicaBuildDirector.construct(id, naziv, gpsLat1, gpsLon1, gpsLat2, gpsLon2,
            najveciKucniBroj);

        ulice.add(ulica);
      }
    }

    if (ulice.isEmpty())
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' je prazna ili ne sadrži odgovarajuće podatke!");

    return ulice;
  }
}
