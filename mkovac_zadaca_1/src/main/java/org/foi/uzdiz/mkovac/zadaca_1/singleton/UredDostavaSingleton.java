package org.foi.uzdiz.mkovac.zadaca_1.singleton;

import java.util.Collections;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_1.builder.Vozilo;

public class UredDostavaSingleton {
  private static volatile UredDostavaSingleton INSTANCE = new UredDostavaSingleton();

  // VozniPark
  // unutar njega Vozila
  // unutar toga List<Paket>

  private List<Vozilo> vozniPark;

  private Vozilo trenutnoVozilo;

  private UredDostavaSingleton() {}

  public static UredDostavaSingleton getInstance() {
    return INSTANCE;

  }

  public List<Vozilo> getVozniPark() {
    return vozniPark;
  }

  public void setVozniPark(List<Vozilo> vozniPark) {
    this.vozniPark = vozniPark;
    Collections.sort(this.vozniPark,
        (a, b) -> Integer.compare(a.getRedoslijed(), b.getRedoslijed()));
    trenutnoVozilo = this.vozniPark.get(0);
  }

  public Vozilo getTrenutnoVozilo() {
    return trenutnoVozilo;
  }

  public void setTrenutnoVozilo(Vozilo trenutnoVozilo) {
    this.trenutnoVozilo = trenutnoVozilo;
  }



}
