package org.foi.uzdiz.mkovac.zadaca_2;

import java.time.LocalDateTime;
import java.util.Iterator;
import org.foi.uzdiz.mkovac.zadaca_2.builder.Paket;
import org.foi.uzdiz.mkovac.zadaca_2.singleton.TvrtkaSingleton;

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

      // TODO dodati ogranicenje ako je virtualni sat prije pocetka radnog vremena (pr)
      if (tvrtka.virtualniSat.toLocalTime().isBefore(tvrtka.pocetakRada))
        continue;

      zaprimiPakete();


    }

    odrediKrajRada();

  }

  private void zaprimiPakete() {
    Iterator<Paket> itr = tvrtka.getPaketi().iterator();

    while (itr.hasNext()) {
      Paket p = itr.next();
      if (p.getVrijemePrijema().isBefore(tvrtka.virtualniSat)) {
        tvrtka.getUredPrijem().zaprimiPaket(p);
        itr.remove();
      }
    }
  }

  private void odrediKrajRada() {
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
