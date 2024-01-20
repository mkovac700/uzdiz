package org.foi.uzdiz.mkovac.zadaca_3.cor;

import org.foi.uzdiz.mkovac.zadaca_3.Simulator;

public class KomandaVRHandler implements KomandaHandler {
  private KomandaHandler nextHandler;

  public KomandaVRHandler(KomandaHandler nextHandler) {
    this.nextHandler = nextHandler;
  }

  @Override
  public boolean handle(String komanda) {
    String kljuc = komanda.split(" ")[0];

    if (kljuc.equals("VR")) {
      int vrijemeIzvrsavanja = 0;
      try {
        vrijemeIzvrsavanja = Integer.parseInt(komanda.split(" ")[1]);
      } catch (NumberFormatException e) {
        System.out.println(e.getMessage());
      }
      Simulator simulator = new Simulator();
      simulator.pokreni(vrijemeIzvrsavanja);

      return true;
    }

    if (nextHandler != null) {
      return nextHandler.handle(komanda);
    }

    return false;
  }

}
