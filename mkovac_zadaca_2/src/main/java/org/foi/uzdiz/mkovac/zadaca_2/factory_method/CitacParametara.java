package org.foi.uzdiz.mkovac.zadaca_2.factory_method;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_2.podaci.Parametar;

public class CitacParametara implements CitacDatoteke<Parametar> {

  @Override
  public List<Parametar> citajDatoteku(String nazivDatoteke) throws IOException {

    var putanja = Path.of(nazivDatoteke);
    if (!Files.exists(putanja) || Files.isDirectory(putanja) || !Files.isReadable(putanja)) {
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' nije datoteka ili nije moguće otvoriti!");
    }

    List<Parametar> parametri = new ArrayList<>();

    var citac = Files.newBufferedReader(putanja, Charset.forName("UTF-8"));

    while (true) {
      var red = citac.readLine();
      if (red == null)
        break;

      if (red.contains("=")) {
        var atributi = red.split("=");
        String kljuc = atributi[0].trim();
        String vrijednost = atributi[1].trim();
        parametri.add(new Parametar(kljuc, vrijednost));
      } else {
        // TODO ispis greske da nema znaka =
      }
    }

    return null;
  }

}
