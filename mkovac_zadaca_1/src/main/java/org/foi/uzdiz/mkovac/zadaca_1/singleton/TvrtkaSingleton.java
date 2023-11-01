package org.foi.uzdiz.mkovac.zadaca_1.singleton;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_1.builder.Vozilo;
import org.foi.uzdiz.mkovac.zadaca_1.podaci.Paket;
import org.foi.uzdiz.mkovac.zadaca_1.podaci.RegexVrsta;
import org.foi.uzdiz.mkovac.zadaca_1.podaci.Vrsta;
import org.foi.uzdiz.mkovac.zadaca_1.pomocnici.CitanjePaketa;
import org.foi.uzdiz.mkovac.zadaca_1.pomocnici.CitanjeVozila;
import org.foi.uzdiz.mkovac.zadaca_1.pomocnici.CitanjeVrsta;

public class TvrtkaSingleton extends Thread {
  private static volatile TvrtkaSingleton INSTANCE = new TvrtkaSingleton();

  // TODO obavezno dodati listu grešaka
  // VAŽNO: tryparsati podatke jer se i za to ispisuje greska
  // također treba hendlati ako ne postoji zaglavlje (prvi redak)

  private List<Vrsta> vrste;
  // private List<Vozilo> vozila;
  private List<Vozilo> vozila;

  private List<Paket> paketi;

  private String datotekaVrsta = "";
  private String datotekaVozila = "";
  private String datotekaPaketa = "";
  private int maksTezina;
  private int vrijemeIsporuke;
  private LocalDateTime virtualniSat;
  private int mnoziteljSekunde;
  private LocalTime pocetakRada;
  private LocalTime krajRada;

  public int vrijemeIzvrsavanja;

  private TvrtkaSingleton() {}

  public static TvrtkaSingleton getInstance() {
    return INSTANCE;
  }

  public void init(String argumenti) {
    GreskeSingleton greske = GreskeSingleton.getInstance();

    UredDostavaSingleton uredDostava = UredDostavaSingleton.getInstance();

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



  @Override
  public synchronized void start() {
    super.start();
  }

  @Override
  public void run() {
    LocalDateTime virtualniKraj = virtualniSat;
    virtualniKraj = virtualniKraj.plusHours(vrijemeIzvrsavanja);

    System.out.println("virtualni sat: " + virtualniSat.toString());
    System.out.println("virtualni kraj: " + virtualniKraj.toString());

    System.out.println(virtualniSat.toLocalTime().isBefore(krajRada));
    System.out.println(virtualniSat.isBefore(virtualniKraj));

    while (virtualniSat.toLocalTime().isBefore(krajRada) && virtualniSat.isBefore(virtualniKraj)) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      virtualniSat = virtualniSat.plusSeconds(mnoziteljSekunde);

      System.out.println("virtualni sat: " + virtualniSat.toString());
    }


  }

  @Override
  public void interrupt() {
    super.interrupt();
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
