package org.foi.uzdiz.mkovac.zadaca_1.builder;

/**
 * Concrete builder
 * 
 * @author Marijan Kovač
 *
 */
public class VoziloBuilderImpl implements VoziloBuilder {

  private Vozilo vozilo;

  public VoziloBuilderImpl() {
    vozilo = new Vozilo();
  }

  @Override
  public Vozilo build() {
    return vozilo;
  }

  @Override
  public VoziloBuilder setRegistracija(final String registracija) {
    vozilo.setRegistracija(registracija);
    return this;
  }

  @Override
  public VoziloBuilder setOpis(final String opis) {
    vozilo.setOpis(opis);
    return this;
  }

  @Override
  public VoziloBuilder setKapacitetTezine(final float kapacitetTezine) {
    vozilo.setKapacitetTezine(kapacitetTezine);
    return this;
  }

  @Override
  public VoziloBuilder setKapacitetProstora(final float kapacitetProstora) {
    vozilo.setKapacitetProstora(kapacitetProstora);
    return this;
  }

  @Override
  public VoziloBuilder setRedoslijed(final int redoslijed) {
    vozilo.setRedoslijed(redoslijed);
    return this;
  }

}
