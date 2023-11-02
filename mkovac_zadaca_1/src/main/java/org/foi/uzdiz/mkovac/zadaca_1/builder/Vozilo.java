package org.foi.uzdiz.mkovac.zadaca_1.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_1.singleton.TvrtkaSingleton;

/**
 * Product created by builder
 * 
 * @author Marijan Kovač
 *
 */
public class Vozilo {
  private List<Paket> paketi;

  private String registracija;
  private String opis;
  private float kapacitetTezine;
  private float kapacitetProstora;
  private int redoslijed;

  private float kapacitetTezineTrenutno = 0;
  private float kapacitetProstoraTrenutno = 0;

  private boolean slobodno = true;
  private LocalDateTime vrijemePolaska;
  private LocalDateTime vrijemeZadnjegDostavljenogPaketa;

  public Vozilo() {
    paketi = new ArrayList<>();
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

  public boolean isSlobodno() {
    return slobodno;
  }

  public void setSlobodno(boolean slobodno) {
    this.slobodno = slobodno;
  }

  public LocalDateTime getVrijemePolaska() {
    return vrijemePolaska;
  }



  public float getKapacitetTezineTrenutno() {
    return kapacitetTezineTrenutno;
  }

  public float getKapacitetProstoraTrenutno() {
    return kapacitetProstoraTrenutno;
  }

  public LocalDateTime getVrijemeZadnjegDostavljenogPaketa() {
    return vrijemeZadnjegDostavljenogPaketa;
  }

  public void setVrijemeZadnjegDostavljenogPaketa(LocalDateTime vrijemeZadnjegDostavljenogPaketa) {
    this.vrijemeZadnjegDostavljenogPaketa = vrijemeZadnjegDostavljenogPaketa;
  }

  public boolean dodajPaket(Paket paket) {
    // ako kapacitet prostora ili kapacitet tezine s novim paketom prelazi zadani kapacitet, postavi
    // u status dostave i vrati false
    if (kapacitetProstoraTrenutno + paket.getM3() > kapacitetProstora
        || kapacitetTezineTrenutno + paket.getTezina() > kapacitetTezine) {

      // ako nema paketa u vozilu, znaci da paket ne stane u odabrano vozilo, i nastavi dalje
      // if (!paketi.isEmpty()) {
      //
      // }

      vrijemePolaska = TvrtkaSingleton.getInstance().getVirtualniSat();
      vrijemeZadnjegDostavljenogPaketa = vrijemePolaska;

      if (!paketi.isEmpty())
        System.out.println("[" + TvrtkaSingleton.getInstance().getVirtualniSat() + "]" + " Vozilo "
            + this + " krenulo u dostavu!");

      // za sve pakete postavi vrijeme preuzimanja

      int brojac = TvrtkaSingleton.getInstance().vrijemeIsporuke;

      for (Paket p : paketi) {
        p.setVrijemePreuzimanja(vrijemePolaska.plusMinutes(brojac));
        brojac += TvrtkaSingleton.getInstance().vrijemeIsporuke;

        // System.out.println("Paket: " + p + " -> Vrijeme preuzimanja: " +
        // p.getVrijemePreuzimanja());
      }

      slobodno = false;

      return false;

    } else {
      kapacitetProstoraTrenutno += paket.getM3();
      kapacitetTezineTrenutno += paket.getTezina();

      paketi.add(paket);

      System.out.println("[" + TvrtkaSingleton.getInstance().getVirtualniSat() + "]"
          + " Ukrcan paket: " + paket + " u vozilo: " + this);

      return true;
    }
  }

  public boolean ukloniPaket(Paket paket) {

    if (paketi.isEmpty()) {// ako nema vise paketa
      slobodno = true;

      return false;
    } else {
      // TODO azuriraj objekt paketa

      // vrijemeZadnjegDostavljenogPaketa = TvrtkaSingleton.getInstance().getVirtualniSat();

      // System.out.println("[" + TvrtkaSingleton.getInstance().getVirtualniSat() + "]"
      // + " Dostavljen paket: " + paket);

      System.out.println("[" + paket.getVrijemePreuzimanja() + "]" + " Dostavljen paket: " + paket);


      // paketi.remove(0);

      paketi.remove(paket);

      return true;
    }

  }

  public void posaljiUDostavu() {
    vrijemePolaska = TvrtkaSingleton.getInstance().getVirtualniSat();
    vrijemeZadnjegDostavljenogPaketa = vrijemePolaska;

    if (!paketi.isEmpty())
      System.out.println("[" + TvrtkaSingleton.getInstance().getVirtualniSat() + "]" + " Vozilo "
          + this + " krenulo u dostavu!");

    // za sve pakete postavi vrijeme preuzimanja

    int brojac = TvrtkaSingleton.getInstance().vrijemeIsporuke;

    for (Paket p : paketi) {
      p.setVrijemePreuzimanja(vrijemePolaska.plusMinutes(brojac));
      brojac += TvrtkaSingleton.getInstance().vrijemeIsporuke;

      // System.out.println("Paket: " + p + " -> Vrijeme preuzimanja: " +
      // p.getVrijemePreuzimanja());
    }

    slobodno = false;
  }

  public List<Paket> getPaketi() {
    return paketi;
  }

  @Override
  public String toString() {
    return this.opis;
  }


}
