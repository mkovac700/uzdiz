package org.foi.uzdiz.mkovac.zadaca_3.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_3.observer.Observer;
import org.foi.uzdiz.mkovac.zadaca_3.observer.Subject;
import org.foi.uzdiz.mkovac.zadaca_3.prototype.VrstaPaketa;

public class Paket implements Subject {

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
    return this.sirina * this.duzina * this.visina;
  }

  public LocalDateTime getVrijemeIsporuke() {
    return vrijemeIsporuke;
  }

  public void setVrijemeIsporuke(LocalDateTime vrijemeIsporuke) {
    this.vrijemeIsporuke = vrijemeIsporuke;
  }



}
