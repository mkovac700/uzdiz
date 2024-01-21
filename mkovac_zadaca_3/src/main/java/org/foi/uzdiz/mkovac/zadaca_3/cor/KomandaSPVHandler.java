package org.foi.uzdiz.mkovac.zadaca_3.cor;

public class KomandaSPVHandler implements KomandaHandler {
  private KomandaHandler nextHandler;

  public KomandaSPVHandler(KomandaHandler nextHandler) {
    this.nextHandler = nextHandler;
  }

  @Override
  public boolean handle(String komanda) {
    String kljuc = komanda.split(" ")[0];

    if (kljuc.equals("SPV")) {
      // TODO
      return true;
    }

    if (nextHandler != null) {
      return nextHandler.handle(komanda);
    }

    return false;
  }

}
