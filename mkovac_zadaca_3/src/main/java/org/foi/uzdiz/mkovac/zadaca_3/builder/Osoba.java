package org.foi.uzdiz.mkovac.zadaca_3.builder;

import org.foi.uzdiz.mkovac.zadaca_3.composite.MjestoComposite;
import org.foi.uzdiz.mkovac.zadaca_3.composite.UlicaLeaf;
import org.foi.uzdiz.mkovac.zadaca_3.observer.Observer;
import org.foi.uzdiz.mkovac.zadaca_3.observer.Subject;

public class Osoba implements Observer {
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

  @Override
  public void update(Subject subject) {
    if (subject instanceof Paket) {
      String oznaka = ((Paket) subject).getOznaka();
      String poruka = "Paket " + oznaka + " promijenio status u " + subject.getStatus();
      System.out.println(this.osoba + ": Stigla poruka '" + poruka + "'");
    }
  }



}
