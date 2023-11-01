package org.foi.uzdiz.mkovac.zadaca_1.builder;

import java.util.ArrayList;
import java.util.List;

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

  private boolean uDostavi = false;

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

  @Override
  public String toString() {
    return this.opis;
  }

  public boolean dodajPaket(Paket paket) {
    // ako kapacitet prostora ili kapacitet tezine s novim paketom prelazi zadani kapacitet, postavi
    // u status dostave i vrati false
    if (kapacitetProstoraTrenutno + paket.getM3() > kapacitetProstora
        || kapacitetTezineTrenutno + paket.getTezina() > kapacitetTezine) {

      uDostavi = true;
      return false;

    } else {
      kapacitetProstoraTrenutno += paket.getM3();
      kapacitetTezineTrenutno += paket.getTezina();

      paketi.add(paket);

      return true;
    }

  }

}
