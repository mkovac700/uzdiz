package org.foi.uzdiz.mkovac.zadaca_1.pomocnici;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_1.builder.Paket;
import org.foi.uzdiz.mkovac.zadaca_1.builder.PaketBuildDirector;
import org.foi.uzdiz.mkovac.zadaca_1.builder.PaketBuilder;
import org.foi.uzdiz.mkovac.zadaca_1.builder.PaketBuilderImpl;
import org.foi.uzdiz.mkovac.zadaca_1.builder.VrstaPaketa;
import org.foi.uzdiz.mkovac.zadaca_1.singleton.GreskeSingleton;
import org.foi.uzdiz.mkovac.zadaca_1.singleton.TvrtkaSingleton;

public class CitanjePaketa2 {
  private GreskeSingleton greske = GreskeSingleton.getInstance();

  public List<Paket> ucitajDatoteku(String nazivDatoteke) throws IOException {
    var putanja = Path.of(nazivDatoteke);
    if (!Files.exists(putanja) || Files.isDirectory(putanja) || !Files.isReadable(putanja)) {
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' nije datoteka ili nije moguće otvoriti!");
    }

    List<Paket> paketi = new ArrayList<>();

    var citac = Files.newBufferedReader(putanja, Charset.forName("UTF-8"));

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");

    int rbr = 0;

    while (true) {
      var red = citac.readLine();
      if (red == null)
        break;

      if (++rbr == 1)
        continue;

      var atributi = red.replace(",", ".").split(";");
      if (atributi.length != 11) {
        System.out.println(greske.dodajGresku(red, "Red nema 11 atributa!"));
      } else {
        LocalDateTime vrijemePrijema;

        String vrsta = atributi[4];

        VrstaPaketa vrstaPaketa = null;

        for (VrstaPaketa vp : TvrtkaSingleton.getInstance().getVrstePaketa()) {
          if (vp.getOznaka().equals(vrsta))
            vrstaPaketa = vp;
        }

        float visina = 0;
        float sirina = 0;
        float duzina = 0;
        float tezina = 0;
        float iznosPouzeca = 0;

        try {
          vrijemePrijema = LocalDateTime.parse(atributi[1], dtf);
          visina = Float.parseFloat(atributi[5]);
          sirina = Float.parseFloat(atributi[6]);
          duzina = Float.parseFloat(atributi[7]);
          tezina = Float.parseFloat(atributi[8]);
          iznosPouzeca = Float.parseFloat(atributi[10]);
        } catch (NumberFormatException | DateTimeParseException e) {
          System.out.println(greske.dodajGresku(red, e.getMessage()));
          continue;
        }

        PaketBuilder builder = new PaketBuilderImpl();
        PaketBuildDirector paketBuildDirector = new PaketBuildDirector(builder);

        var paket = paketBuildDirector.construct(atributi[0], vrijemePrijema, atributi[2],
            atributi[3], vrstaPaketa, visina, sirina, duzina, tezina, atributi[9], iznosPouzeca);

        paketi.add(paket);
      }
    }

    return paketi;
  }
}
