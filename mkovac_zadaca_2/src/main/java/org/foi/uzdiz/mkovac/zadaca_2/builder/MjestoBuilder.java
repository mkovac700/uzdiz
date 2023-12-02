package org.foi.uzdiz.mkovac.zadaca_2.builder;

/**
 * The builder abstraction
 * 
 * @author Marijan Kovač
 *
 */
public interface MjestoBuilder {

  Mjesto build();

  MjestoBuilder setId(final int id);

  MjestoBuilder setNaziv(final String naziv);

  MjestoBuilder setUliceId(final int[] uliceId);

}
