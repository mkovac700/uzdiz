package org.foi.uzdiz.mkovac.zadaca_1.singleton;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_1.builder.Paket;

public class UredPrijemSingleton {
  private static volatile UredPrijemSingleton INSTANCE = new UredPrijemSingleton();

  // List<Paket>

  // naplatiDostavu()

  /**
   * Trajno ostaju radi statistike
   */
  private List<Paket> primljeniPaketi;

  /**
   * Privremeno dok se ne ukrcaju
   */
  private List<Paket> primljeniPaketiTmp;

  private UredPrijemSingleton() {
    primljeniPaketi = new ArrayList<Paket>();
    primljeniPaketiTmp = new ArrayList<Paket>();
  }

  public static UredPrijemSingleton getInstance() {
    return INSTANCE;
  }

  public List<Paket> getSviPrimljeniPaketi() {
    return primljeniPaketi;
  }

  public List<Paket> getPrimljeniPaketi() {
    return primljeniPaketiTmp;
  }

  public void zaprimiPaket(Paket paket) {
    primljeniPaketiTmp.add(paket);
    primljeniPaketi.add(paket);
  }


}
