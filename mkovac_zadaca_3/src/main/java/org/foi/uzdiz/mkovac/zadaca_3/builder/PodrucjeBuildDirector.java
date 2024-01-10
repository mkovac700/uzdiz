package org.foi.uzdiz.mkovac.zadaca_3.builder;

/**
 * Director
 * 
 * @author Marijan Kovač
 *
 */
public class PodrucjeBuildDirector {

  private PodrucjeBuilder builder;

  public PodrucjeBuildDirector(final PodrucjeBuilder builder) {
    this.builder = builder;
  }

  public Podrucje construct(int id) {
    return builder.setId(id).build();
  }

}
