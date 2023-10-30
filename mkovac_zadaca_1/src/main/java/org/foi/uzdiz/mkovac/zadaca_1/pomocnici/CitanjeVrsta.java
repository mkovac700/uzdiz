package org.foi.uzdiz.mkovac.zadaca_1.pomocnici;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_1.podaci.Vrsta;

public class CitanjeVrsta {
  public List<Vrsta> ucitajDatoteku(String nazivDatoteke) throws IOException {
    var putanja = Path.of(nazivDatoteke);
    if (!Files.exists(putanja) || Files.isDirectory(putanja) || !Files.isReadable(putanja)) {
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' nije datoteka ili nije moguće otvoriti!");
    }

    List<Vrsta> vrste = new ArrayList<>();

    var citac = Files.newBufferedReader(putanja, Charset.forName("UTF-8"));

    while (true) {
      var red = citac.readLine();
      if (red == null)
        break;

      var atributi = red.split(";");
      if (atributi.length != 10) {
        // TODO singleton za greške
        System.out.println("citanje vrsta: red nema 10 atributa");
      } else {
        var vrsta = new Vrsta(atributi[0], atributi[1], atributi[2], atributi[3], atributi[4],
            atributi[5], atributi[6], atributi[7], atributi[8], atributi[9]);
        vrste.add(vrsta);
      }
    }

    return vrste;
  }
}
