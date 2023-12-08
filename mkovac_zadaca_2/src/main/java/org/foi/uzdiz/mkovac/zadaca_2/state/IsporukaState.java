package org.foi.uzdiz.mkovac.zadaca_2.state;

import org.foi.uzdiz.mkovac.zadaca_2.builder.Vozilo;

public class IsporukaState implements StatusVoznjeState {

  private String oznaka = "ISPORUKA";

  public String getOznaka() {
    return oznaka;
  }

  @Override
  public void setUkrcavanje(Vozilo vozilo) {
    StatusVoznjeState ukrcavanjeState = new UkrcavanjeState();
    System.out
        .println("Vozilo " + vozilo.getRegistracija() + " ne može biti prebačeno u status vožnje "
            + ukrcavanjeState.getOznaka() + " iz statusa vožnje " + this.oznaka);
  }

  @Override
  public void setIsporuka(Vozilo vozilo) {
    System.out
        .println("Vozilo " + vozilo.getRegistracija() + " je već u statusu vožnje " + this.oznaka);
  }

  @Override
  public void setPovratak(Vozilo vozilo) {
    vozilo.setStatusVoznje(new PovratakState());
    System.out.println("Vozilo " + vozilo.getRegistracija() + " promijenilo status vožnje u "
        + vozilo.getStatusVoznje().getOznaka());
  }

}
