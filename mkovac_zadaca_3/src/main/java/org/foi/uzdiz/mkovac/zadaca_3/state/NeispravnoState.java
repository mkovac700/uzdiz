package org.foi.uzdiz.mkovac.zadaca_3.state;

import org.foi.uzdiz.mkovac.zadaca_3.builder.Vozilo;

public class NeispravnoState implements StatusVozilaState {

  private String oznaka = "NI";

  public String getOznaka() {
    return oznaka;
  }

  public void setOznaka(String oznaka) {
    this.oznaka = oznaka;
  }

  @Override
  public void aktiviraj(Vozilo vozilo) {
    System.out.println("Neispravno vozilo " + vozilo.getOpis() + " (" + vozilo.getRegistracija()
        + ") ne može biti aktivirano.");
  }

  @Override
  public void deaktiviraj(Vozilo vozilo) {
    System.out.println("Neispravno vozilo " + vozilo.getOpis() + " (" + vozilo.getRegistracija()
        + ") ne može biti deaktivirano.");
  }

  @Override
  public void setNeispravno(Vozilo vozilo) {
    System.out.println(
        "Vozilo " + vozilo.getOpis() + " (" + vozilo.getRegistracija() + ") je već neispravno.");
  }

}
