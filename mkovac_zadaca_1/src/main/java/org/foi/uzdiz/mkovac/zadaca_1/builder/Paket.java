package org.foi.uzdiz.mkovac.zadaca_1.builder;

import java.time.LocalDateTime;

public class Paket {

  private String oznaka;
  private LocalDateTime vrijemePrijema;
  private String posiljatelj;
  private String primatelj;
  private VrstaPaketa vrstaPaketa;
  private float visina;
  private float sirina;
  private float duzina;
  private float tezina;
  private String uslugaDostave;
  private float iznosPouzeca;

  private LocalDateTime vrijemePreuzimanja;

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

  public String getPosiljatelj() {
    return posiljatelj;
  }

  public void setPosiljatelj(String posiljatelj) {
    this.posiljatelj = posiljatelj;
  }

  public String getPrimatelj() {
    return primatelj;
  }

  public void setPrimatelj(String primatelj) {
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

  public float getM3() {
    return this.visina * this.sirina * this.duzina;
  }

  public LocalDateTime getVrijemePreuzimanja() {
    return vrijemePreuzimanja;
  }

  public void setVrijemePreuzimanja(LocalDateTime vrijemePreuzimanja) {
    this.vrijemePreuzimanja = vrijemePreuzimanja;
  }



}
