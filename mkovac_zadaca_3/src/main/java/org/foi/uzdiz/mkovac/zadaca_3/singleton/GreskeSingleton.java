package org.foi.uzdiz.mkovac.zadaca_3.singleton;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.Greska;

public class GreskeSingleton {
  private static volatile GreskeSingleton INSTANCE = new GreskeSingleton();

  private List<Greska> greske;

  private GreskeSingleton() {
    greske = new ArrayList<>();
  }

  public static GreskeSingleton getInstance() {
    return INSTANCE;
  }

  public Greska novaGreska(String zapis, String opis) {
    Greska greska = new Greska(greske.size() + 1, zapis, opis);
    greske.add(greska);

    return greska;
  }



}
