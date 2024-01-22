package org.foi.uzdiz.mkovac.zadaca_3.memento;

import java.time.LocalDateTime;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_3.builder.Paket;
import org.foi.uzdiz.mkovac.zadaca_3.builder.Vozilo;

public class TvrtkaMemento {
  public LocalDateTime virtualniSat;
  private List<Paket> paketi;
  private List<Vozilo> vozila;

  public TvrtkaMemento(LocalDateTime virtualniSat, List<Paket> paketi, List<Vozilo> vozila) {
    this.virtualniSat = virtualniSat;
    this.paketi = paketi;
    this.vozila = vozila;
  }

  public LocalDateTime getVirtualniSat() {
    return virtualniSat;
  }

  public List<Paket> getPaketi() {
    return paketi;
  }

  public List<Vozilo> getVozila() {
    return vozila;
  }


}
