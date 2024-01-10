package org.foi.uzdiz.mkovac.zadaca_3.builder;

import org.foi.uzdiz.mkovac.zadaca_3.composite.UlicaLeaf;

/**
 * The builder abstraction
 * 
 * @author Marijan Kovač
 *
 */
public interface UlicaBuilder {
  UlicaLeaf build();

  UlicaBuilder setId(final int id);

  UlicaBuilder setNaziv(final String naziv);

  UlicaBuilder setGpsLat1(final float gpsLat1);

  UlicaBuilder setGpsLon1(final float gpsLon1);

  UlicaBuilder setGpsLat2(final float gpsLat2);

  UlicaBuilder setGpsLon2(final float gpsLon2);

  UlicaBuilder setNajveciKucniBroj(final int najveciKucniBroj);

}
