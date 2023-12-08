package org.foi.uzdiz.mkovac.zadaca_2.podaci;

import java.time.LocalDateTime;
import java.time.LocalTime;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Paket;

public class Segment {
  private String odGps;
  private String doGps;
  private float udaljenost; // valjda isto sto i km
  private LocalDateTime vrijemePocetka;
  private LocalDateTime vrijemeKraja;
  private LocalTime trajanjeVoznje;
  private LocalTime trajanjeIsporuke;
  private LocalTime ukupnoTrajanjeSegmenta;
  private float km;
  private Paket paket;


}
