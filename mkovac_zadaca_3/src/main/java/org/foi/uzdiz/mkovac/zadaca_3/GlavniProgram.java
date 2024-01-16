package org.foi.uzdiz.mkovac.zadaca_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        String kljuc = komanda.split(" ")[0];

        if (kljuc.equals("IP")) {

        } else if (kljuc.equals("VR")) {
          int vrijemeIzvrsavanja = Integer.parseInt(komanda.split(" ")[1]);
          simulator.pokreni(vrijemeIzvrsavanja);
        } else if (kljuc.equals("SV")) {

        } else if (kljuc.equals("VV")) {

        } else if (kljuc.equals("VS")) {
          tvrtka.ispisiSegmente(grupa[20], grupa[21]);
        } else if (kljuc.equals("PP")) {
          tvrtka.ispisPodrucja();
        } else if (kljuc.equals("PO")) {
          tvrtka.promjeniStatusSlanjaObavijesti(grupa[7].replace("'", ""), grupa[8], grupa[9]);
        } else if (kljuc.equals("PS")) {
          tvrtka.promijeniStatusVozila(grupa[12], grupa[13]);
        } else if (kljuc.equals("Q")) {
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
