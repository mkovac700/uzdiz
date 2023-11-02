package org.foi.uzdiz.mkovac.zadaca_1.singleton;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_1.builder.Paket;
import org.foi.uzdiz.mkovac.zadaca_1.builder.Vozilo;
import org.foi.uzdiz.mkovac.zadaca_1.builder.VrstaPaketa;
import org.foi.uzdiz.mkovac.zadaca_1.podaci.RegexVrsta;
import org.foi.uzdiz.mkovac.zadaca_1.pomocnici.CitanjePaketa2;
import org.foi.uzdiz.mkovac.zadaca_1.pomocnici.CitanjeVozila;
import org.foi.uzdiz.mkovac.zadaca_1.pomocnici.CitanjeVrstaPaketa;

public class TvrtkaSingleton {
  private static volatile TvrtkaSingleton INSTANCE = new TvrtkaSingleton();

  // TODO obavezno dodati listu grešaka
  // VAŽNO: tryparsati podatke jer se i za to ispisuje greska
  // također treba hendlati ako ne postoji zaglavlje (prvi redak)

  // private List<Vrsta> vrste;
  public List<VrstaPaketa> vrstePaketa;

  public List<Vozilo> vozila;

  // private List<Paket> paketi;

  public List<Paket> paketi;

  private String datotekaVrsta = "";
  private String datotekaVozila = "";
  private String datotekaPaketa = "";
  public int maksTezina;
  public int vrijemeIsporuke;
  public LocalDateTime virtualniSat;
  public int mnoziteljSekunde;
  public LocalTime pocetakRada;
  public LocalTime krajRada;

  public int vrijemeIzvrsavanja;

  public UredDostavaSingleton uredDostava;
  public UredPrijemSingleton uredPrijem;

  private TvrtkaSingleton() {}

  public static TvrtkaSingleton getInstance() {
    return INSTANCE;
  }

  public int getMaksTezina() {
    return maksTezina;
  }

  public List<VrstaPaketa> getVrstePaketa() {
    return vrstePaketa;
  }



  public LocalDateTime getVirtualniSat() {
    return virtualniSat;
  }

  public void init(String argumenti) {
    GreskeSingleton greske = GreskeSingleton.getInstance();

    uredDostava = UredDostavaSingleton.getInstance();
    uredPrijem = UredPrijemSingleton.getInstance();

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

    maksTezina = Integer.parseInt(postavke[4].split(" ")[1]);
    vrijemeIsporuke = Integer.parseInt(postavke[5].split(" ")[1]);

    String datum = postavke[6].split(" ")[1];
    String vrijeme = postavke[6].split(" ")[2];
    String datumVrijeme = String.join(" ", datum, vrijeme);

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
    virtualniSat = LocalDateTime.parse(datumVrijeme, dtf);

    System.out.println("maks tezina: " + maksTezina);
    System.out.println("vrijeme isporuke: " + vrijemeIsporuke);
    System.out.println("virtualni sat: " + virtualniSat.toString());

    mnoziteljSekunde = Integer.parseInt(postavke[10].split(" ")[1]);

    String vrijemePocetak = postavke[11].split(" ")[1];
    String vrijemeKraj = postavke[14].split(" ")[1];
    DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
    pocetakRada = LocalTime.parse(vrijemePocetak, tf);
    krajRada = LocalTime.parse(vrijemeKraj, tf);

    System.out.println("mnozitelj sekunde: " + mnoziteljSekunde);
    System.out.println("pocetak rada: " + pocetakRada.toString());
    System.out.println("kraj rada: " + krajRada.toString());

    uredDostava.setVozniPark(vozila);
  }



  /*
   * private void ucitajVrste() { CitanjeVrsta citacVrsta = new CitanjeVrsta(); try { vrste =
   * citacVrsta.ucitajDatoteku(datotekaVrsta); } catch (IOException e) { // TODO Auto-generated
   * catch block e.printStackTrace(); }
   * 
   * for (Vrsta vrsta : vrste) {
   * 
   * System.out.println(vrsta.toString()); } }
   */

  private void ucitajVrste() {
    CitanjeVrstaPaketa citacVrstaPaketa = new CitanjeVrstaPaketa();
    try {
      vrstePaketa = citacVrstaPaketa.ucitajDatoteku(datotekaVrsta);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    for (VrstaPaketa vrsta : vrstePaketa) {

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

  /*
   * private void ucitajPakete() { CitanjePaketa citacPaketa = new CitanjePaketa(); try { paketi =
   * citacPaketa.ucitajDatoteku(datotekaPaketa); } catch (IOException e) { // TODO Auto-generated
   * catch block e.printStackTrace(); }
   * 
   * for (Paket paket : paketi) {
   * 
   * System.out.println(paket.toString()); } }
   */

  private void ucitajPakete() {
    CitanjePaketa2 citacPaketa = new CitanjePaketa2();
    try {
      paketi = citacPaketa.ucitajDatoteku(datotekaPaketa);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    Collections.sort(paketi, (a, b) -> a.getVrijemePrijema().compareTo(b.getVrijemePrijema()));

    for (Paket paket : paketi) {

      System.out.println(paket.toString());
    }
  }
}
