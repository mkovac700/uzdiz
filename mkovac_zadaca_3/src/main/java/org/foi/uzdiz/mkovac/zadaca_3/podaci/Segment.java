package org.foi.uzdiz.mkovac.zadaca_3.podaci;

import java.time.LocalDateTime;
import org.foi.uzdiz.mkovac.zadaca_3.builder.Paket;
import org.foi.uzdiz.mkovac.zadaca_3.visitor.VoziloElement;
import org.foi.uzdiz.mkovac.zadaca_3.visitor.VoziloVisitor;

public class Segment implements VoziloElement {
  private String odGps;
  private String doGps;
  private float udaljenost; // km
  private LocalDateTime vrijemePocetka;
  private LocalDateTime vrijemeKraja;
  private float trajanjeVoznje; // udaljenost/prosjecnaBrzina*60 --> u minutama
  private int trajanjeIsporuke;
  private float ukupnoTrajanjeSegmenta; // trajanjeVoznje + trajanjeIsporuke
  private Paket paket;

  public Segment() {}

  public String getOdGps() {
    return odGps;
  }

  public void setOdGps(String odGps) {
    this.odGps = odGps;
  }

  public String getDoGps() {
    return doGps;
  }

  public void setDoGps(String doGps) {
    this.doGps = doGps;
  }

  public float getUdaljenost() {
    return udaljenost;
  }

  public void setUdaljenost(float udaljenost) {
    this.udaljenost = udaljenost;
  }

  public LocalDateTime getVrijemePocetka() {
    return vrijemePocetka;
  }

  public void setVrijemePocetka(LocalDateTime vrijemePocetka) {
    this.vrijemePocetka = vrijemePocetka;
  }

  public LocalDateTime getVrijemeKraja() {
    return vrijemeKraja;
  }

  public void setVrijemeKraja(LocalDateTime vrijemeKraja) {
    this.vrijemeKraja = vrijemeKraja;
  }

  public float getTrajanjeVoznje() {
    return trajanjeVoznje;
  }

  public void setTrajanjeVoznje(float trajanjeVoznje) {
    this.trajanjeVoznje = trajanjeVoznje;
  }

  public int getTrajanjeIsporuke() {
    return trajanjeIsporuke;
  }

  public void setTrajanjeIsporuke(int trajanjeIsporuke) {
    this.trajanjeIsporuke = trajanjeIsporuke;
  }

  public float getUkupnoTrajanjeSegmenta() {
    return ukupnoTrajanjeSegmenta;
  }

  public void setUkupnoTrajanjeSegmenta(float ukupnoTrajanjeSegmenta) {
    this.ukupnoTrajanjeSegmenta = ukupnoTrajanjeSegmenta;
  }

  public Paket getPaket() {
    return paket;
  }

  public void setPaket(Paket paket) {
    this.paket = paket;
  }

  @Override
  public String[] accept(VoziloVisitor visitor) {
    return visitor.visit(this);
  }


}
