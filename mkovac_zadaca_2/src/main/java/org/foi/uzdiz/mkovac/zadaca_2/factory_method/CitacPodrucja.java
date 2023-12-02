package org.foi.uzdiz.mkovac.zadaca_2.factory_method;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Podrucje;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.GreskeSingleton;

public class CitacPodrucja implements CitacDatoteke<Podrucje> {

  private GreskeSingleton greske = GreskeSingleton.getInstance();

  @Override
  public List<Podrucje> citajDatoteku(String nazivDatoteke) throws IOException {

    var putanja = Path.of(nazivDatoteke);
    if (!Files.exists(putanja) || Files.isDirectory(putanja) || !Files.isReadable(putanja)) {
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' nije datoteka ili nije moguće otvoriti!");
    }

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

      }
    } // while

    return null;
  }

}
