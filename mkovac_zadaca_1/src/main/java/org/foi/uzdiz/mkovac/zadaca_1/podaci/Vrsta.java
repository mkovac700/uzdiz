package org.foi.uzdiz.mkovac.zadaca_1.podaci;

public record Vrsta(String oznaka, String opis, String visina, String sirina, String duzina,
    String maksimalnaTezina, String cijena, String cijenaHitno, String cijenaP, String cijenaT) {

  @Override
  public String toString() {
    return this.oznaka() + ";" + this.opis() + ";" + this.visina() + ";" + this.sirina() + ";"
        + this.duzina() + ";" + this.maksimalnaTezina() + ";" + this.cijena() + ";"
        + this.cijenaHitno() + ";" + this.cijenaP() + ";" + this.cijenaT();
  }


}
