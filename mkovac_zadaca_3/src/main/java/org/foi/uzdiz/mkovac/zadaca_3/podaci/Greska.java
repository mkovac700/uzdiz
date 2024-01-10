package org.foi.uzdiz.mkovac.zadaca_3.podaci;

public class Greska {
  private int redniBroj;
  private String zapis;
  private String opis;

  public Greska(int redniBroj, String zapis, String opis) {
    this.redniBroj = redniBroj;
    this.zapis = zapis;
    this.opis = opis;
  }

  @Override
  public String toString() {
    return "[GREŠKA #" + redniBroj + "] SADRŽAJ RETKA: " + zapis + " OPIS GREŠKE: " + opis;
  }


}
