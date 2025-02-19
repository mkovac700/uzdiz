package org.foi.uzdiz.mkovac.zadaca_2.template_method;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Podrucje;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Vozilo;
import org.foi.uzdiz.mkovac.zadaca_2.builder.VoziloBuildDirector;
import org.foi.uzdiz.mkovac.zadaca_2.builder.VoziloBuilder;
import org.foi.uzdiz.mkovac.zadaca_2.builder.VoziloBuilderImpl;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.GreskeSingleton;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.TvrtkaSingleton;
import org.foi.uzdiz.mkovac.zadaca_2.state.AktivnoState;
import org.foi.uzdiz.mkovac.zadaca_2.state.NeaktivnoState;
import org.foi.uzdiz.mkovac.zadaca_2.state.NeispravnoState;
import org.foi.uzdiz.mkovac.zadaca_2.state.StatusVozilaState;

public class CitacVozila implements CitacDatoteke<Vozilo> {

  private GreskeSingleton greske = GreskeSingleton.getInstance();

  @Override
  public List<Vozilo> citajDatoteku(String nazivDatoteke) throws IOException {

    var putanja = Path.of(nazivDatoteke);
    if (!Files.exists(putanja) || Files.isDirectory(putanja) || !Files.isReadable(putanja)) {
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' nije datoteka ili nije moguće otvoriti!");
    }

    List<Podrucje> podrucja = TvrtkaSingleton.getInstance().getPodrucja();

    List<Vozilo> vozila = new ArrayList<>();

    var citac = Files.newBufferedReader(putanja, Charset.forName("UTF-8"));

    int rbr = 0;

    while (true) {
      var red = citac.readLine();
      if (red == null)
        break;

      if (++rbr == 1)
        continue;

      var atributi = red.split(";");
      if (atributi.length != 8) {
        System.out.println(greske.novaGreska(red, "Red nema 8 atributa!"));
      } else {
        Arrays.setAll(atributi, i -> atributi[i].trim());

        String registracija = atributi[0];
        String opis = atributi[1];
        float kapacitetTezine = 0;
        float kapacitetProstora = 0;
        int redoslijed = 0;
        float prosjecnaBrzina = 0;
        int[] podrucjaPoRanguID = null;
        String statusID = atributi[7];

        try {
          kapacitetTezine = Float.parseFloat(atributi[2].replace(",", "."));
          kapacitetProstora = Float.parseFloat(atributi[3].replace(",", "."));
          redoslijed = Integer.parseInt(atributi[4]);
          prosjecnaBrzina = Float.parseFloat(atributi[5].replace(",", "."));
          podrucjaPoRanguID = Arrays.stream(atributi[6].replace(" ", "").split(","))
              .mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException e) {
          System.out.println(greske.novaGreska(red, e.getMessage()));
          continue;
        }

        if (vozila.stream().anyMatch(vozilo -> registracija.equals(vozilo.getRegistracija()))) {
          System.out.println(greske.novaGreska(red, "Duplikat registracije vozila!"));
          continue;
        }

        List<Podrucje> podrucjaPoRangu = new ArrayList<>();

        for (int id : podrucjaPoRanguID) {
          if (podrucja.stream().anyMatch(p -> p.getId() == id)) {
            Podrucje podrucje = podrucja.stream().filter(p -> p.getId() == id).findFirst().get();
            podrucjaPoRangu.add(podrucje);
          } else {
            System.out.println(greske.novaGreska(red,
                "[UPOZORENJE] Vozilo u popisu područja sadrži ID područja koje ne postoji u sustavu!"));
          }
        }

        if (!statusID.equals("A") && !statusID.equals("NA") && !statusID.equals("NI")) {
          System.out
              .println(greske.novaGreska(red, "Vrijednost atributa 'status' nije A | NI | NA"));
          continue;
        }

        StatusVozilaState status = null;

        if (statusID.equals("A"))
          status = new AktivnoState();
        if (statusID.equals("NA"))
          status = new NeaktivnoState();
        if (statusID.equals("NI"))
          status = new NeispravnoState();

        VoziloBuilder builder = new VoziloBuilderImpl();
        VoziloBuildDirector voziloBuildDirector = new VoziloBuildDirector(builder);

        var vozilo = voziloBuildDirector.construct(registracija, opis, kapacitetTezine,
            kapacitetProstora, redoslijed, prosjecnaBrzina, podrucjaPoRangu, status);

        vozila.add(vozilo);
      }
    }

    if (vozila.isEmpty())
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' je prazna ili ne sadrži odgovarajuće podatke!");

    return vozila;
  }
}
