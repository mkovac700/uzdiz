package org.foi.uzdiz.mkovac.zadaca_3.podaci;

public class Parametar {
  private String kljuc;
  private String vrijednost;

  public Parametar(String kljuc, String vrijednost) {
    super();
    this.kljuc = kljuc;
    this.vrijednost = vrijednost;
  }

  public String getKljuc() {
    return kljuc;
  }

  public void setKljuc(String kljuc) {
    this.kljuc = kljuc;
  }

  public String getVrijednost() {
    return vrijednost;
  }

  public void setVrijednost(String vrijednost) {
    this.vrijednost = vrijednost;
  }



}
