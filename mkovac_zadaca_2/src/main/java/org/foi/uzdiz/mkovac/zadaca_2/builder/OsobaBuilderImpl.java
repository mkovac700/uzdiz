package org.foi.uzdiz.mkovac.zadaca_2.builder;

import org.foi.uzdiz.mkovac.zadaca_2.composite.MjestoComposite;
import org.foi.uzdiz.mkovac.zadaca_2.composite.UlicaLeaf;

/**
 * Concrete builder
 * 
 * @author Marijan Kovač
 *
 */
public class OsobaBuilderImpl implements OsobaBuilder {

  private Osoba osoba;

  public OsobaBuilderImpl() {
    osoba = new Osoba();
  }

  @Override
  public Osoba build() {
    return osoba;
  }

  @Override
  public OsobaBuilder setOsoba(String osoba) {
    this.osoba.setOsoba(osoba);
    return this;
  }

  @Override
  public OsobaBuilder setGrad(MjestoComposite grad) {
    osoba.setGrad(grad);
    return this;
  }

  @Override
  public OsobaBuilder setUlica(UlicaLeaf ulica) {
    osoba.setUlica(ulica);
    return this;
  }

  @Override
  public OsobaBuilder setKbr(int kbr) {
    osoba.setKbr(kbr);
    return this;
  }



}
