package org.foi.uzdiz.mkovac.zadaca_3.podaci;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_3.visitor.Element;
import org.foi.uzdiz.mkovac.zadaca_3.visitor.Visitor;

public class Voznja implements Element {
  private LocalDateTime vrijemePocetka;
  private LocalDateTime vrijemePovratka;
  // private LocalTime trajanje;
  // private float ukupnoKm;
  // private int brojHitnih; // setta se odmah nakon kreiranja objekta voznje
  // private int brojObicnih; // setta se odmah nakon kreiranja objekta voznje
  // private int brojIsporucenih; // ++ nakon svake isporuke (unutar klase vozila) i onda se setta i
  // ovdje

  private float zauzeceTezineNaPocetku;
  private float zauzeceProstoraNaPocetku;

  private List<Segment> segmenti;

  public Voznja() {
    segmenti = new ArrayList<>();
  }

  public LocalDateTime getVrijemePocetka() {
    return vrijemePocetka;
  }

  public LocalDateTime getVrijemePovratka() {
    return vrijemePovratka;
  }

  public LocalTime getTrajanje() {
    if (vrijemePocetka == null || vrijemePovratka == null)
      return null;
    Duration duration = Duration.between(vrijemePocetka, vrijemePovratka);
    return LocalTime.of(duration.toHoursPart(), duration.toMinutesPart(), duration.toSecondsPart());
  }

  public float getUkupnoKm() {
    float ukupnoKm = 0;
    for (Segment s : segmenti) {
      ukupnoKm += s.getUdaljenost();
    }
    return ukupnoKm;
  }

  public int getBrojHitnih() {
    int brojHitnih = 0;
    for (Segment s : segmenti) {
      if (s.getPaket() != null && s.getPaket().getUslugaDostave().equals("H"))
        brojHitnih++;
    }
    return brojHitnih;
  }

  public int getBrojObicnih() {
    int brojObicnih = 0;
    for (Segment s : segmenti) {
      if (s.getPaket() != null && !s.getPaket().getUslugaDostave().equals("H"))
        brojObicnih++;
    }
    return brojObicnih;
  }

  public int getBrojIsporucenih() {
    int brojIsporucenih = 0;
    for (Segment s : segmenti) {
      if (s.getPaket() != null && s.getPaket().getStatus().equals("PREUZETO"))
        brojIsporucenih++;
    }
    return brojIsporucenih;
  }

  /*
   * public float getZauzeceTezineNaPocetku() { float tezinaUkupno = 0; for (Segment s : segmenti) {
   * tezinaUkupno += s.getPaket().getTezina(); } return tezinaUkupno; }
   * 
   * public float getZauzeceProstoraNaPocetku() { float prostorUkupno = 0; for (Segment s :
   * segmenti) { prostorUkupno += s.getPaket().getM3(); } return prostorUkupno; }
   */

  public void setVrijemePocetka(LocalDateTime vrijemePocetka) {
    this.vrijemePocetka = vrijemePocetka;
  }

  public void setVrijemePovratka(LocalDateTime vrijemePovratka) {
    this.vrijemePovratka = vrijemePovratka;
  }

  public void setZauzeceTezineNaPocetku(float zauzeceTezineNaPocetku) {
    this.zauzeceTezineNaPocetku = zauzeceTezineNaPocetku;
  }

  public void setZauzeceProstoraNaPocetku(float zauzeceProstoraNaPocetku) {
    this.zauzeceProstoraNaPocetku = zauzeceProstoraNaPocetku;
  }

  public float getZauzeceTezineNaPocetku() {
    return zauzeceTezineNaPocetku;
  }

  public float getZauzeceProstoraNaPocetku() {
    return zauzeceProstoraNaPocetku;
  }

  public void setSegmenti(List<Segment> segmenti) {
    this.segmenti = segmenti;
  }

  public List<Segment> getSegmenti() {
    return segmenti;
  }

  @Override
  public String[] accept(Visitor visitor) {
    return visitor.visit(this);
  }



}
