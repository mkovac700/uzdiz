package org.foi.uzdiz.mkovac.zadaca_2.builder;

import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_2.state.StatusVozilaState;

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

  @Override
  public VoziloBuilder setProsjecnaBrzina(float prosjecnaBrzina) {
    vozilo.setProsjecnaBrzina(prosjecnaBrzina);
    return this;
  }

  @Override
  public VoziloBuilder setPodrucjaPoRangu(List<Podrucje> podrucjaPoRangu) {
    vozilo.setPodrucjaPoRangu(podrucjaPoRangu);
    return this;
  }

  @Override
  public VoziloBuilder setStatus(StatusVozilaState status) {
    vozilo.setStatus(status);
    return this;
  }

}
