package org.foi.uzdiz.mkovac.zadaca_1.podaci;

public record Paket(String oznaka, String vrijemePrijema, String posiljatelj, String primatelj,
    String vrstaPaketa, String visina, String sirina, String duzina, String tezina,
    String uslugaDostave, String iznosPouzeca) {

  @Override
  public String toString() {
    return this.oznaka() + ";" + this.vrijemePrijema() + ";" + this.posiljatelj() + ";"
        + this.primatelj() + ";" + this.vrstaPaketa() + ";" + this.visina() + ";" + this.sirina()
        + ";" + this.duzina() + ";" + this.tezina() + ";" + this.uslugaDostave() + ";"
        + this.iznosPouzeca();
  }


}
