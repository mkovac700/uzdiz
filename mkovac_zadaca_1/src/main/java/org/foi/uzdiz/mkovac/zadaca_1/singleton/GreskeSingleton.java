package org.foi.uzdiz.mkovac.zadaca_1.singleton;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_1.podaci.Greska;

public class GreskeSingleton {
  private static volatile GreskeSingleton INSTANCE = new GreskeSingleton();

  private List<Greska> greske;

  private GreskeSingleton() {
    greske = new ArrayList<>();
  }

  public static GreskeSingleton getInstance() {
    return INSTANCE;
  }

  /**
   * Dodaje novi zapis greške u listu i vraća dodani zapis kako bi se moglo ispisati
   * 
   * @param zapis
   * @param opis
   * @return Greska
   */
  public Greska dodajGresku(String zapis, String opis) {
    Greska greska = new Greska(greske.size() + 1, zapis, opis);
    greske.add(greska);

    return greska;
  }



}
