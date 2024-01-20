package org.foi.uzdiz.mkovac.zadaca_3.cor;

import org.foi.uzdiz.mkovac.zadaca_3.singleton.TvrtkaSingleton;

public class KomandaIPHandler implements KomandaHandler {
  private KomandaHandler nextHandler;

  public KomandaIPHandler(KomandaHandler nextHandler) {
    this.nextHandler = nextHandler;
  }

  @Override
  public boolean handle(String komanda) {
    String kljuc = komanda.split(" ")[0];

    if (kljuc.equals("IP")) {
      TvrtkaSingleton.getInstance().ispisPaketa();
      return true;
    }

    if (nextHandler != null) {
      return nextHandler.handle(komanda);
    }

    return false;
  }

}
