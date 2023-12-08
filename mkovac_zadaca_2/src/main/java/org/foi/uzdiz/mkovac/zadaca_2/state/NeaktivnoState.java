package org.foi.uzdiz.mkovac.zadaca_2.state;

import org.foi.uzdiz.mkovac.zadaca_2.builder.Vozilo;

public class NeaktivnoState implements StatusVozilaState {

  private String oznaka = "NA";

  public String getOznaka() {
    return oznaka;
  }

  public void setOznaka(String oznaka) {
    this.oznaka = oznaka;
  }

  @Override
  public void aktiviraj(Vozilo vozilo) {
    System.out.println(
        "Aktiviranje vozila " + vozilo.getOpis() + " (" + vozilo.getRegistracija() + ")...");

    vozilo.setStatus(new AktivnoState());
  }

  @Override
  public void deaktiviraj(Vozilo vozilo) {
    System.out.println(
        "Vozilo " + vozilo.getOpis() + " (" + vozilo.getRegistracija() + ") je već neaktivno.");
  }

  @Override
  public void setNeispravno(Vozilo vozilo) {
    System.out.println("Postavljanje vozila " + vozilo.getOpis() + " (" + vozilo.getRegistracija()
        + ") u neispravno stanje...");

    vozilo.setStatus(new NeispravnoState());
  }

}
