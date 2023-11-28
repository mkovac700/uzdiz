package org.foi.uzdiz.mkovac.zadaca_2.prototype;

import org.foi.uzdiz.mkovac.zadaca_2.podaci.CijenaVrsta;

public class NetipskiPaket extends VrstaPaketa {
  public NetipskiPaket(String oznaka, String opis, float visina, float sirina, float duzina,
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

  public NetipskiPaket(NetipskiPaket target) {
    super(target);
  }

  @Override
  public VrstaPaketa clone() {
    return new NetipskiPaket(this);
  }

  public float izracunajCijenu(CijenaVrsta cijenaVrsta, float volumen, float tezina) {
    if (cijenaVrsta == CijenaVrsta.standardno)
      return this.cijena + (this.cijenaP * volumen) + (this.cijenaT * tezina);
    else
      return this.cijenaHitno + (this.cijenaP * volumen) + (this.cijenaT * tezina);
  }
}
