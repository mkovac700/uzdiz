package org.foi.uzdiz.mkovac.zadaca_2.builder;

import java.time.LocalDateTime;
import org.foi.uzdiz.mkovac.zadaca_2.prototype.VrstaPaketa;

public class Paket {
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

}
