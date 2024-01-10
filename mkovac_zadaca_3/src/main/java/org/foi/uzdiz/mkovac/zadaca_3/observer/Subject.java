package org.foi.uzdiz.mkovac.zadaca_3.observer;

public interface Subject {
  public void addObserver(Observer observer);

  public void removeObserver(Observer observer);

  public String getStatus();

  public void setStatus(String status);

  public void posaljiObavijest();
}
