package org.foi.uzdiz.mkovac.zadaca_3.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.Segment;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.Voznja;
import org.foi.uzdiz.mkovac.zadaca_3.singleton.TvrtkaSingleton;
import org.foi.uzdiz.mkovac.zadaca_3.state.StatusVozilaState;
import org.foi.uzdiz.mkovac.zadaca_3.state.StatusVoznjeState;
import org.foi.uzdiz.mkovac.zadaca_3.state.UkrcavanjeState;
import org.foi.uzdiz.mkovac.zadaca_3.strategy.IsporukaStrategy;

public class Vozilo {
  private String registracija;
  private String opis;
  private float kapacitetTezine;
  private float kapacitetProstora;
  private int redoslijed;
  private float prosjecnaBrzina;
  private List<Podrucje> podrucjaPoRangu;
  private StatusVozilaState status;

  private StatusVoznjeState statusVoznje;

  private List<Paket> paketi;
  private Podrucje trenutnoPodrucje;

  private List<Voznja> voznje;

  private Voznja trenutnaVoznja;

  private IsporukaStrategy isporukaStrategy;

  private LocalDateTime vrijemePovratka;

  public Vozilo() {
    paketi = new ArrayList<>();
    statusVoznje = new UkrcavanjeState();
    voznje = new ArrayList<>();
  }

  public float getOdvozenoKm() {
    float ukupno = 0;
    for (Voznja v : voznje) {
      ukupno += v.getUkupnoKm();
    }
    return ukupno;
  }

  public int getBrojHitnih() {
    int brojHitnih = 0;
    for (Paket p : paketi) {
      if (p.getUslugaDostave().equals("H"))
        brojHitnih++;
    }
    return brojHitnih;
  }

  public int getBrojObicnih() {
    int brojObicnih = 0;
    for (Paket p : paketi) {
      if (!p.getUslugaDostave().equals("H"))
        brojObicnih++;
    }
    return brojObicnih;
  }

  public int getBrojIsporucenih() {
    int brojIsporucenih = 0;
    for (Paket p : paketi) {
      if (p.getStatus().equals("PREUZETO"))
        brojIsporucenih++;
    }
    return brojIsporucenih;
  }

  public int getBrojVoznji() {
    return voznje.size();
  }

  public float getTrenutniPostotakZauzecaProstora() {
    return (this.izracunajTrenutnoZauzeceProstora() / this.kapacitetProstora) * 100;
  }

  public float getTrenutniPostotakZauzecaTezine() {
    return (this.izracunajTrenutnuTezinu() / this.kapacitetTezine) * 100;
  }

  public String getRegistracija() {
    return registracija;
  }

  public void setRegistracija(String registracija) {
    this.registracija = registracija;
  }

  public String getOpis() {
    return opis;
  }

  public void setOpis(String opis) {
    this.opis = opis;
  }

  public float getKapacitetTezine() {
    return kapacitetTezine;
  }

  public void setKapacitetTezine(float kapacitetTezine) {
    this.kapacitetTezine = kapacitetTezine;
  }

  public float getKapacitetProstora() {
    return kapacitetProstora;
  }

  public void setKapacitetProstora(float kapacitetProstora) {
    this.kapacitetProstora = kapacitetProstora;
  }

  public int getRedoslijed() {
    return redoslijed;
  }

  public void setRedoslijed(int redoslijed) {
    this.redoslijed = redoslijed;
  }

  public float getProsjecnaBrzina() {
    return prosjecnaBrzina;
  }

  public void setProsjecnaBrzina(float prosjecnaBrzina) {
    this.prosjecnaBrzina = prosjecnaBrzina;
  }

  public List<Podrucje> getPodrucjaPoRangu() {
    return podrucjaPoRangu;
  }

  public void setPodrucjaPoRangu(List<Podrucje> podrucjaPoRangu) {
    this.podrucjaPoRangu = podrucjaPoRangu;
  }

  public StatusVozilaState getStatus() {
    return status;
  }

  public void setStatus(StatusVozilaState status) {
    this.status = status;
  }

  public void aktiviraj() {
    this.status.aktiviraj(this);
  }

  public void deaktiviraj() {
    this.status.deaktiviraj(this);
  }

  public void setNeispravno() {
    this.status.setNeispravno(this);
  }

  public void setUkrcavanje() {
    this.statusVoznje.setUkrcavanje(this);
  }

  public void setIsporuka() {
    this.statusVoznje.setIsporuka(this);
  }

  public void setPovratak() {
    this.statusVoznje.setPovratak(this);
  }

  public StatusVoznjeState getStatusVoznje() {
    return statusVoznje;
  }

  public void setStatusVoznje(StatusVoznjeState statusVoznje) {
    this.statusVoznje = statusVoznje;
  }

  @Override
  public String toString() {
    return this.opis;
  }

  public List<Paket> getPaketi() {
    return paketi;
  }

  public Podrucje getTrenutnoPodrucje() {
    return trenutnoPodrucje;
  }

  public void setTrenutnoPodrucje(Podrucje trenutnoPodrucje) {
    this.trenutnoPodrucje = trenutnoPodrucje;
  }



  public Voznja getTrenutnaVoznja() {
    return trenutnaVoznja;
  }

  public float izracunajTrenutnuTezinu() {
    float tezinaUkupno = 0;
    for (Paket p : paketi) {
      tezinaUkupno += p.getTezina();
    }
    return tezinaUkupno;
  }

  public float izracunajTrenutnoZauzeceProstora() {
    float prostorUkupno = 0;
    for (Paket p : paketi) {
      prostorUkupno += p.getM3();
    }
    return prostorUkupno;
  }

  public void ukrcajPaket(Paket paket, Podrucje podrucje) {
    if (paketi.isEmpty()) {
      // TODO znači da ako nema paketa, trenutno podrucje je null (first run)
      // pa se mora postavit sukladno uvjetima u ured dostava
      // VAŽNO - kod iskrcaja se mora vratit nazad na null kad se isporuči zadnji paket!!!
      trenutnoPodrucje = podrucje;
      trenutnaVoznja = new Voznja();
      trenutnaVoznja.setVrijemePocetka(TvrtkaSingleton.getInstance().virtualniSat);
      voznje.add(trenutnaVoznja);
    }

    paketi.add(paket);
  }

  public IsporukaStrategy getIsporukaStrategy() {
    return isporukaStrategy;
  }

  public void setIsporukaStrategy(IsporukaStrategy isporukaStrategy) {
    this.isporukaStrategy = isporukaStrategy;
  }

  public void odrediVrstuIsporuke() {

    isporukaStrategy.obaviIzracune(paketi, trenutnaVoznja);

    LocalDateTime vrijemePocetka = TvrtkaSingleton.getInstance().virtualniSat;

    // dodaj ostale podatke za segmente
    for (Segment segment : trenutnaVoznja.getSegmenti()) {
      segment.setTrajanjeVoznje((int) segment.getUdaljenost() / (int) this.prosjecnaBrzina * 60);
      segment
          .setUkupnoTrajanjeSegmenta(segment.getTrajanjeVoznje() + segment.getTrajanjeIsporuke());
      segment.setVrijemePocetka(vrijemePocetka);
      segment.setVrijemeKraja(vrijemePocetka.plusMinutes(segment.getUkupnoTrajanjeSegmenta()));

      vrijemePocetka = segment.getVrijemeKraja();
    }

    this.setIsporuka();

    for (Paket paket : paketi) {
      paket.setStatus("U DOSTAVI");
    }

    try {
      trenutnaVoznja.setZauzeceProstoraNaPocetku(
          (this.izracunajTrenutnuTezinu() / this.kapacitetTezine) * 100);
    } catch (Exception e) {
      trenutnaVoznja.setZauzeceProstoraNaPocetku(0);
    }

    try {
      trenutnaVoznja.setZauzeceTezineNaPocetku(
          (this.izracunajTrenutnoZauzeceProstora() / this.kapacitetProstora) * 100);
    } catch (Exception e) {
      trenutnaVoznja.setZauzeceProstoraNaPocetku(0);
    }
  }

  public void obaviIsporuku() {
    // this.setIsporuka();

    // for (Paket paket : paketi) {
    // paket.setOznaka("U DOSTAVI");
    // }

    // TODO mozda dodati provjeru statusa vozila dal je u dostavi?


    // if (!paketi.isEmpty()) {
    for (Segment segment : trenutnaVoznja.getSegmenti()) {



      // ovdje se provjeri i da nije zadnji segment u pitanju, odnosno povratak (paket je null)
      if (paketi.isEmpty() && segment.getPaket() == null) {
        this.setPovratak();
        this.setVrijemePovratka(segment.getVrijemeKraja());

        trenutnaVoznja.setVrijemePovratka(vrijemePovratka);

        this.trenutnoPodrucje = null;
        this.trenutnaVoznja = null;

        System.out.println("Vozilo " + this.getRegistracija() + " bi se trebalo vratiti u "
            + TvrtkaSingleton.getInstance().konvertirajDatumVrijeme(this.getVrijemePovratka()));

      }

      if (segment.getPaket() != null && segment.getPaket().getStatus().equals("U DOSTAVI")) {
        // ako je vrijeme za to ukloni paket iz liste paketa


        if (segment.getVrijemeKraja().isBefore(TvrtkaSingleton.getInstance().virtualniSat)
            || segment.getVrijemeKraja().isEqual(TvrtkaSingleton.getInstance().virtualniSat)) {
          segment.getPaket().setVrijemeIsporuke(TvrtkaSingleton.getInstance().virtualniSat);
          segment.getPaket().setStatus("PREUZETO");
          paketi.remove(segment.getPaket());
        }
      }
    }
    // }


    // nakon svega provjeri jel lista paketa prazna (svi dostavljeni) i u tom slucaju settaj vozilo
    // u
    // stanje povratka te odredi vrijeme kad bi se trebalo vratit
    /*
     * if (paketi.isEmpty()) { this.setPovratak(); }
     */


  }

  public LocalDateTime getVrijemePovratka() {
    return vrijemePovratka;
  }

  public void setVrijemePovratka(LocalDateTime vrijemePovratka) {
    this.vrijemePovratka = vrijemePovratka;
  }

}
