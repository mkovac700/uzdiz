package org.foi.uzdiz.mkovac.zadaca_2.builder;

import org.foi.uzdiz.mkovac.zadaca_2.composite.MjestoComposite;

/**
 * The builder abstraction
 * 
 * @author Marijan Kovač
 *
 */
public interface MjestoBuilder {

  MjestoComposite build();

  MjestoBuilder setId(final int id);

  MjestoBuilder setNaziv(final String naziv);

}
