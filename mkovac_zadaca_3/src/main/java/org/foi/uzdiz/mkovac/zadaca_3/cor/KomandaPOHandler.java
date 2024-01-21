package org.foi.uzdiz.mkovac.zadaca_3.cor;

import org.foi.uzdiz.mkovac.zadaca_3.podaci.RegexVrsta;
import org.foi.uzdiz.mkovac.zadaca_3.singleton.RegexProvjeraSingleton;
import org.foi.uzdiz.mkovac.zadaca_3.singleton.TvrtkaSingleton;

public class KomandaPOHandler implements KomandaHandler {
  private KomandaHandler nextHandler;

  public KomandaPOHandler(KomandaHandler nextHandler) {
    this.nextHandler = nextHandler;
  }

  @Override
  public boolean handle(String komanda) {
    String kljuc = komanda.split(" ")[0];
    String[] grupa =
        RegexProvjeraSingleton.getInstance().razdvojiIzraz(komanda, RegexVrsta.komanda);

    if (kljuc.equals("PO")) {
      TvrtkaSingleton.getInstance().promjeniStatusSlanjaObavijesti(grupa[8].replace("'", ""),
          grupa[9], grupa[10]);
      return true;
    }

    if (nextHandler != null) {
      return nextHandler.handle(komanda);
    }

    return false;
  }

}
