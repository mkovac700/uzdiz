package org.foi.uzdiz.mkovac.zadaca_3.state;

import org.foi.uzdiz.mkovac.zadaca_3.builder.Vozilo;

public interface StatusVoznjeState {

  void setUkrcavanje(Vozilo vozilo);

  void setIsporuka(Vozilo vozilo);

  void setPovratak(Vozilo vozilo);

  String getOznaka();
}
