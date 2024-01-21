package org.foi.uzdiz.mkovac.zadaca_3.cor;

import org.foi.uzdiz.mkovac.zadaca_3.decorator.IspisPaketa;
import org.foi.uzdiz.mkovac.zadaca_3.decorator.IspisPaketaImpl;
import org.foi.uzdiz.mkovac.zadaca_3.decorator.IspisPrometaPaketa;

public class KomandaIPHandler implements KomandaHandler {
  private KomandaHandler nextHandler;

  public KomandaIPHandler(KomandaHandler nextHandler) {
    this.nextHandler = nextHandler;
  }

  @Override
  public boolean handle(String komanda) {
    String[] komandaDijelovi = komanda.split(" ");
    String kljuc = komandaDijelovi[0];

    if (kljuc.equals("IP")) {
      IspisPaketa ispisPaketa;

      if (komandaDijelovi.length == 2 && komandaDijelovi[1].equals("P")) {
        ispisPaketa = new IspisPrometaPaketa(new IspisPaketaImpl());
        ispisPaketa.ispisi();
        return true;
      } else if (komandaDijelovi.length == 1) {
        ispisPaketa = new IspisPaketaImpl();
        ispisPaketa.ispisi();
        return true;
      } else
        return false;
    }

    if (nextHandler != null) {
      return nextHandler.handle(komanda);
    }

    return false;
  }

}
