package org.foi.uzdiz.mkovac.zadaca_2.builder;

import org.foi.uzdiz.mkovac.zadaca_2.composite.MjestoComposite;
import org.foi.uzdiz.mkovac.zadaca_2.composite.UlicaLeaf;

/**
 * The builder abstraction
 * 
 * @author Marijan Kovač
 *
 */
public interface OsobaBuilder {

  Osoba build();

  OsobaBuilder setOsoba(final String osoba);

  OsobaBuilder setGrad(final MjestoComposite grad);

  OsobaBuilder setUlica(final UlicaLeaf ulica);

  OsobaBuilder setKbr(final int kbr);

}
