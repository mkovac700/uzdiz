package org.foi.uzdiz.mkovac.zadaca_1.pomocnici;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_1.builder.Vozilo;
import org.foi.uzdiz.mkovac.zadaca_1.builder.VoziloBuildDirector;
import org.foi.uzdiz.mkovac.zadaca_1.builder.VoziloBuilder;
import org.foi.uzdiz.mkovac.zadaca_1.builder.VoziloBuilderImpl;
import org.foi.uzdiz.mkovac.zadaca_1.singleton.GreskeSingleton;

public class CitanjeVozila {
  private GreskeSingleton greske = GreskeSingleton.getInstance();

  public List<Vozilo> ucitajDatoteku(String nazivDatoteke) throws IOException {
    var putanja = Path.of(nazivDatoteke);
    if (!Files.exists(putanja) || Files.isDirectory(putanja) || !Files.isReadable(putanja)) {
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' nije datoteka ili nije moguće otvoriti!");
    }

    List<Vozilo> vozila = new ArrayList<>();

    var citac = Files.newBufferedReader(putanja, Charset.forName("UTF-8"));

    // final VoziloBuilder builder = new VoziloBuilderImpl();
    // final VoziloBuildDirector voziloBuildDirector = new VoziloBuildDirector(builder);

    int rbr = 0;

    while (true) {
      var red = citac.readLine();
      if (red == null)
        break;

      if (++rbr == 1)
        continue;

      var atributi = red.replace(",", ".").split(";");
      if (atributi.length != 5) {
        System.out.println(greske.dodajGresku(red, "Red nema 5 atributa!"));
      } else {
        float kapacitetTezine = 0;
        float kapacitetProstora = 0;
        int redoslijed = 0;

        try {
          kapacitetTezine = Float.parseFloat(atributi[2]);
          kapacitetProstora = Float.parseFloat(atributi[3]);
          redoslijed = Integer.parseInt(atributi[4]);
        } catch (NumberFormatException e) {
          System.out.println(greske.dodajGresku(red, e.getMessage()));
          continue;
        }

        VoziloBuilder builder = new VoziloBuilderImpl();
        VoziloBuildDirector voziloBuildDirector = new VoziloBuildDirector(builder);

        var vozilo = voziloBuildDirector.construct(atributi[0], atributi[1], kapacitetTezine,
            kapacitetProstora, redoslijed);

        vozila.add(vozilo);
      }
    }

    return vozila;
  }
}
