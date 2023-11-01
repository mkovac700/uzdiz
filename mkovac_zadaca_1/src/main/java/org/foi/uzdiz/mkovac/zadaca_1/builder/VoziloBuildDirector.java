package org.foi.uzdiz.mkovac.zadaca_1.builder;

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
      float kapacitetProstora, int redoslijed) {
    return builder.setRegistracija(registracija).setOpis(opis).setKapacitetTezine(kapacitetTezine)
        .setKapacitetProstora(kapacitetProstora).setRedoslijed(redoslijed).build();
  }

}
