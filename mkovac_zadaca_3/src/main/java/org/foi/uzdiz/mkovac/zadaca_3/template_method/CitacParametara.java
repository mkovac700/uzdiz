package org.foi.uzdiz.mkovac.zadaca_3.template_method;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.Parametar;
import org.foi.uzdiz.mkovac.zadaca_3.singleton.GreskeSingleton;

public class CitacParametara implements CitacDatoteke<Parametar> {

  private GreskeSingleton greske = GreskeSingleton.getInstance();

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

      var atributi = red.split("=");

      if (atributi.length != 2) {
        System.out.println(greske.novaGreska(red, "Red nije u obliku ključ=vrijednost"));
      } else {
        String kljuc = atributi[0].trim();
        String vrijednost = atributi[1].trim();
        parametri.add(new Parametar(kljuc, vrijednost));
      }
    }

    if (parametri.isEmpty())
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' je prazna ili ne sadrži odgovarajuće podatke!");

    return parametri;
  }

}
