package org.foi.uzdiz.mkovac.zadaca_2.state;

import org.foi.uzdiz.mkovac.zadaca_2.builder.Vozilo;

public interface StatusVozilaState {
  void aktiviraj(Vozilo vozilo);

  void deaktiviraj(Vozilo vozilo);

  void setNeispravno(Vozilo vozilo);

  String getOznaka();
}
