package org.foi.uzdiz.mkovac.zadaca_2.strategy;

import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Paket;
import org.foi.uzdiz.mkovac.zadaca_2.podaci.Voznja;

public interface IsporukaStrategy {
  void obaviIzracune(List<Paket> paketi, Voznja voznja);
}
