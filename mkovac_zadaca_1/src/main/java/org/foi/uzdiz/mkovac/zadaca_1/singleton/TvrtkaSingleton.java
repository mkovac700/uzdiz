package org.foi.uzdiz.mkovac.zadaca_1.singleton;

import java.io.IOException;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_1.podaci.Greska;
import org.foi.uzdiz.mkovac.zadaca_1.podaci.Paket;
import org.foi.uzdiz.mkovac.zadaca_1.podaci.RegexVrsta;
import org.foi.uzdiz.mkovac.zadaca_1.podaci.Vozilo;
import org.foi.uzdiz.mkovac.zadaca_1.podaci.Vrsta;
import org.foi.uzdiz.mkovac.zadaca_1.pomocnici.CitanjePaketa;
import org.foi.uzdiz.mkovac.zadaca_1.pomocnici.CitanjeVozila;
import org.foi.uzdiz.mkovac.zadaca_1.pomocnici.CitanjeVrsta;

public class TvrtkaSingleton {
  private static volatile TvrtkaSingleton INSTANCE = new TvrtkaSingleton();

  // TODO obavezno dodati listu grešaka
  // VAŽNO: tryparsati podatke jer se i za to ispisuje greska
  // također treba hendlati ako ne postoji zaglavlje (prvi redak)

  private List<Greska> greske;

  private String datotekaVrsta = "";
  private String datotekaVozila = "";
  private String datotekaPaketa = "";

  private List<Vrsta> vrste;
  private List<Vozilo> vozila;
  private List<Paket> paketi;

  private TvrtkaSingleton() {}

  public static TvrtkaSingleton getInstance() {
    return INSTANCE;
  }

  public void init(String argumenti) {
    String postavke[] = RegexSingleton.getInstance().razdvojiIzraz(argumenti, RegexVrsta.argumenti);

    for (int i = 0; i < postavke.length; i++) {
      System.out.println(i + ": " + postavke[i]);
    }

    datotekaVrsta = postavke[1].split(" ")[1];
    datotekaVozila = postavke[2].split(" ")[1];
    datotekaPaketa = postavke[3].split(" ")[1];

    System.out.println("ucitane vrste: ");
    ucitajVrste();
    System.out.println("ucitana vozila: ");
    ucitajVozila();
    System.out.println("ucitani paketi: ");
    ucitajPakete();
  }

  private void ucitajVrste() {
    CitanjeVrsta citacVrsta = new CitanjeVrsta();
    try {
      vrste = citacVrsta.ucitajDatoteku(datotekaVrsta);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    for (Vrsta vrsta : vrste) {

      System.out.println(vrsta.toString());
    }
  }

  private void ucitajVozila() {
    CitanjeVozila citacVozila = new CitanjeVozila();
    try {
      vozila = citacVozila.ucitajDatoteku(datotekaVozila);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    for (Vozilo vozilo : vozila) {

      System.out.println(vozilo.toString());
    }
  }

  private void ucitajPakete() {
    CitanjePaketa citacPaketa = new CitanjePaketa();
    try {
      paketi = citacPaketa.ucitajDatoteku(datotekaPaketa);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    for (Paket paket : paketi) {

      System.out.println(paket.toString());
    }
  }
}
