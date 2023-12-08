package org.foi.uzdiz.mkovac.zadaca_2.state;

import org.foi.uzdiz.mkovac.zadaca_2.builder.Vozilo;

public interface StatusVoznjeState {

  void setUkrcavanje(Vozilo vozilo);

  void setIsporuka(Vozilo vozilo);

  void setPovratak(Vozilo vozilo);

  String getOznaka();
}
