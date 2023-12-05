package org.foi.uzdiz.mkovac.zadaca_2.factory_method;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Osoba;
import org.foi.uzdiz.mkovac.zadaca_2.builder.OsobaBuildDirector;
import org.foi.uzdiz.mkovac.zadaca_2.builder.OsobaBuilder;
import org.foi.uzdiz.mkovac.zadaca_2.builder.OsobaBuilderImpl;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Paket;
import org.foi.uzdiz.mkovac.zadaca_2.builder.PaketBuildDirector;
import org.foi.uzdiz.mkovac.zadaca_2.builder.PaketBuilder;
import org.foi.uzdiz.mkovac.zadaca_2.builder.PaketBuilderImpl;
import org.foi.uzdiz.mkovac.zadaca_2.prototype.NetipskiPaket;
import org.foi.uzdiz.mkovac.zadaca_2.prototype.TipskiPaket;
import org.foi.uzdiz.mkovac.zadaca_2.prototype.VrstaPaketa;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.GreskeSingleton;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.TvrtkaSingleton;

public class CitacPaketa implements CitacDatoteke<Paket> {

  private GreskeSingleton greske = GreskeSingleton.getInstance();

  @Override
  public List<Paket> citajDatoteku(String nazivDatoteke) throws IOException {

    var putanja = Path.of(nazivDatoteke);
    if (!Files.exists(putanja) || Files.isDirectory(putanja) || !Files.isReadable(putanja)) {
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' nije datoteka ili nije moguće otvoriti!");
    }

    List<Osoba> osobe = TvrtkaSingleton.getInstance().getOsobe();

    List<VrstaPaketa> vrstePaketa = TvrtkaSingleton.getInstance().getVrstePaketa();

    List<Paket> paketi = new ArrayList<>();

    DateTimeFormatter dtf = TvrtkaSingleton.getInstance().getDtf();

    var citac = Files.newBufferedReader(putanja, Charset.forName("UTF-8"));

    int rbr = 0;

    while (true) {
      var red = citac.readLine();
      if (red == null)
        break;

      if (++rbr == 1)
        continue;

      var atributi = red.split(";");
      if (atributi.length != 11) {
        System.out.println(greske.novaGreska(red, "Red nema 11 atributa!"));
      } else {
        Arrays.setAll(atributi, i -> atributi[i].trim());

        String oznaka = atributi[0];
        LocalDateTime vrijemePrijema;
        String pos = atributi[2];
        String prim = atributi[3];
        String vp = atributi[4];
        float visina;
        float sirina;
        float duzina;
        float tezina;
        String uslugaDostave = atributi[9];
        float iznosPouzeca;

        try {
          vrijemePrijema = LocalDateTime.parse(atributi[1], dtf);
          visina = Float.parseFloat(atributi[5].replace(",", "."));
          sirina = Float.parseFloat(atributi[6].replace(",", "."));
          duzina = Float.parseFloat(atributi[7].replace(",", "."));
          tezina = Float.parseFloat(atributi[8].replace(",", "."));
          iznosPouzeca = Float.parseFloat(atributi[10].replace(",", "."));
        } catch (NumberFormatException | DateTimeParseException e) {
          System.out.println(greske.novaGreska(red, e.getMessage()));
          continue;
        }

        VrstaPaketa vrstaPaketa;
        Osoba posiljatelj;
        Osoba primatelj;

        if (paketi.stream().anyMatch(paket -> paket.getOznaka().equals(oznaka))) {
          System.out.println(greske.novaGreska(red, "Duplikat oznake paketa!"));
          continue;
        }

        if (!vrstePaketa.stream().anyMatch(vp2 -> vp2.oznaka.equals(vp))) {
          System.out.println(greske.novaGreska(red, "Nepoznata vrsta paketa!"));
          continue;
        } else {
          if (vp.equals("X")) {
            vrstaPaketa = ((NetipskiPaket) vrstePaketa.stream().filter(vp2 -> vp2.oznaka.equals(vp))
                .findFirst().get()).clone();
          } else {
            vrstaPaketa = ((TipskiPaket) vrstePaketa.stream().filter(vp2 -> vp2.oznaka.equals(vp))
                .findFirst().get()).clone();
          }
        }

        // TODO ako posiljatelj ne postoji, nije neki problem pa se posalje upozorenje i kreira novi
        // sa null vrijednostima!
        if (!osobe.stream().anyMatch(osoba -> osoba.getOsoba().equals(pos))) {
          System.out.println(greske.novaGreska(red,
              "[UPOZORENJE] Pošiljatelj ne postoji u sustavu! -> Kreiram novi na temelju podatka iz paketa..."));
          OsobaBuilder osobaBuilder = new OsobaBuilderImpl();
          OsobaBuildDirector osobaBuildDirector = new OsobaBuildDirector(osobaBuilder);
          Osoba o = osobaBuildDirector.construct(pos, null, null, 0);
          osobe.add(o);
          posiljatelj = o;
          // continue;
        } else {
          posiljatelj =
              osobe.stream().filter(osoba -> osoba.getOsoba().equals(pos)).findFirst().get();
        }

        if (!osobe.stream().anyMatch(osoba -> osoba.getOsoba().equals(prim))) {
          System.out.println(greske.novaGreska(red, "Primatelj ne postoji u sustavu!"));
          continue;
        } else {
          primatelj =
              osobe.stream().filter(osoba -> osoba.getOsoba().equals(prim)).findFirst().get();
        }

        if (visina < 0.0f || sirina < 0.0f || duzina < 0.0f || tezina < 0.0f
            || iznosPouzeca < 0.0f) {
          System.out.println(greske.novaGreska(red,
              "Visina i/ili širina i/ili dužina i/ili težina i/ili iznos pouzeća su manji od 0,00!"));
          continue;
        }

        if (!uslugaDostave.equals("S") && !uslugaDostave.equals("H") && !uslugaDostave.equals("P")
            && !uslugaDostave.equals("R")) {
          System.out.println(
              greske.novaGreska(red, "Vrijednost atributa 'Usluga dostave' nije S | H | P | R"));
          continue;
        }

        if (!uslugaDostave.equals("P") && iznosPouzeca != 0.0f) {
          System.out.println(greske.novaGreska(red,
              "Paket s uslugom dostave koja nije 'P' (plaćanje pouzećem) ima pridružen iznos pouzeća!"));
          continue;
        }

        if (uslugaDostave.equals("P") && iznosPouzeca == 0.0f) {
          System.out.println(greske.novaGreska(red,
              "Paket s uslugom dostave 'P' (plaćanje pouzećem) ima iznos pouzeća 0,00!"));
          continue;
        }

        if (!vp.equals("X") && (visina != 0.0f || sirina != 0.0f || duzina != 0.0f)) {
          System.out.println(greske.novaGreska(red,
              "Visina i/ili širina i/ili dužina i/ili težina imaju vrijednost različitu od 0,00 za tipski paket!"));
          continue;
        }

        // ako je nestandardni i ako visina, sirina, duzina ili tezina premasuju max
        if (vrstaPaketa.oznaka.equals("X")
            && (visina > vrstaPaketa.visina || sirina > vrstaPaketa.sirina
                || duzina > vrstaPaketa.duzina || tezina > vrstaPaketa.maksimalnaTezina)) {
          System.out.println(greske.novaGreska(red,
              "Visina i/ili širina i/ili dužina i/ili težina paketa je veća od maksimalno dozvoljenih vrijednosti za vrstu paketa 'X'!"));
          continue;
        }

        // ako je standardni provjerit samo tezinu jer ostalo gleda iz vrste paketa
        if (!vrstaPaketa.oznaka.equals("X") && tezina > vrstaPaketa.maksimalnaTezina) {
          System.out.println(greske.novaGreska(red,
              "Težina paketa je veća od maksimalno dozvoljenih vrijednosti za vrstu paketa '"
                  + vrstaPaketa.oznaka + "'!"));
          continue;
        }

        // TODO build paket

        PaketBuilder builder = new PaketBuilderImpl();
        PaketBuildDirector paketBuildDirector = new PaketBuildDirector(builder);

        var paket = paketBuildDirector.construct(oznaka, vrijemePrijema, posiljatelj, primatelj,
            vrstaPaketa, visina, sirina, duzina, tezina, uslugaDostave, iznosPouzeca);

        // TODO pretplati posiljatelja i primatelja na info o paketu
        paket.addObserver(posiljatelj);
        paket.addObserver(primatelj);

        paketi.add(paket);
      }
    } // while

    if (paketi.isEmpty())
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' je prazna ili ne sadrži odgovarajuće podatke!");

    Collections.sort(paketi, (a, b) -> a.getVrijemePrijema().compareTo(b.getVrijemePrijema()));

    return paketi;
  }

}
