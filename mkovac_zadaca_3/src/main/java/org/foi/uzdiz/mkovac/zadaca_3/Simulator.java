package org.foi.uzdiz.mkovac.zadaca_3;

import java.time.LocalDateTime;
import org.foi.uzdiz.mkovac.zadaca_3.singleton.TvrtkaSingleton;

public class Simulator {
  private TvrtkaSingleton tvrtka = TvrtkaSingleton.getInstance();

  private LocalDateTime virtualniKraj;

  public Simulator() {}

  public void pokreni(int vrijemeIzvrsavanja) {
    virtualniKraj = tvrtka.virtualniSat;
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

      System.out.println("[" + tvrtka.getVirtualniSatFormatirano() + "]");

      if (tvrtka.virtualniSat.toLocalTime().isBefore(tvrtka.pocetakRada))
        continue;

      tvrtka.getUredPrijem().zaprimiPakete();

      if (tvrtka.virtualniSat.getHour() != tvrtka.virtualniSat.plusSeconds(tvrtka.mnoziteljSekunde)
          .getHour()) {
        System.out.println("Utovar paketa...");

        tvrtka.getUredPrijem().prebaciPaketeUUredZaDostavu();

        tvrtka.getUredDostava().utovariHitnePakete();

        tvrtka.getUredDostava().utovariOstalePakete();
      }
      tvrtka.getUredDostava().pokreniIsporuku();
    }
    odrediKrajRada();
  }

  private void odrediKrajRada() {
    if (tvrtka.virtualniSat.toLocalTime().equals(tvrtka.krajRada)
        || tvrtka.virtualniSat.toLocalTime().isAfter(tvrtka.krajRada)) {
      System.out.println("Kraj rada: isteklo radno vrijeme!");
    } else if (tvrtka.virtualniSat.equals(virtualniKraj)
        || tvrtka.virtualniSat.isAfter(virtualniKraj)) {
      System.out.println("Kraj rada: isteklo zadano vrijeme izvršavanja!");

      // TODO dodat provjeru jel postoje vozila koja su jos u dostavi te nastavit dostavljat ako
      // jesu
    } else {
      System.out.println("Kraj rada: neočekivan prekid!");
    }
  }
}
