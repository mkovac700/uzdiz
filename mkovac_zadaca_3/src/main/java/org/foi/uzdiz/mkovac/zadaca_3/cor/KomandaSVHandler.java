package org.foi.uzdiz.mkovac.zadaca_3.cor;

import org.foi.uzdiz.mkovac.zadaca_3.singleton.TvrtkaSingleton;

public class KomandaSVHandler implements KomandaHandler {
  private KomandaHandler nextHandler;

  public KomandaSVHandler(KomandaHandler nextHandler) {
    this.nextHandler = nextHandler;
  }

  @Override
  public boolean handle(String komanda) {
    String kljuc = komanda.split(" ")[0];

    if (kljuc.equals("SV")) {
      TvrtkaSingleton.getInstance().ispisiVozila();
      return true;
    }

    if (nextHandler != null) {
      return nextHandler.handle(komanda);
    }

    return false;
  }

}
