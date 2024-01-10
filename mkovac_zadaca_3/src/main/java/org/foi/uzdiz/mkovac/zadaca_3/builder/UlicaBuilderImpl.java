package org.foi.uzdiz.mkovac.zadaca_3.builder;

import org.foi.uzdiz.mkovac.zadaca_3.composite.UlicaLeaf;

/**
 * Concrete builder
 * 
 * @author Marijan Kovač
 *
 */
public class UlicaBuilderImpl implements UlicaBuilder {

  private UlicaLeaf ulica;

  public UlicaBuilderImpl() {
    ulica = new UlicaLeaf();
  }

  @Override
  public UlicaLeaf build() {
    return ulica;
  }

  @Override
  public UlicaBuilder setId(int id) {
    ulica.setId(id);
    return this;
  }

  @Override
  public UlicaBuilder setNaziv(String naziv) {
    ulica.setNaziv(naziv);
    return this;
  }

  @Override
  public UlicaBuilder setGpsLat1(float gpsLat1) {
    ulica.setGpsLat1(gpsLat1);
    return this;
  }

  @Override
  public UlicaBuilder setGpsLon1(float gpsLon1) {
    ulica.setGpsLon1(gpsLon1);
    return this;
  }

  @Override
  public UlicaBuilder setGpsLat2(float gpsLat2) {
    ulica.setGpsLat2(gpsLat2);
    return this;
  }

  @Override
  public UlicaBuilder setGpsLon2(float gpsLon2) {
    ulica.setGpsLon2(gpsLon2);
    return this;
  }

  @Override
  public UlicaBuilder setNajveciKucniBroj(int najveciKucniBroj) {
    ulica.setNajveciKucniBroj(najveciKucniBroj);
    return this;
  }

}
