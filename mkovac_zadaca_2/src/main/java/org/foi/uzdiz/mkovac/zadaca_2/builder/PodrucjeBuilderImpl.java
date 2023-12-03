package org.foi.uzdiz.mkovac.zadaca_2.builder;

/**
 * Concrete builder
 * 
 * @author Marijan Kovač
 *
 */
public class PodrucjeBuilderImpl implements PodrucjeBuilder {

  private Podrucje podrucje;

  public PodrucjeBuilderImpl() {
    podrucje = new Podrucje();
  }

  @Override
  public Podrucje build() {
    return podrucje;
  }

  @Override
  public PodrucjeBuilder setId(int id) {
    podrucje.setId(id);
    return this;
  }

}
