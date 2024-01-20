package org.foi.uzdiz.mkovac.zadaca_3.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_3.observer.Observer;
import org.foi.uzdiz.mkovac.zadaca_3.observer.Subject;
import org.foi.uzdiz.mkovac.zadaca_3.prototype.VrstaPaketa;
import org.foi.uzdiz.mkovac.zadaca_3.visitor.PaketElement;
import org.foi.uzdiz.mkovac.zadaca_3.visitor.PaketVisitor;

public class Paket implements Subject, PaketElement {

  private List<Observer> observers = new ArrayList<>();

  private String status = "";

  private String oznaka;
  private LocalDateTime vrijemePrijema;
  private Osoba posiljatelj;
  private Osoba primatelj;
  private VrstaPaketa vrstaPaketa;
  private float visina;
  private float sirina;
  private float duzina;
  private float tezina;
  private String uslugaDostave;
  private float iznosPouzeca;

  private LocalDateTime vrijemeIsporuke;

  public Paket() {}

  public String getOznaka() {
    return oznaka;
  }

  public void setOznaka(String oznaka) {
    this.oznaka = oznaka;
  }

  public LocalDateTime getVrijemePrijema() {
    return vrijemePrijema;
  }

  public void setVrijemePrijema(LocalDateTime vrijemePrijema) {
    this.vrijemePrijema = vrijemePrijema;
  }

  public Osoba getPosiljatelj() {
    return posiljatelj;
  }

  public void setPosiljatelj(Osoba posiljatelj) {
    this.posiljatelj = posiljatelj;
  }

  public Osoba getPrimatelj() {
    return primatelj;
  }

  public void setPrimatelj(Osoba primatelj) {
    this.primatelj = primatelj;
  }

  public VrstaPaketa getVrstaPaketa() {
    return vrstaPaketa;
  }

  public void setVrstaPaketa(VrstaPaketa vrstaPaketa) {
    this.vrstaPaketa = vrstaPaketa;
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

  public float getTezina() {
    return tezina;
  }

  public void setTezina(float tezina) {
    this.tezina = tezina;
  }

  public String getUslugaDostave() {
    return uslugaDostave;
  }

  public void setUslugaDostave(String uslugaDostave) {
    this.uslugaDostave = uslugaDostave;
  }

  public float getIznosPouzeca() {
    return iznosPouzeca;
  }

  public void setIznosPouzeca(float iznosPouzeca) {
    this.iznosPouzeca = iznosPouzeca;
  }

  @Override
  public String toString() {
    return this.oznaka;
  }

  @Override
  public void addObserver(Observer observer) {
    if (!observers.contains(observer)) {
      observers.add(observer);
    } else {
      System.out.println("Pretplatnik već dodan!");
    }
  }

  @Override
  public void removeObserver(Observer observer) {
    if (observers.contains(observer)) {
      observers.remove(observer);
    } else {
      System.out.println("Pretplatnik ne postoji!");
    }

  }

  @Override
  public String getStatus() {
    return status;
  }

  @Override
  public void setStatus(String state) {
    this.status = state;
    posaljiObavijest();
  }

  @Override
  public void posaljiObavijest() {
    Iterator<Observer> i = observers.iterator();
    while (i.hasNext()) {
      Observer o = (Observer) i.next();
      o.update(this);
    }

  }

  public float getM3() {
    if (this.vrstaPaketa.oznaka.equals("X"))
      return this.sirina * this.duzina * this.visina;
    else
      return this.vrstaPaketa.sirina * this.vrstaPaketa.duzina * this.vrstaPaketa.visina;
  }

  public LocalDateTime getVrijemeIsporuke() {
    return vrijemeIsporuke;
  }

  public void setVrijemeIsporuke(LocalDateTime vrijemeIsporuke) {
    this.vrijemeIsporuke = vrijemeIsporuke;
  }

  @Override
  public String[] accept(PaketVisitor visitor) {
    return visitor.visit(this);
  }

  // AKA cijena paketa
  public float getIznosDostave() {
    float iznos = 0.0f;

    if (this.uslugaDostave.equals("P"))
      return iznos;

    if (this.vrstaPaketa.oznaka.equals("X")) {
      if (this.uslugaDostave.equals("H")) {
        iznos = this.vrstaPaketa.cijenaHitno + (this.vrstaPaketa.cijenaP * this.getM3())
            + (this.vrstaPaketa.cijenaT * this.getTezina());
      } else {
        iznos = this.vrstaPaketa.cijena + (this.vrstaPaketa.cijenaP * this.getM3())
            + (this.vrstaPaketa.cijenaT * this.getTezina());
      }
    } else {
      if (this.uslugaDostave.equals("H")) {
        iznos = this.vrstaPaketa.cijenaHitno;
      } else {
        iznos = this.vrstaPaketa.cijena;
      }
    }

    return iznos;
  }

}
