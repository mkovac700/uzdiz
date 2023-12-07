package org.foi.uzdiz.mkovac.zadaca_2.state;

import org.foi.uzdiz.mkovac.zadaca_2.builder.Vozilo;

public class AktivnoState implements State {

  private String oznaka = "A";

  public String getOznaka() {
    return oznaka;
  }

  public void setOznaka(String oznaka) {
    this.oznaka = oznaka;
  }

  @Override
  public void aktiviraj(Vozilo vozilo) {

    System.out.println(
        "Vozilo " + vozilo.getOpis() + " (" + vozilo.getRegistracija() + ") je već aktivno.");
  }

  @Override
  public void deaktiviraj(Vozilo vozilo) {
    System.out.println(
        "Deaktiviranje vozila " + vozilo.getOpis() + " (" + vozilo.getRegistracija() + ")...");

    vozilo.setStatus(new NeaktivnoState());
  }

  @Override
  public void setNeispravno(Vozilo vozilo) {
    System.out.println("Postavljanje vozila " + vozilo.getOpis() + " (" + vozilo.getRegistracija()
        + ") u neispravno stanje...");

    vozilo.setStatus(new NeispravnoState());
  }

}
