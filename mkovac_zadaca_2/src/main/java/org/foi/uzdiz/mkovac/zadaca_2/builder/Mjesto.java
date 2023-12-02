package org.foi.uzdiz.mkovac.zadaca_2.builder;

public class Mjesto {
  private int id;
  private String naziv;
  private int[] uliceId;
  // private List<Ulica> ulice;

  public Mjesto() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNaziv() {
    return naziv;
  }

  public void setNaziv(String naziv) {
    this.naziv = naziv;
  }

  public int[] getUliceId() {
    return uliceId;
  }

  public void setUliceId(int[] uliceId) {
    this.uliceId = uliceId;
  }

  @Override
  public String toString() {
    return this.id + ": " + this.naziv;
  }

  // public List<Ulica> getUlice() {
  // return ulice;
  // }
  //
  // public void setUlice(List<Ulica> ulice) {
  // this.ulice = ulice;
  // }


}
