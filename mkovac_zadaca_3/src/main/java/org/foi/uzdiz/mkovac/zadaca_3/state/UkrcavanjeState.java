package org.foi.uzdiz.mkovac.zadaca_3.state;

import org.foi.uzdiz.mkovac.zadaca_3.builder.Vozilo;

public class UkrcavanjeState implements StatusVoznjeState {

  private String oznaka = "UKRCAVANJE";

  public String getOznaka() {
    return oznaka;
  }

  @Override
  public void setUkrcavanje(Vozilo vozilo) {
    System.out
        .println("Vozilo " + vozilo.getRegistracija() + " je već u statusu vožnje " + this.oznaka);
  }

  @Override
  public void setIsporuka(Vozilo vozilo) {
    vozilo.setStatusVoznje(new IsporukaState());
    System.out.println("Vozilo " + vozilo.getRegistracija() + " promijenilo status vožnje u "
        + vozilo.getStatusVoznje().getOznaka());
  }

  @Override
  public void setPovratak(Vozilo vozilo) {
    StatusVoznjeState povratakState = new PovratakState();
    System.out
        .println("Vozilo " + vozilo.getRegistracija() + " ne može biti prebačeno u status vožnje "
            + povratakState.getOznaka() + " iz statusa vožnje " + this.oznaka);
  }

}
