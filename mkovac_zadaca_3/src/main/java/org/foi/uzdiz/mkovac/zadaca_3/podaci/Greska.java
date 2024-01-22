package org.foi.uzdiz.mkovac.zadaca_3.podaci;

public class Greska {
  private static final String ANSI_RED = "\033[31m";
  private static final String ANSI_GREEN = "\033[33m";
  private static final String ANSI_CLEAR = "\033[0m";

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
    return ANSI_RED + "[GREŠKA #" + redniBroj + "]" + ANSI_CLEAR + " SADRŽAJ RETKA: " + zapis
        + " OPIS GREŠKE: " + opis;
  }


}
