package org.foi.uzdiz.mkovac.zadaca_3.builder;

import org.foi.uzdiz.mkovac.zadaca_3.composite.MjestoComposite;
import org.foi.uzdiz.mkovac.zadaca_3.composite.UlicaLeaf;

/**
 * Director
 * 
 * @author Marijan Kovač
 *
 */
public class OsobaBuildDirector {

  private OsobaBuilder builder;

  public OsobaBuildDirector(final OsobaBuilder builder) {
    this.builder = builder;
  }

  public Osoba construct(String osoba, MjestoComposite grad, UlicaLeaf ulica, int kbr) {
    return builder.setOsoba(osoba).setGrad(grad).setUlica(ulica).setKbr(kbr).build();
  }

}
