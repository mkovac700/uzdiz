package org.foi.uzdiz.mkovac.zadaca_2.state;

import org.foi.uzdiz.mkovac.zadaca_2.builder.Vozilo;

public class PovratakState implements StatusVoznjeState {

  private String oznaka = "POVRATAK";

  public String getOznaka() {
    return oznaka;
  }

  @Override
  public void setUkrcavanje(Vozilo vozilo) {
    vozilo.setStatusVoznje(new UkrcavanjeState());
    System.out.println("Vozilo " + vozilo.getRegistracija() + " promijenilo status vožnje u "
        + vozilo.getStatusVoznje().getOznaka());
  }

  @Override
  public void setIsporuka(Vozilo vozilo) {
    StatusVoznjeState isporukaState = new IsporukaState();
    System.out
        .println("Vozilo " + vozilo.getRegistracija() + " ne može biti prebačeno u status vožnje "
            + isporukaState.getOznaka() + " iz statusa vožnje " + this.oznaka);
  }

  @Override
  public void setPovratak(Vozilo vozilo) {
    System.out
        .println("Vozilo " + vozilo.getRegistracija() + " je već u statusu vožnje " + this.oznaka);
  }

}
