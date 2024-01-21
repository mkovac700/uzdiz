package org.foi.uzdiz.mkovac.zadaca_3.decorator;

import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_3.builder.Paket;
import org.foi.uzdiz.mkovac.zadaca_3.singleton.TvrtkaSingleton;

public class IspisPrometaPaketa extends IspisPaketaDecorator {

  public IspisPrometaPaketa(IspisPaketa ispisPaketa) {
    super(ispisPaketa);
  }

  public void ispisi() {
    super.ispisi();
    this.ispisiPrometPaketa();
  }

  private void ispisiPrometPaketa() {
    TvrtkaSingleton tvrtka = TvrtkaSingleton.getInstance();

    List<Paket> primljeniPaketi = tvrtka.getUredPrijem().getSviPrimljeniPaketi();

    if (primljeniPaketi == null || primljeniPaketi.isEmpty()) {
      System.out.println("Nema podataka za prikaz!");
      return;
    }

    float prikupljeniNovac = tvrtka.getPrikupljeniNovac();

    System.out.println("UKUPNO PAKETA: " + primljeniPaketi.size());
    System.out.println("PRIKUPLJENI NOVAC: " + String.format("%.2f", prikupljeniNovac));
  }
}
