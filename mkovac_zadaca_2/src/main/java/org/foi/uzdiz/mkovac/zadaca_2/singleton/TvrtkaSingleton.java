package org.foi.uzdiz.mkovac.zadaca_2.singleton;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Osoba;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Podrucje;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Vozilo;
import org.foi.uzdiz.mkovac.zadaca_2.composite.LokacijaComponent;
import org.foi.uzdiz.mkovac.zadaca_2.composite.MjestoComposite;
import org.foi.uzdiz.mkovac.zadaca_2.composite.UlicaLeaf;
import org.foi.uzdiz.mkovac.zadaca_2.factory_method.CitacDatoteke;
import org.foi.uzdiz.mkovac.zadaca_2.factory_method.CitacMjesta;
import org.foi.uzdiz.mkovac.zadaca_2.factory_method.CitacOsoba;
import org.foi.uzdiz.mkovac.zadaca_2.factory_method.CitacParametara;
import org.foi.uzdiz.mkovac.zadaca_2.factory_method.CitacPodrucja;
import org.foi.uzdiz.mkovac.zadaca_2.factory_method.CitacUlica;
import org.foi.uzdiz.mkovac.zadaca_2.factory_method.CitacVozila;
import org.foi.uzdiz.mkovac.zadaca_2.factory_method.CitacVrstaPaketa;
import org.foi.uzdiz.mkovac.zadaca_2.iznimke.NeispravniParametri;
import org.foi.uzdiz.mkovac.zadaca_2.podaci.Parametar;
import org.foi.uzdiz.mkovac.zadaca_2.prototype.VrstaPaketa;

public class TvrtkaSingleton {
  private static volatile TvrtkaSingleton INSTANCE = new TvrtkaSingleton();

  private TvrtkaSingleton() {
    postavke = new Properties();
  }

  public static TvrtkaSingleton getInstance() {
    return INSTANCE;
  }

  private Properties postavke;

  public LocalDateTime virtualniSat;
  public int mnoziteljSekunde;
  public LocalTime pocetakRada;
  public LocalTime krajRada;

  private List<Parametar> parametri;
  private List<VrstaPaketa> vrstePaketa;
  private List<UlicaLeaf> ulice;
  private List<MjestoComposite> mjesta;
  private List<Podrucje> podrucja;
  private List<Vozilo> vozila;
  private List<Osoba> osobe;

  public String getVirtualniSatFormatirano() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
    String virtualniSatFormatirano = virtualniSat.format(dtf);
    return virtualniSatFormatirano;
  }

  public void init(String argument) throws IOException, NeispravniParametri {
    // TODO POPRAVIT REDOSLIJED

    this.ucitajParametre(argument);
    this.ucitajVrstePaketa(postavke.getProperty("vp"));
    this.ucitajUlice(postavke.getProperty("pu"));
    this.ucitajMjesta(postavke.getProperty("pm"));
    this.ucitajPodrucja(postavke.getProperty("pmu"));
    this.ucitajVozila(postavke.getProperty("pv"));
    this.ucitajOsobe(postavke.getProperty("po"));

    // TODO izdvoji postavke za tvrtku:
    this.izdvojiPostavke();
  }

  private void izdvojiPostavke() {
    String datumVrijeme = postavke.getProperty("vs");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
    virtualniSat = LocalDateTime.parse(datumVrijeme, dtf);

    mnoziteljSekunde = Integer.parseInt(postavke.getProperty("ms"));

    String vrijemePocetak = postavke.getProperty("pr");
    String vrijemeKraj = postavke.getProperty("kr");
    DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
    pocetakRada = LocalTime.parse(vrijemePocetak, tf);
    krajRada = LocalTime.parse(vrijemeKraj, tf);
  }

  private void ucitajParametre(String datoteka) throws IOException, NeispravniParametri {
    CitacDatoteke<Parametar> citac = new CitacParametara();
    parametri = citac.citajDatoteku(datoteka);

    for (Parametar parametar : parametri) {
      if (!postavke.containsKey(parametar.getKljuc()))
        postavke.put(parametar.getKljuc(), parametar.getVrijednost());
      else
        throw new NeispravniParametri("Datoteka parametara sadrži duplikat ključa!");

      // TODO remove
      System.out.println(
          "k: " + parametar.getKljuc() + " v: " + postavke.getProperty(parametar.getKljuc()));
    }

    if (!ParametriProvjeraSingleton.getInstance().provjeriParametre(postavke))
      throw new NeispravniParametri(
          "Datoteka parametara ne sadrži sve parametre ili parametri nisu ispravni!");
    else {
      // TODO remove
      System.out.println("Parametri OK");
    }
  }

  private void ucitajVrstePaketa(String datoteka) throws IOException {
    CitacDatoteke<VrstaPaketa> citac = new CitacVrstaPaketa();
    vrstePaketa = citac.citajDatoteku(datoteka);

    // TODO remove
    System.out.println("Ucitane vrste paketa: ");

    // TODO remove
    for (VrstaPaketa vrstaPaketa : vrstePaketa) {
      System.out.println(vrstaPaketa.oznaka);
    }
  }

  private void ucitajVozila(String datoteka) throws IOException {
    CitacDatoteke<Vozilo> citac = new CitacVozila();
    vozila = citac.citajDatoteku(datoteka);

    // TODO remove
    System.out.println("Ucitana vozila: ");

    // TODO remove
    for (Vozilo vozilo : vozila) {
      System.out.println(vozilo);
    }
  }

  private void ucitajUlice(String datoteka) throws IOException {
    CitacDatoteke<UlicaLeaf> citac = new CitacUlica();
    ulice = citac.citajDatoteku(datoteka);

    // TODO remove
    System.out.println("Ucitane ulice: ");

    // TODO remove
    for (UlicaLeaf ulica : ulice) {
      System.out.println(ulica.prikaziDetalje());
    }
  }

  private void ucitajMjesta(String datoteka) throws IOException {
    CitacDatoteke<MjestoComposite> citac = new CitacMjesta();
    mjesta = citac.citajDatoteku(datoteka);

    // TODO remove
    System.out.println("Ucitana mjesta: ");

    // TODO remove
    for (MjestoComposite mjesto : mjesta) {
      System.out.println(mjesto.prikaziDetalje());
    }
  }

  private void ucitajPodrucja(String datoteka) throws IOException {
    CitacDatoteke<Podrucje> citac = new CitacPodrucja();
    podrucja = citac.citajDatoteku(datoteka);

    // TODO remove
    System.out.println("Ucitana podrucja: ");

    // TODO remove
    for (Podrucje podrucje : podrucja) {
      System.out.println("Područje " + podrucje.getId());
      for (LokacijaComponent mjesto : podrucje.getSvaMjesta().dajLokacije()) {
        System.out.println("    " + ((MjestoComposite) mjesto).getId() + ": "
            + ((MjestoComposite) mjesto).getNaziv());
        for (LokacijaComponent ulica : mjesto.dajLokacije()) {
          System.out.println(
              "        " + ((UlicaLeaf) ulica).getId() + ": " + ((UlicaLeaf) ulica).getNaziv());
        }
      }
    }
  }

  private void ucitajOsobe(String datoteka) throws IOException {
    CitacDatoteke<Osoba> citac = new CitacOsoba();
    osobe = citac.citajDatoteku(datoteka);

    // TODO remove
    System.out.println("Ucitane osobe: ");

    // TODO remove
    for (Osoba osoba : osobe) {
      System.out.println(osoba);
    }

  }

  public String dajPostavku(String kljuc) {
    return postavke.getProperty(kljuc);
  }

  public List<UlicaLeaf> getUlice() {
    return ulice;
  }

  public List<MjestoComposite> getMjesta() {
    return mjesta;
  }

  public List<Podrucje> getPodrucja() {
    return podrucja;
  }

  public void ispisPodrucja() {
    for (Podrucje podrucje : podrucja) {
      System.out.println("Područje " + podrucje.getId());
      for (LokacijaComponent mjesto : podrucje.getSvaMjesta().dajLokacije()) {
        System.out.println("    " + ((MjestoComposite) mjesto).getId() + ": "
            + ((MjestoComposite) mjesto).getNaziv());
        for (LokacijaComponent ulica : mjesto.dajLokacije()) {
          System.out.println(
              "        " + ((UlicaLeaf) ulica).getId() + ": " + ((UlicaLeaf) ulica).getNaziv());
        }
      }
    }
  }
}
