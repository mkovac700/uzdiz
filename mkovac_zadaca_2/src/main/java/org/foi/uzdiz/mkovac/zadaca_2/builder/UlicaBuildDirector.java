package org.foi.uzdiz.mkovac.zadaca_2.builder;

import org.foi.uzdiz.mkovac.zadaca_2.composite.UlicaLeaf;

/**
 * Director
 * 
 * @author Marijan Kovač
 *
 */
public class UlicaBuildDirector {

  private UlicaBuilder builder;

  public UlicaBuildDirector(final UlicaBuilder builder) {
    this.builder = builder;
  }

  public UlicaLeaf construct(int id, String naziv, float gpsLat1, float gpsLon1, float gpsLat2,
      float gpsLon2, int najveciKucniBroj) {
    return builder.setId(id).setNaziv(naziv).setGpsLat1(gpsLat1).setGpsLon1(gpsLon1)
        .setGpsLat2(gpsLat2).setGpsLon2(gpsLon2).setNajveciKucniBroj(najveciKucniBroj).build();
  }

}
