package org.foi.uzdiz.mkovac.zadaca_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.foi.uzdiz.mkovac.zadaca_2.iznimke.NeispravniParametri;
import org.foi.uzdiz.mkovac.zadaca_2.podaci.RegexVrsta;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.RegexProvjeraSingleton;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.TvrtkaSingleton;

public class GlavniProgram {

  public static void main(String[] args) {

    RegexProvjeraSingleton regex = RegexProvjeraSingleton.getInstance();

    String argument = String.join(" ", args).trim();
    if (!regex.provjeriIzraz(argument, RegexVrsta.txtDatoteka)) {
      System.out.println("Neispravan argument!");
      return;
    }

    // TODO inicijalizacija tvrtke:
    TvrtkaSingleton tvrtka = TvrtkaSingleton.getInstance();
    try {
      tvrtka.init(argument);
    } catch (IOException | NeispravniParametri e) {
      System.out.println("Neuspješna inicijalizacija sustava: " + e.getMessage());
      return;
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    Simulator simulator = new Simulator();

    while (true) {
      String komanda = null;
      try {
        komanda = reader.readLine();
      } catch (IOException e) {
        System.out.println("Greška u čitanju primljene komande: " + e.getMessage());
        continue;
      }

      if (regex.provjeriIzraz(komanda, RegexVrsta.komanda)) {
        String[] grupa = regex.razdvojiIzraz(komanda, RegexVrsta.komanda);

        if (komanda.equals("IP")) {
          // TODO ispis paketa
        } else if (komanda.split(" ")[0].equals("VR")) {
          int vrijemeIzvrsavanja = Integer.parseInt(komanda.split(" ")[1]);
          simulator.pokreni(vrijemeIzvrsavanja);
        } else if (komanda.equals("PP")) {
          tvrtka.ispisPodrucja();
        } else if (komanda.split(" ")[0].equals("PO")) {
          tvrtka.promjeniStatusSlanjaObavijesti(grupa[8].replace("'", ""), grupa[9], grupa[10]);
        } else if (komanda.equals("Q")) {
          break;
        }
      } else
        System.out.println("Nepoznata komanda!");
    }

    try {
      reader.close();
    } catch (IOException e) {
      System.out.println("Greška u zatvaranju čitača unosa: " + e.getMessage());
    }

  }

}
