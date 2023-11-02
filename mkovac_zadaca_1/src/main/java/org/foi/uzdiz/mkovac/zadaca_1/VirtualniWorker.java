package org.foi.uzdiz.mkovac.zadaca_1;

import java.time.LocalDateTime;
import java.util.Iterator;
import org.foi.uzdiz.mkovac.zadaca_1.builder.Paket;
import org.foi.uzdiz.mkovac.zadaca_1.builder.Vozilo;
import org.foi.uzdiz.mkovac.zadaca_1.singleton.TvrtkaSingleton;

public class VirtualniWorker extends Thread {

  private TvrtkaSingleton tvrtka = TvrtkaSingleton.getInstance();

  public VirtualniWorker() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public synchronized void start() {
    super.start();
  }

  @Override
  public void run() {
    LocalDateTime virtualniKraj = tvrtka.virtualniSat;
    virtualniKraj = virtualniKraj.plusHours(tvrtka.vrijemeIzvrsavanja);

    while (tvrtka.virtualniSat.toLocalTime().isBefore(tvrtka.krajRada)
        && tvrtka.virtualniSat.isBefore(virtualniKraj)) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      tvrtka.virtualniSat = tvrtka.virtualniSat.plusSeconds(tvrtka.mnoziteljSekunde);

      Iterator<Paket> itr = tvrtka.paketi.iterator();

      while (itr.hasNext()) {
        Paket p = itr.next();
        if (p.getVrijemePrijema().isBefore(tvrtka.virtualniSat)) {
          tvrtka.uredPrijem.zaprimiPaket(p);
          itr.remove();
        }
      }

      // ako je prošao puni sat:
      if (tvrtka.virtualniSat.getMinute() == 0 || tvrtka.virtualniSat
          .getHour() != tvrtka.virtualniSat.minusSeconds(tvrtka.mnoziteljSekunde).getHour()) {

        for (Vozilo v : tvrtka.uredDostava.getVozniPark()) {
          for (Paket p : v.getPaketi()) {
            if (p.getUslugaDostave().equals("H")) {

              v.posaljiUDostavu();
              odaberiVozilo();
              break;
            }
          }

          if (v.getKapacitetProstoraTrenutno() / v.getKapacitetProstora() >= 0.5
              || v.getKapacitetTezineTrenutno() / v.getKapacitetTezine() >= 0.5) {

            odaberiVozilo();
            v.posaljiUDostavu();
          }
        }
      }

      // ukrcavanje paketa:
      if (!tvrtka.uredPrijem.getPrimljeniPaketi().isEmpty()) {

        Iterator<Paket> itr2 = tvrtka.uredPrijem.getPrimljeniPaketi().iterator();

        // prvo hitni
        while (itr2.hasNext()) {
          Paket p = itr2.next();
          if (p.getUslugaDostave().equals("H")) {
            while (!tvrtka.uredDostava.getTrenutnoVozilo().dodajPaket(p)) {
              odaberiVozilo();
            }
            itr2.remove();
          }
        }

        Iterator<Paket> itr3 = tvrtka.uredPrijem.getPrimljeniPaketi().iterator();
        // onda ostalo
        while (itr3.hasNext()) {
          Paket p = itr3.next();
          while (!tvrtka.uredDostava.getTrenutnoVozilo().dodajPaket(p)) {
            odaberiVozilo();
          }
          itr3.remove();
        }
      }

      // iskrcavanje paketa:
      for (Vozilo v : tvrtka.uredDostava.getVozniPark()) {
        if (!v.isSlobodno()) {

          Iterator<Paket> itr3 = v.getPaketi().iterator();

          while (itr3.hasNext()) {
            Paket p = itr3.next();

            if ((p.getVrijemePreuzimanja().isBefore(tvrtka.virtualniSat)
                || p.getVrijemePreuzimanja().isEqual(tvrtka.virtualniSat))
                && (p.getVrijemePreuzimanja()
                    .isAfter(tvrtka.virtualniSat.minusSeconds(tvrtka.mnoziteljSekunde))
                    || p.getVrijemePreuzimanja()
                        .isEqual(tvrtka.virtualniSat.minusSeconds(tvrtka.mnoziteljSekunde)))) {

              System.out.println("[" + p.getVrijemePreuzimanja() + "]" + " Dostavljen paket: " + p);

              itr3.remove();
            }
          }

          if (v.getPaketi().isEmpty())
            v.setSlobodno(true);
        }
      }
    }

    // odredi kraj rada:
    if (tvrtka.virtualniSat.toLocalTime().equals(tvrtka.krajRada)
        || tvrtka.virtualniSat.toLocalTime().isAfter(tvrtka.krajRada)) {
      System.out.println("Kraj rada: isteklo radno vrijeme!");
    }

    if (tvrtka.virtualniSat.equals(virtualniKraj) || tvrtka.virtualniSat.isAfter(virtualniKraj)) {
      System.out.println("Kraj rada: isteklo zadano vrijeme izvršavanja!");
    }
  }

  public void odaberiVozilo() {
    for (Vozilo v : tvrtka.uredDostava.getVozniPark()) {
      if (v.isSlobodno()) {
        tvrtka.uredDostava.setTrenutnoVozilo(v);
        break;
      }
    }
  }

  @Override
  public void interrupt() {
    super.interrupt();
  }

}
