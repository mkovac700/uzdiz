package org.foi.uzdiz.mkovac.zadaca_1.builder;

import java.time.LocalDateTime;

/**
 * Concrete builder
 * 
 * @author Marijan Kovač
 *
 */
public class PaketBuilderImpl implements PaketBuilder {

  private Paket paket;

  public PaketBuilderImpl() {
    paket = new Paket();
  }

  @Override
  public Paket build() {
    return paket;
  }

  @Override
  public PaketBuilder setOznaka(String oznaka) {
    paket.setOznaka(oznaka);
    return this;
  }

  @Override
  public PaketBuilder setVrijemePrijema(LocalDateTime vrijemePrijema) {
    paket.setVrijemePrijema(vrijemePrijema);
    return this;
  }

  @Override
  public PaketBuilder setPosiljatelj(String posiljatelj) {
    paket.setPosiljatelj(posiljatelj);
    return this;
  }

  @Override
  public PaketBuilder setPrimatelj(String primatelj) {
    paket.setPrimatelj(primatelj);
    return this;
  }

  @Override
  public PaketBuilder setVrstaPaketa(VrstaPaketa vrstaPaketa) {
    paket.setVrstaPaketa(vrstaPaketa);
    return this;
  }

  @Override
  public PaketBuilder setVisina(float visina) {
    paket.setVisina(visina);
    return this;
  }

  @Override
  public PaketBuilder setSirina(float sirina) {
    paket.setSirina(sirina);
    return this;
  }

  @Override
  public PaketBuilder setDuzina(float duzina) {
    paket.setDuzina(duzina);
    return this;
  }

  @Override
  public PaketBuilder setTezina(float tezina) {
    paket.setTezina(tezina);
    return this;
  }

  @Override
  public PaketBuilder setUslugaDostave(String uslugaDostave) {
    paket.setUslugaDostave(uslugaDostave);
    return this;
  }

  @Override
  public PaketBuilder setIznosPouzeca(float iznosPouzeca) {
    paket.setIznosPouzeca(iznosPouzeca);
    return this;
  }



}
