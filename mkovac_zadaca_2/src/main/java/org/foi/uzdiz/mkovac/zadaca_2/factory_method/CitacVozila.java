package org.foi.uzdiz.mkovac.zadaca_2.factory_method;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Vozilo;
import org.foi.uzdiz.mkovac.zadaca_2.builder.VoziloBuildDirector;
import org.foi.uzdiz.mkovac.zadaca_2.builder.VoziloBuilder;
import org.foi.uzdiz.mkovac.zadaca_2.builder.VoziloBuilderImpl;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.GreskeSingleton;

public class CitacVozila implements CitacDatoteke<Vozilo> {

  private GreskeSingleton greske = GreskeSingleton.getInstance();

  @Override
  public List<Vozilo> citajDatoteku(String nazivDatoteke) throws IOException {

    var putanja = Path.of(nazivDatoteke);
    if (!Files.exists(putanja) || Files.isDirectory(putanja) || !Files.isReadable(putanja)) {
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' nije datoteka ili nije moguće otvoriti!");
    }

    List<Vozilo> vozila = new ArrayList<>();

    var citac = Files.newBufferedReader(putanja, Charset.forName("UTF-8"));

    int rbr = 0;

    while (true) {
      var red = citac.readLine();
      if (red == null)
        break;

      if (++rbr == 1)
        continue;

      var atributi = red.replace(",", ".").split(";");
      if (atributi.length != 8) {
        System.out.println(greske.novaGreska(red, "Red nema 8 atributa!"));
      } else {
        String registracija = null;
        String opis = null;
        float kapacitetTezine = 0;
        float kapacitetProstora = 0;
        int redoslijed = 0;
        float prosjecnaBrzina = 0;
        int[] podrucjaPoRangu = null;
        String status = null;

        registracija = atributi[0];
        opis = atributi[1];
        status = atributi[7];

        try {
          kapacitetTezine = Float.parseFloat(atributi[2]);
          kapacitetProstora = Float.parseFloat(atributi[3]);
          redoslijed = Integer.parseInt(atributi[4]);
          prosjecnaBrzina = Float.parseFloat(atributi[5]);
          podrucjaPoRangu =
              Arrays.stream(atributi[6].split(".")).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException e) {
          System.out.println(greske.novaGreska(red, e.getMessage()));
          continue;
        }

        // TODO if podrucjaPoRangu not in popisPodrucja continue;

        // TODO if status != A, NA, NI
        if (!status.equals("A") && !status.equals("NA") && !status.equals("NI")) {
          System.out.println(status);
          System.out
              .println(greske.novaGreska(red, "Vrijednost atributa 'status' nije A | NI | NA"));
          continue;
        }

        // TODO BUILDER
        VoziloBuilder builder = new VoziloBuilderImpl();
        VoziloBuildDirector voziloBuildDirector = new VoziloBuildDirector(builder);

        var vozilo = voziloBuildDirector.construct(registracija, opis, kapacitetTezine,
            kapacitetProstora, redoslijed, prosjecnaBrzina, podrucjaPoRangu, status);

        vozila.add(vozilo);
      }
    }
    return vozila;
  }
}
