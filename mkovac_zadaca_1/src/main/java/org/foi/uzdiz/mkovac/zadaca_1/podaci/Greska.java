package org.foi.uzdiz.mkovac.zadaca_1.podaci;

public record Greska(int redniBroj, String zapis, String opis) {

  @Override
  public String toString() {
    return "#" + redniBroj + " Zapis: " + zapis + " Opis: " + opis;
  }


}
