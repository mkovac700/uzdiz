package org.foi.uzdiz.mkovac.zadaca_1.builder;

import org.foi.uzdiz.mkovac.zadaca_1.podaci.CijenaVrsta;

public class VrstaPaketa {

  private String oznaka;
  private String opis;
  private float visina;
  private float sirina;
  private float duzina;
  private float maksimalnaTezina;
  private float cijena;
  private float cijenaHitno;
  private float cijenaP;
  private float cijenaT;

  public VrstaPaketa() {}

  public String getOznaka() {
    return oznaka;
  }

  public void setOznaka(String oznaka) {
    this.oznaka = oznaka;
  }

  public String getOpis() {
    return opis;
  }

  public void setOpis(String opis) {
    this.opis = opis;
  }

  public float getVisina() {
    return visina;
  }

  public void setVisina(float visina) {
    this.visina = visina;
  }

  public float getSirina() {
    return sirina;
  }

  public void setSirina(float sirina) {
    this.sirina = sirina;
  }

  public float getDuzina() {
    return duzina;
  }

  public void setDuzina(float duzina) {
    this.duzina = duzina;
  }

  public float getMaksimalnaTezina() {
    return maksimalnaTezina;
  }

  public void setMaksimalnaTezina(float maksimalnaTezina) {
    this.maksimalnaTezina = maksimalnaTezina;
  }

  public float getCijena() {
    return cijena;
  }

  public void setCijena(float cijena) {
    this.cijena = cijena;
  }

  public float getCijenaHitno() {
    return cijenaHitno;
  }

  public void setCijenaHitno(float cijenaHitno) {
    this.cijenaHitno = cijenaHitno;
  }

  public float getCijenaP() {
    return cijenaP;
  }

  public void setCijenaP(float cijenaP) {
    this.cijenaP = cijenaP;
  }

  public float getCijenaT() {
    return cijenaT;
  }

  public void setCijenaT(float cijenaT) {
    this.cijenaT = cijenaT;
  }

  public float izracunajCijenu(CijenaVrsta cijena) {
    if (oznaka.equals("X")) {
      if (cijena == CijenaVrsta.standardno) {

      } else {

      }
    } else {
      if (cijena == CijenaVrsta.standardno) {

      } else {

      }
    }

    return 0;
  }

  @Override
  public String toString() {
    return this.oznaka;
  }



}
