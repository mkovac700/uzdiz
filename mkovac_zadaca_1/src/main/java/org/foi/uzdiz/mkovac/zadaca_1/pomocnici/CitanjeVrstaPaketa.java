package org.foi.uzdiz.mkovac.zadaca_1.pomocnici;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_1.builder.VrstaPaketa;
import org.foi.uzdiz.mkovac.zadaca_1.builder.VrstaPaketaBuildDirector;
import org.foi.uzdiz.mkovac.zadaca_1.builder.VrstaPaketaBuilder;
import org.foi.uzdiz.mkovac.zadaca_1.builder.VrstaPaketaBuilderImpl;
import org.foi.uzdiz.mkovac.zadaca_1.singleton.GreskeSingleton;
import org.foi.uzdiz.mkovac.zadaca_1.singleton.TvrtkaSingleton;

public class CitanjeVrstaPaketa {
  private GreskeSingleton greske = GreskeSingleton.getInstance();

  public List<VrstaPaketa> ucitajDatoteku(String nazivDatoteke) throws IOException {
    var putanja = Path.of(nazivDatoteke);
    if (!Files.exists(putanja) || Files.isDirectory(putanja) || !Files.isReadable(putanja)) {
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' nije datoteka ili nije moguće otvoriti!");
    }

    List<VrstaPaketa> vrstePaketa = new ArrayList<>();

    var citac = Files.newBufferedReader(putanja, Charset.forName("UTF-8"));

    int rbr = 0;

    while (true) {
      var red = citac.readLine();
      if (red == null)
        break;

      if (++rbr == 1)
        continue;

      var atributi = red.replace(",", ".").split(";");
      if (atributi.length != 10) {
        System.out.println(greske.dodajGresku(red, "Red nema 10 atributa!"));
      } else {
        String oznaka = atributi[0];
        String opis = atributi[1];

        float visina = 0;
        float sirina = 0;
        float duzina = 0;
        float maksimalnaTezina = 0;
        float cijena = 0;
        float cijenaHitno = 0;
        float cijenaP = 0;
        float cijenaT = 0;

        try {
          visina = Float.parseFloat(atributi[2]);
          sirina = Float.parseFloat(atributi[3]);
          duzina = Float.parseFloat(atributi[4]);
          maksimalnaTezina = Float.parseFloat(atributi[5]);
          cijena = Float.parseFloat(atributi[6]);
          cijenaHitno = Float.parseFloat(atributi[7]);
          cijenaP = Float.parseFloat(atributi[8]);
          cijenaT = Float.parseFloat(atributi[9]);
        } catch (NumberFormatException e) {
          System.out.println(greske.dodajGresku(red, e.getMessage()));
          continue;
        }

        if (oznaka.equals("X"))
          maksimalnaTezina = TvrtkaSingleton.getInstance().getMaksTezina();

        VrstaPaketaBuilder builder = new VrstaPaketaBuilderImpl();
        VrstaPaketaBuildDirector vrstaPaketaBuildDirector = new VrstaPaketaBuildDirector(builder);

        var vrstaPaketa = vrstaPaketaBuildDirector.construct(oznaka, opis, visina, sirina, duzina,
            maksimalnaTezina, cijena, cijenaHitno, cijenaP, cijenaT);
        vrstePaketa.add(vrstaPaketa);
      }
    }

    return vrstePaketa;
  }
}
