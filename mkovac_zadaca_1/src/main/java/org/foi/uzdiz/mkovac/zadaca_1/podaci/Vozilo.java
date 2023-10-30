package org.foi.uzdiz.mkovac.zadaca_1.podaci;

public record Vozilo(String registracija, String opis, String kapacitetTezine,
    String kapacitetProstora, String redoslijed) {

  @Override
  public String toString() {
    return this.registracija() + ";" + this.opis() + ";" + this.kapacitetTezine() + ";"
        + this.kapacitetProstora() + ";" + this.redoslijed();
  }


}
