package org.foi.uzdiz.mkovac.zadaca_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.foi.uzdiz.mkovac.zadaca_1.podaci.RegexVrsta;
import org.foi.uzdiz.mkovac.zadaca_1.singleton.RegexSingleton;
import org.foi.uzdiz.mkovac.zadaca_1.singleton.TvrtkaSingleton;

public class GlavniProgram {

  public static void main(String[] args) throws IOException {

    String argumenti = String.join(" ", args).trim();
    if (!RegexSingleton.getInstance().provjeriIzraz(argumenti, RegexVrsta.argumenti)) {
      System.out.println("Neispravni argumenti!");
      return;
    }

    TvrtkaSingleton tvrtka = TvrtkaSingleton.getInstance();
    tvrtka.init(argumenti);

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    VirtualniWorker vw = null;

    while (true) {
      String komanda = reader.readLine();
      if (RegexSingleton.getInstance().provjeriIzraz(komanda, RegexVrsta.komanda)) {
        if (komanda.equals("IP")) {

        } else if (komanda.split(" ")[0].equals("VR")) {

          tvrtka.vrijemeIzvrsavanja = Integer.parseInt(komanda.split(" ")[1]);

          vw = new VirtualniWorker();
          vw.start();
        } else if (komanda.equals("Q")) {

          if (vw != null && vw.isAlive())
            vw.interrupt();

          break;
        }
      } else
        System.out.println("Nepoznata komanda!");
    }

    reader.close();
  }

}
