package org.foi.uzdiz.mkovac.zadaca_2;

import java.io.IOException;
import org.foi.uzdiz.mkovac.zadaca_2.iznimke.NeispravniParametri;
import org.foi.uzdiz.mkovac.zadaca_2.podaci.RegexVrsta;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.RegexProvjeraSingleton;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.TvrtkaSingleton;

public class GlavniProgram {

  public static void main(String[] args) {
    String argument = String.join(" ", args).trim();
    if (!RegexProvjeraSingleton.getInstance().provjeriIzraz(argument, RegexVrsta.txtDatoteka)) {
      System.out.println("Neispravan argument!");
      return;
    }

    // inicijalizacija tvrtke:
    TvrtkaSingleton tvrtka = TvrtkaSingleton.getInstance();
    try {
      tvrtka.init(argument);
    } catch (IOException | NeispravniParametri e) {
      System.out.println("Neuspješna inicijalizacija sustava: " + e.getMessage());
      return;
    }
  }

}
