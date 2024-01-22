package org.foi.uzdiz.mkovac.zadaca_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.foi.uzdiz.mkovac.zadaca_3.cor.KomandaHandler;
import org.foi.uzdiz.mkovac.zadaca_3.cor.KomandaIPHandler;
import org.foi.uzdiz.mkovac.zadaca_3.cor.KomandaPOHandler;
import org.foi.uzdiz.mkovac.zadaca_3.cor.KomandaPPHandler;
import org.foi.uzdiz.mkovac.zadaca_3.cor.KomandaPPVHandler;
import org.foi.uzdiz.mkovac.zadaca_3.cor.KomandaPSHandler;
import org.foi.uzdiz.mkovac.zadaca_3.cor.KomandaQHandler;
import org.foi.uzdiz.mkovac.zadaca_3.cor.KomandaSPVHandler;
import org.foi.uzdiz.mkovac.zadaca_3.cor.KomandaSVHandler;
import org.foi.uzdiz.mkovac.zadaca_3.cor.KomandaVRHandler;
import org.foi.uzdiz.mkovac.zadaca_3.cor.KomandaVSHandler;
import org.foi.uzdiz.mkovac.zadaca_3.cor.KomandaVVHandler;
import org.foi.uzdiz.mkovac.zadaca_3.iznimke.NeispravniParametri;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.RegexVrsta;
import org.foi.uzdiz.mkovac.zadaca_3.singleton.RegexProvjeraSingleton;
import org.foi.uzdiz.mkovac.zadaca_3.singleton.TvrtkaSingleton;

public class GlavniProgram {

  public static void main(String[] args) {

    RegexProvjeraSingleton regex = RegexProvjeraSingleton.getInstance();

    String argument = String.join(" ", args).trim();
    if (!regex.provjeriIzraz(argument, RegexVrsta.txtDatoteka)) {
      System.out.println("Neispravan argument!");
      return;
    }

    TvrtkaSingleton tvrtka = TvrtkaSingleton.getInstance();
    try {
      tvrtka.init(argument);
    } catch (IOException | NeispravniParametri e) {
      System.out.println("Neuspješna inicijalizacija sustava: " + e.getMessage());
      return;
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    Simulator simulator = new Simulator();

    KomandaHandler komandaHandlerChain =
        new KomandaIPHandler(new KomandaVRHandler(new KomandaSVHandler(new KomandaVVHandler(
            new KomandaVSHandler(new KomandaPPHandler(new KomandaPSHandler(new KomandaPOHandler(
                new KomandaSPVHandler(new KomandaPPVHandler(new KomandaQHandler(null)))))))))));

    while (!tvrtka.KRAJ) {// true
      String komanda = null;
      try {
        komanda = reader.readLine();
      } catch (IOException e) {
        System.out.println("Greška u čitanju primljene komande: " + e.getMessage());
        continue;
      }

      if (!regex.provjeriIzraz(komanda, RegexVrsta.komanda) || !komandaHandlerChain.handle(komanda))
        System.out.println("Nepoznata komanda!");

    }

    try {
      reader.close();
    } catch (IOException e) {
      System.out.println("Greška u zatvaranju čitača unosa: " + e.getMessage());
    }

  }

}
