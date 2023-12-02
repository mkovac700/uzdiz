package org.foi.uzdiz.mkovac.zadaca_2.builder;

import org.foi.uzdiz.mkovac.zadaca_2.composite.Mjesto;

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

  // public Mjesto construct(int id, String naziv, int[] uliceId) {
  // return builder.setId(id).setNaziv(naziv).setUliceId(uliceId).build();
  // }

  public Mjesto construct(int id, String naziv) {
    return builder.setId(id).setNaziv(naziv).build();
  }

}
