package org.foi.uzdiz.mkovac.zadaca_3.builder;

import org.foi.uzdiz.mkovac.zadaca_3.composite.MjestoComposite;

/**
 * Concrete builder
 * 
 * @author Marijan Kovač
 *
 */
public class MjestoBuilderImpl implements MjestoBuilder {

  private MjestoComposite mjesto;

  public MjestoBuilderImpl() {
    mjesto = new MjestoComposite();
  }

  @Override
  public MjestoComposite build() {
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


}
