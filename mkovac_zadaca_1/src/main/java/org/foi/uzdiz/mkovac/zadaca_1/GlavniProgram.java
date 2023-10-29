package org.foi.uzdiz.mkovac.zadaca_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.foi.uzdiz.mkovac.zadaca_1.pomocnici.RegexVrsta;
import org.foi.uzdiz.mkovac.zadaca_1.singleton.RegexSingleton;
import org.foi.uzdiz.mkovac.zadaca_1.singleton.TvrtkaSingleton;

public class GlavniProgram {

  public static void main(String[] args) throws IOException {

    String argumenti = String.join(" ", args).trim();
    if (!RegexSingleton.getInstance().provjeriIzraz(argumenti, RegexVrsta.argumenti)) {
      System.out.println("neispravni argumenti");
      return;
    }

    // GlavniProgram gp = new GlavniProgram();

    // TODO instanciraj tvrtku

    TvrtkaSingleton tvrtka = TvrtkaSingleton.getInstance();
    tvrtka.init(argumenti);

    // pokreni program

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      String komanda = reader.readLine();
      if (RegexSingleton.getInstance().provjeriIzraz(komanda, RegexVrsta.komanda))
        System.out.println("Unijeli ste " + komanda);
      else
        System.out.println("Nepoznata komanda!");
    }
  }

  // private boolean provjeriIzraz(String izraz, RegexVrsta vrsta) {
  // Matcher matcher = RegexSingleton.getInstance().dajPatternKomanda().matcher(izraz);
  // return matcher.matches();
  // }

}
