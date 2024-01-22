package org.foi.uzdiz.mkovac.zadaca_3.memento;

import java.util.HashMap;
import java.util.Map;

public class TvrtkaCaretaker {
  private Map<String, TvrtkaMemento> mementoHistory = new HashMap<String, TvrtkaMemento>();

  public void addMemento(String naziv, TvrtkaMemento memento) {
    if (naziv != null && naziv.trim().length() != 0 && memento != null) {
      if (mementoHistory.get(naziv) == null) {
        mementoHistory.put(naziv, memento);
        System.out
            .println("Spremljeno trenutno stanje virtualnog sata, paketa i vozila pod nazivom '"
                + naziv + "'");
      } else {
        System.out.println("Već postoji spremljeno stanje pod nazivom '" + naziv + "'");
      }
    } else {
      System.out.println("Greška u spremanju stanja!");
    }
  }

  public TvrtkaMemento getMemento(String naziv) {
    TvrtkaMemento memento = null;

    if (naziv != null && naziv.trim().length() != 0) {
      memento = mementoHistory.get(naziv);
      if (memento != null) {
        System.out.println("Pronađeno spremljeno stanje pod nazivom '" + naziv + "'");
      } else {
        System.out.println("Nije pronađeno spremljeno stanje pod nazivom '" + naziv + "'");
      }
    }

    return memento;
  }
}
