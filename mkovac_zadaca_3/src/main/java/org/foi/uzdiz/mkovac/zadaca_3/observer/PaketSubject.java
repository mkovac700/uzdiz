package org.foi.uzdiz.mkovac.zadaca_3.observer;

public interface PaketSubject {
  public void addObserver(PaketObserver observer);

  public void removeObserver(PaketObserver observer);

  public String getStatus();

  public void setStatus(String status);

  public void posaljiObavijest();
}
