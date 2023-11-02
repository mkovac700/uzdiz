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

    System.out.println("virtualni sat: " + tvrtka.virtualniSat.toString());
    System.out.println("virtualni kraj: " + virtualniKraj.toString());

    System.out.println(tvrtka.virtualniSat.toLocalTime().isBefore(tvrtka.krajRada));
    System.out.println(tvrtka.virtualniSat.isBefore(virtualniKraj));

    while (tvrtka.virtualniSat.toLocalTime().isBefore(tvrtka.krajRada)
        && tvrtka.virtualniSat.isBefore(virtualniKraj)) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      tvrtka.virtualniSat = tvrtka.virtualniSat.plusSeconds(tvrtka.mnoziteljSekunde);

      // System.out.println("virtualni sat: " + virtualniSat.toString());

      Iterator<Paket> itr = tvrtka.paketi.iterator();

      while (itr.hasNext()) {
        Paket p = itr.next();
        if (p.getVrijemePrijema().isBefore(tvrtka.virtualniSat)) {
          tvrtka.uredPrijem.zaprimiPaket(p);
          itr.remove();
        }
      }

      // System.out.println("[" + tvrtka.virtualniSat + "]" + " Zaprimljeni paketi: ");
      //
      // for (Paket p : tvrtka.uredPrijem.getPrimljeniPaketi()) {
      // System.out.println(p);
      // }

      // System.out.println("svi paketi: ");
      // for (Paket p : paketi) {
      // System.out.println(p);
      // }

      // odabir vozila:
      odaberiVozilo();

      // ukrcavanje paketa:
      if (!tvrtka.uredPrijem.getPrimljeniPaketi().isEmpty()) {
        // ukrcaj paket u vozilo
        Iterator<Paket> itr2 = tvrtka.uredPrijem.getPrimljeniPaketi().iterator();

        while (itr2.hasNext()) {
          Paket p = itr2.next();
          if (!tvrtka.uredDostava.getTrenutnoVozilo().dodajPaket(p)) {
            odaberiVozilo();
            tvrtka.uredDostava.getTrenutnoVozilo().dodajPaket(p);
          }
          itr2.remove();
        }
      }

      // iskrcavanje paketa:
      for (Vozilo v : tvrtka.uredDostava.getVozniPark()) {
        if (!v.isSlobodno()) {

          // for (Paket p : v.getPaketi()) {
          // if ((p.getVrijemePreuzimanja().isAfter(tvrtka.virtualniSat)
          // || p.getVrijemePreuzimanja().isEqual(tvrtka.virtualniSat))
          // && (p.getVrijemePreuzimanja()
          // .isBefore(tvrtka.virtualniSat.plusSeconds(tvrtka.mnoziteljSekunde))
          // || p.getVrijemePreuzimanja()
          // .isEqual(tvrtka.virtualniSat.plusSeconds(tvrtka.mnoziteljSekunde)))) {
          // v.ukloniPaket(p);
          // }
          // }
          //
          // if (v.getPaketi().isEmpty())
          // v.setSlobodno(true);

          Iterator<Paket> itr3 = v.getPaketi().iterator();

          // System.out.println("ISKRCAVANJE PAKETA IZ " + v.getOpis());

          while (itr3.hasNext()) {
            Paket p = itr3.next();

            if ((p.getVrijemePreuzimanja().isAfter(tvrtka.virtualniSat)
                || p.getVrijemePreuzimanja().isEqual(tvrtka.virtualniSat))
                && (p.getVrijemePreuzimanja()
                    .isBefore(tvrtka.virtualniSat.plusSeconds(tvrtka.mnoziteljSekunde))
                    || p.getVrijemePreuzimanja()
                        .isEqual(tvrtka.virtualniSat.plusSeconds(tvrtka.mnoziteljSekunde)))) {

              System.out.println("[" + p.getVrijemePreuzimanja() + "]" + " Dostavljen paket: " + p);

              itr3.remove();
            }
          }

          if (v.getPaketi().isEmpty())
            v.setSlobodno(true);
        }
      }



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
