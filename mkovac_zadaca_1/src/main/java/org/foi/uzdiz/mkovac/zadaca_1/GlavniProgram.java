package org.foi.uzdiz.mkovac.zadaca_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;

public class GlavniProgram {

  public static void main(String[] args) throws IOException {

    GlavniProgram gp = new GlavniProgram();

    // TODO instanciraj tvrtku

    // pokreni program

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      String komanda = reader.readLine();
      if (gp.provjeriIzraz(komanda))
        System.out.println("Unijeli ste " + komanda);
      else
        System.out.println("Nepoznata komanda!");
    }
  }

  private boolean provjeriIzraz(String izraz) {
    Matcher matcher = RegexSingleton.getInstance().dajPatternKomanda().matcher(izraz);
    return matcher.matches();
  }

}
