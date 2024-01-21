package org.foi.uzdiz.mkovac.zadaca_3.decorator;

import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_3.builder.Paket;
import org.foi.uzdiz.mkovac.zadaca_3.singleton.TvrtkaSingleton;
import org.foi.uzdiz.mkovac.zadaca_3.visitor.PaketVisitorImpl;

public class IspisPaketaImpl implements IspisPaketa {

  @Override
  public void ispisi() {
    final int RAZMAK_KRACI = 3;
    final int RAZMAK_DUZI = 20;

    List<Paket> primljeniPaketi =
        TvrtkaSingleton.getInstance().getUredPrijem().getSviPrimljeniPaketi();

    if (primljeniPaketi == null || primljeniPaketi.isEmpty()) {
      System.out.println("Nema podataka za prikaz!");
      return;
    }

    PaketVisitorImpl paketVisitorImpl = new PaketVisitorImpl();

    String[] zaglavlje = {"#", "OZNAKA PAKETA", "VRIJEME PRIJEMA", "VRSTA PAKETA", "VRSTA USLUGE",
        "STATUS ISPORUKE", "VRIJEME PREUZIMANJA", "IZNOS DOSTAVE", "IZNOS POUZEĆA"};

    // PRINTAJ GORNJI BORDER ZAGLAVLJA
    for (int i = 0; i <= zaglavlje.length; i++) {
      System.out.print("+");
      if (i == zaglavlje.length)
        break;
      for (int j = 0; i != 0 ? j < RAZMAK_DUZI : j < RAZMAK_KRACI; j++) {
        System.out.print("-");
      }
    }

    System.out.println();

    // PRINTAJ ZAGLAVLJE

    System.out.print("|");

    int totalSpaces;
    int padding;

    int razmak;

    for (int i = 0; i < zaglavlje.length; i++) {
      razmak = i == 0 ? RAZMAK_KRACI : RAZMAK_DUZI;
      totalSpaces = razmak - zaglavlje[i].length();
      padding = totalSpaces / 2;
      System.out.print(String.format("%-" + razmak + "s|",
          String.format("%-" + (zaglavlje[i].length() + padding) + "s",
              String.format("%" + (zaglavlje[i].length() + padding) + "s", zaglavlje[i]))));
    }

    System.out.println();

    // PRINTAJ DONJI BORDER ZAGLAVLJA
    for (int i = 0; i <= zaglavlje.length; i++) {
      System.out.print("+");
      if (i == zaglavlje.length)
        break;
      for (int j = 0; i != 0 ? j < RAZMAK_DUZI : j < RAZMAK_KRACI; j++) {
        System.out.print("-");
      }
    }

    System.out.println();

    // PRINTAJ PODATKE

    int brojac = 0;
    for (Paket paket : primljeniPaketi) {
      String[] podaci = paket.accept(paketVisitorImpl);
      System.out.print("|");
      System.out.print(String.format("%-" + RAZMAK_KRACI + "s|", ++brojac));
      for (String p : podaci) {
        System.out.print(String.format("%-" + RAZMAK_DUZI + "s|", p));
      }
      System.out.println();
    }

    // PRINTAJ DONJI BORDER
    for (int i = 0; i <= zaglavlje.length; i++) {
      System.out.print("+");
      if (i == zaglavlje.length)
        break;
      for (int j = 0; i != 0 ? j < RAZMAK_DUZI : j < RAZMAK_KRACI; j++) {
        System.out.print("-");
      }
    }

    System.out.println();
  }

}
