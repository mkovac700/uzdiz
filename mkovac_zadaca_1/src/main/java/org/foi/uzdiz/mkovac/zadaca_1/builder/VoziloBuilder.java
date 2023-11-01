package org.foi.uzdiz.mkovac.zadaca_1.builder;

/**
 * The builder abstraction
 * 
 * @author Marijan Kovač
 *
 */
public interface VoziloBuilder {
  Vozilo build();

  VoziloBuilder setRegistracija(final String registracija);

  VoziloBuilder setOpis(final String opis);

  VoziloBuilder setKapacitetTezine(final float kapacitetTezine);

  VoziloBuilder setKapacitetProstora(final float kapacitetProstora);

  VoziloBuilder setRedoslijed(final int redoslijed);
}
