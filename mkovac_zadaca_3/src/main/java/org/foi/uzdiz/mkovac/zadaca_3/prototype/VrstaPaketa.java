package org.foi.uzdiz.mkovac.zadaca_3.prototype;

public abstract class VrstaPaketa {
  public String oznaka;
  public String opis;
  public float visina;
  public float sirina;
  public float duzina;
  public float maksimalnaTezina;
  public float cijena;
  public float cijenaHitno;
  public float cijenaP;
  public float cijenaT;

  public VrstaPaketa() {}

  public VrstaPaketa(VrstaPaketa target) {
    this.oznaka = target.oznaka;
    this.opis = target.opis;
    this.visina = target.visina;
    this.sirina = target.sirina;
    this.duzina = target.duzina;
    this.maksimalnaTezina = target.maksimalnaTezina;
    this.cijena = target.cijena;
    this.cijenaHitno = target.cijenaHitno;
    this.cijenaP = target.cijenaP;
    this.cijenaT = target.cijenaT;
  }

  public abstract VrstaPaketa clone();
}
