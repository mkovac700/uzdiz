package org.foi.uzdiz.mkovac.zadaca_2.builder;

import java.util.List;

/**
 * Director
 * 
 * @author Marijan Kovač
 *
 */
public class VoziloBuildDirector {

  private VoziloBuilder builder;

  public VoziloBuildDirector(final VoziloBuilder builder) {
    this.builder = builder;
  }

  public Vozilo construct(String registracija, String opis, float kapacitetTezine,
      float kapacitetProstora, int redoslijed, float prosjecnaBrzina,
      List<Podrucje> podrucjaPoRangu, String status) {
    return builder.setRegistracija(registracija).setOpis(opis).setKapacitetTezine(kapacitetTezine)
        .setKapacitetProstora(kapacitetProstora).setRedoslijed(redoslijed)
        .setProsjecnaBrzina(prosjecnaBrzina).setPodrucjaPoRangu(podrucjaPoRangu).setStatus(status)
        .build();
  }

}
