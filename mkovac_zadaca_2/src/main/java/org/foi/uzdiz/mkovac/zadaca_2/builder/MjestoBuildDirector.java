package org.foi.uzdiz.mkovac.zadaca_2.builder;

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

  public Mjesto construct(int id, String naziv, int[] uliceId) {
    return builder.setId(id).setNaziv(naziv).setUliceId(uliceId).build();
  }

}
