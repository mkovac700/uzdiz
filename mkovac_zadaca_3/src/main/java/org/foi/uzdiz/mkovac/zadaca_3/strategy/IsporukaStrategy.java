package org.foi.uzdiz.mkovac.zadaca_3.strategy;

import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_3.builder.Paket;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.Voznja;

public interface IsporukaStrategy {

  void obaviIzracune(List<Paket> paketi, Voznja voznja);

}
