package org.foi.uzdiz.mkovac.zadaca_2.template_method;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_2.prototype.NetipskiPaket;
import org.foi.uzdiz.mkovac.zadaca_2.prototype.TipskiPaket;
import org.foi.uzdiz.mkovac.zadaca_2.prototype.VrstaPaketa;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.GreskeSingleton;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.TvrtkaSingleton;

public class CitacVrstaPaketa implements CitacDatoteke<VrstaPaketa> {

  private GreskeSingleton greske = GreskeSingleton.getInstance();

  @Override
  public List<VrstaPaketa> citajDatoteku(String nazivDatoteke) throws IOException {
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
        System.out.println(greske.novaGreska(red, "Red nema 10 atributa!"));
      } else {
        Arrays.setAll(atributi, i -> atributi[i].trim());

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
          System.out.println(greske.novaGreska(red, e.getMessage()));
          continue;
        }

        if (vrstePaketa.stream().anyMatch(vrstaPaketa -> oznaka.equals(vrstaPaketa.oznaka))) {
          System.out.println(greske.novaGreska(red, "Duplikat oznake paketa!"));
          continue;
        }

        if (!oznaka.equals("A") && !oznaka.equals("B") && !oznaka.equals("C") && !oznaka.equals("D")
            && !oznaka.equals("E") && !oznaka.equals("X")) {
          System.out.println(
              greske.novaGreska(red, "Vrijednost atributa 'oznaka' nije A | B | C | D | E | X"));
          continue;
        }

        if (visina <= 0.0f || sirina <= 0.0f || duzina <= 0.0f) {
          System.out.println(greske.novaGreska(red,
              "Visina i/ili širina i/ili dužina su manje ili jednake 0,00!"));
          continue;
        }

        if (!oznaka.equals("X") && maksimalnaTezina <= 0.0f) {
          System.out.println(greske.novaGreska(red,
              "Maksimalna težina za tipski paket je manja ili jednaka 0,00!"));
          continue;
        }

        if (cijena <= 0.0f || cijenaHitno <= 0.0f) {
          System.out.println(
              greske.novaGreska(red, "'Cijena' i/ili 'Cijena hitno' je manja ili jednaka 0,00!"));
          continue;
        }

        if (oznaka.equals("X") && (cijenaP <= 0.0f || cijenaT <= 0.0f)) {
          System.out.println(greske.novaGreska(red,
              "'CijenaP' i/ili 'CijenaT' je manja ili jednaka 0,00 za paket vrste 'X'!"));
          continue;
        }

        if (oznaka.equals("X")) {
          maksimalnaTezina = Float.parseFloat(TvrtkaSingleton.getInstance().dajPostavku("mt"));

          vrstePaketa.add(new NetipskiPaket(oznaka, opis, visina, sirina, duzina, maksimalnaTezina,
              cijena, cijenaHitno, cijenaP, cijenaT));
        } else {
          vrstePaketa.add(new TipskiPaket(oznaka, opis, visina, sirina, duzina, maksimalnaTezina,
              cijena, cijenaHitno, cijenaP, cijenaT));
        }
      }
    }

    if (vrstePaketa.isEmpty())
      throw new IOException(
          "Datoteka '" + nazivDatoteke + "' je prazna ili ne sadrži odgovarajuće podatke!");

    return vrstePaketa;
  }
}

