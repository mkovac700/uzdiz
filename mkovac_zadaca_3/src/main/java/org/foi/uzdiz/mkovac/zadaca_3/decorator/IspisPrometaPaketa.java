package org.foi.uzdiz.mkovac.zadaca_3.decorator;

public class IspisPrometaPaketa extends IspisPaketaDecorator {

  public IspisPrometaPaketa(IspisPaketa ispisPaketa) {
    super(ispisPaketa);
  }

  public void ispisi() {
    super.ispisi();
    this.ispisiPrometPaketa();
  }

  private void ispisiPrometPaketa() {
    System.out.println("Probni ispis");
  }
}
