package org.foi.uzdiz.mkovac.zadaca_2.podaci;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Voznja {
  private LocalDateTime vrijemePocetka;
  private LocalDateTime vrijemePovratka;
  private LocalTime trajanje;
  private float ukupnoKm;
  private int brojHitnih; // setta se odmah nakon kreiranja objekta voznje
  private int brojObicnih; // setta se odmah nakon kreiranja objekta voznje
  private int brojIsporucenih; // ++ nakon svake isporuke (unutar klase vozila) i onda se setta i
                               // ovdje

  private float zauzeceTezineNaPocetku;
  private float zauzeceProstoraNaPocetku;

  private List<Segment> segmenti;

  public Voznja() {
    segmenti = new ArrayList<>();
  }

  public void setVrijemePocetka(LocalDateTime vrijemePocetka) {
    this.vrijemePocetka = vrijemePocetka;
  }

  public void setVrijemePovratka(LocalDateTime vrijemePovratka) {
    this.vrijemePovratka = vrijemePovratka;
  }

  public void setTrajanje(LocalTime trajanje) {
    this.trajanje = trajanje;
  }

  public void setUkupnoKm(float ukupnoKm) {
    this.ukupnoKm = ukupnoKm;
  }

  public void setBrojHitnih(int brojHitnih) {
    this.brojHitnih = brojHitnih;
  }

  public void setBrojObicnih(int brojObicnih) {
    this.brojObicnih = brojObicnih;
  }

  public void setBrojIsporucenih(int brojIsporucenih) {
    this.brojIsporucenih = brojIsporucenih;
  }

  public void setZauzeceTezineNaPocetku(float zauzeceTezineNaPocetku) {
    this.zauzeceTezineNaPocetku = zauzeceTezineNaPocetku;
  }

  public void setZauzeceProstoraNaPocetku(float zauzeceProstoraNaPocetku) {
    this.zauzeceProstoraNaPocetku = zauzeceProstoraNaPocetku;
  }

  public void setSegmenti(List<Segment> segmenti) {
    this.segmenti = segmenti;
  }

  public List<Segment> getSegmenti() {
    return segmenti;
  }



}
