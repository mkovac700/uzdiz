package org.foi.uzdiz.mkovac.zadaca_2.builder;

import java.util.List;

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

  VoziloBuilder setProsjecnaBrzina(final float prosjecnaBrzina);

  VoziloBuilder setPodrucjaPoRangu(final List<Podrucje> podrucjaPoRangu);

  VoziloBuilder setStatus(final String status);
}
