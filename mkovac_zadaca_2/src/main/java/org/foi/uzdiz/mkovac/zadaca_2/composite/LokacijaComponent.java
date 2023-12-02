package org.foi.uzdiz.mkovac.zadaca_2.composite;

import java.util.ArrayList;

/**
 * Component
 * 
 * @author Marijan Kovač
 *
 */
public interface LokacijaComponent {
  public String prikaziDetalje();

  public default void dodajLokaciju(LokacijaComponent lokacija) {
    throw new UnsupportedOperationException("Metoda nije podržana za list!");
  }

  public default ArrayList<LokacijaComponent> dajLokacije() {
    throw new UnsupportedOperationException("Metoda nije podržana za list!");
  }
}
