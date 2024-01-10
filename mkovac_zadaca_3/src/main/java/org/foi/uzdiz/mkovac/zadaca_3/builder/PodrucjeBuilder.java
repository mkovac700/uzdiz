package org.foi.uzdiz.mkovac.zadaca_3.builder;

/**
 * The builder abstraction
 * 
 * @author Marijan Kovač
 *
 */
public interface PodrucjeBuilder {

  Podrucje build();

  PodrucjeBuilder setId(final int id);
}
