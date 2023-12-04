package org.foi.uzdiz.mkovac.zadaca_2.builder;

import org.foi.uzdiz.mkovac.zadaca_2.composite.MjestoComposite;
import org.foi.uzdiz.mkovac.zadaca_2.composite.UlicaLeaf;

public class Osoba {
  private String osoba;
  private MjestoComposite grad;
  private UlicaLeaf ulica;
  int kbr;

  public Osoba() {}

  public String getOsoba() {
    return osoba;
  }

  public void setOsoba(String osoba) {
    this.osoba = osoba;
  }

  public MjestoComposite getGrad() {
    return grad;
  }

  public void setGrad(MjestoComposite grad) {
    this.grad = grad;
  }

  public UlicaLeaf getUlica() {
    return ulica;
  }

  public void setUlica(UlicaLeaf ulica) {
    this.ulica = ulica;
  }

  public int getKbr() {
    return kbr;
  }

  public void setKbr(int kbr) {
    this.kbr = kbr;
  }

  @Override
  public String toString() {
    return this.osoba + " -> " + "Grad: " + this.grad.getId() + ", Ulica: " + this.ulica.getId()
        + ", Kbr: " + this.kbr;
  }



}
