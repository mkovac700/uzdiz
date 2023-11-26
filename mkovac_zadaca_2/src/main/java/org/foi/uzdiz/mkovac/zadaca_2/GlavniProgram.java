package org.foi.uzdiz.mkovac.zadaca_2;

import org.foi.uzdiz.mkovac.zadaca_2.podaci.RegexVrsta;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.RegexProvjeraSingleton;

public class GlavniProgram {

  public static void main(String[] args) {
    String argumenti = String.join(" ", args).trim();
    if (!RegexProvjeraSingleton.getInstance().provjeriIzraz(argumenti, RegexVrsta.txtDatoteka)) {
      System.out.println("Neispravan argument!");
      return;
    }
    System.out.println("ok");
  }

}
