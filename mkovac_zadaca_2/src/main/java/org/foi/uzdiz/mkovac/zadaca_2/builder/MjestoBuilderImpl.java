package org.foi.uzdiz.mkovac.zadaca_2.builder;

/**
 * Concrete builder
 * 
 * @author Marijan Kovač
 *
 */
public class MjestoBuilderImpl implements MjestoBuilder {

  private Mjesto mjesto;

  public MjestoBuilderImpl() {
    mjesto = new Mjesto();
  }

  @Override
  public Mjesto build() {
    return mjesto;
  }

  @Override
  public MjestoBuilder setId(int id) {
    mjesto.setId(id);
    return this;
  }

  @Override
  public MjestoBuilder setNaziv(String naziv) {
    mjesto.setNaziv(naziv);
    return this;
  }

  @Override
  public MjestoBuilder setUliceId(int[] uliceId) {
    mjesto.setUliceId(uliceId);
    return this;
  }



}
