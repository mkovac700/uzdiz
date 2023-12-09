package org.foi.uzdiz.mkovac.zadaca_2;

import java.time.LocalDateTime;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.TvrtkaSingleton;

public class Simulator {
  private TvrtkaSingleton tvrtka = TvrtkaSingleton.getInstance();

  private LocalDateTime virtualniKraj;

  // private static int brojZaprimljenihPaketa;

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

      // TODO dodati ogranicenje ako je virtualni sat prije pocetka radnog vremena (pr)
      if (tvrtka.virtualniSat.toLocalTime().isBefore(tvrtka.pocetakRada))
        continue;

      // TODO prebaciti metodu u tvrtka.uredprijem
      tvrtka.getUredPrijem().zaprimiPakete();

      // TODO prije nego otkuca puni sat, odraditi postupak utovara

      if (tvrtka.virtualniSat.getHour() != tvrtka.virtualniSat.plusSeconds(tvrtka.mnoziteljSekunde)
          .getHour()) {
        System.out.println("Utovar paketa...");

        // preseli pakete u ured za dostavu
        tvrtka.getUredPrijem().prebaciPaketeUUredZaDostavu();

        // utovari hitne
        tvrtka.getUredDostava().utovariHitnePakete();

        // utovari ostale
        tvrtka.getUredDostava().utovariOstalePakete();

        // sada kreće dostava
        tvrtka.getUredDostava().pokreniIsporuku();

      }


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
