package org.foi.uzdiz.mkovac.zadaca_2.prototype;

public class TipskiPaket extends VrstaPaketa {

  public TipskiPaket(String oznaka, String opis, float visina, float sirina, float duzina,
      float maksimalnaTezina, float cijena, float cijenaHitno, float cijenaP, float cijenaT) {
    this.oznaka = oznaka;
    this.opis = opis;
    this.visina = visina;
    this.sirina = sirina;
    this.duzina = duzina;
    this.maksimalnaTezina = maksimalnaTezina;
    this.cijena = cijena;
    this.cijenaHitno = cijenaHitno;
    this.cijenaP = cijenaP;
    this.cijenaT = cijenaT;
  }

  public TipskiPaket(TipskiPaket target) {
    super(target);
  }

  @Override
  public VrstaPaketa clone() {
    return new TipskiPaket(this);
  }

}
