package org.foi.uzdiz.mkovac.zadaca_2.builder;

import org.foi.uzdiz.mkovac.zadaca_2.composite.MjestoComposite;

/**
 * Director
 * 
 * @author Marijan Kovač
 *
 */
public class MjestoBuildDirector {

  private MjestoBuilder builder;

  public MjestoBuildDirector(final MjestoBuilder builder) {
    this.builder = builder;
  }

  public MjestoComposite construct(int id, String naziv) {
    return builder.setId(id).setNaziv(naziv).build();
  }

}
