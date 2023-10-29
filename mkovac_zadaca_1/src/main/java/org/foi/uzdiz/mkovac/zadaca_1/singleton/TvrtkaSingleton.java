package org.foi.uzdiz.mkovac.zadaca_1.singleton;

import org.foi.uzdiz.mkovac.zadaca_1.pomocnici.RegexVrsta;

public class TvrtkaSingleton {
  private static volatile TvrtkaSingleton INSTANCE = new TvrtkaSingleton();

  private TvrtkaSingleton() {}

  public static TvrtkaSingleton getInstance() {
    return INSTANCE;
  }

  public void init(String argumenti) {
    String postavke[] = RegexSingleton.getInstance().razdvojiIzraz(argumenti, RegexVrsta.argumenti);

    for (int i = 0; i < postavke.length; i++) {
      System.out.println(i + ": " + postavke[i]);
    }
  }
}
