package org.foi.uzdiz.mkovac.zadaca_3.decorator;

public abstract class IspisPaketaDecorator implements IspisPaketa {

  private IspisPaketa ispisPaketa;

  public IspisPaketaDecorator(IspisPaketa ispisPaketa) {
    this.ispisPaketa = ispisPaketa;
  }

  @Override
  public void ispisi() {
    ispisPaketa.ispisi();
  }


}
