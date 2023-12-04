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
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Osoba;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Paket;
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

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");

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
          visina = Float.parseFloat(atributi[5]);
          sirina = Float.parseFloat(atributi[6]);
          duzina = Float.parseFloat(atributi[7]);
          tezina = Float.parseFloat(atributi[8]);
          iznosPouzeca = Float.parseFloat(atributi[10]);
        } catch (NumberFormatException | DateTimeParseException e) {
          System.out.println(greske.novaGreska(red, e.getMessage()));
          continue;
        }

        if (paketi.stream().anyMatch(paket -> paket.getOznaka().equals(oznaka))) {
          System.out.println(greske.novaGreska(red, "Duplikat oznake paketa!"));
          continue;
        }

        if (!osobe.stream().anyMatch(osoba -> osoba.getOsoba().equals(pos))) {
          System.out.println(greske.novaGreska(red, "Pošiljatelj ne postoji u sustavu!"));
          continue;
        }

        if (!osobe.stream().anyMatch(osoba -> osoba.getOsoba().equals(prim))) {
          System.out.println(greske.novaGreska(red, "Primatelj ne postoji u sustavu!"));
          continue;
        }

        if (!vrstePaketa.stream().anyMatch(vrstaPaketa -> vrstaPaketa.oznaka.equals(vp))) {
          System.out.println(greske.novaGreska(red, "Nepoznata vrsta paketa!"));
          continue;
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

        // VrstaPaketa vrstaPaketa =

        // TODO build paket
      }
    } // while

    return paketi;
  }

}
