package org.foi.uzdiz.mkovac.zadaca_2;

import java.time.LocalDateTime;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.TvrtkaSingleton;

public class Simulator {
  private TvrtkaSingleton tvrtka = TvrtkaSingleton.getInstance();

  public Simulator() {}

  public void pokreni(int vrijemeIzvrsavanja) {
    LocalDateTime virtualniKraj = tvrtka.virtualniSat;
    virtualniKraj = virtualniKraj.plusHours(vrijemeIzvrsavanja);

    while (tvrtka.virtualniSat.toLocalTime().isBefore(tvrtka.krajRada)
        && tvrtka.virtualniSat.isBefore(virtualniKraj)) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        System.out.println("Spavanje prekinuto: " + e.getMessage());
        break;
      }

      tvrtka.virtualniSat = tvrtka.virtualniSat.plusSeconds(tvrtka.mnoziteljSekunde);

      System.out.println("[" + tvrtka.getVirtualniSatFormatirano() + "]" + " kao nešto radim...");
    }

    // TODO odredi kraj rada:
    if (tvrtka.virtualniSat.toLocalTime().equals(tvrtka.krajRada)
        || tvrtka.virtualniSat.toLocalTime().isAfter(tvrtka.krajRada)) {
      System.out.println("Kraj rada: isteklo radno vrijeme!");
    } else if (tvrtka.virtualniSat.equals(virtualniKraj)
        || tvrtka.virtualniSat.isAfter(virtualniKraj)) {
      System.out.println("Kraj rada: isteklo zadano vrijeme izvršavanja!");
    } else {
      System.out.println("Kraj rada: neočekivan prekid!");
    }

  }
}
