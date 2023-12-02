package org.foi.uzdiz.mkovac.zadaca_2.composite;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Composite
 * 
 * @author Marijan Kovač
 *
 */
public class Mjesto implements LokacijaComponent {
  private int id;
  private String naziv;

  private ArrayList<LokacijaComponent> ulice;

  public Mjesto() {
    this.ulice = new ArrayList<>();
  }

  public Mjesto(int id, String naziv) {
    this.id = id;
    this.naziv = naziv;
    this.ulice = new ArrayList<>();
  }

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

  @Override
  public void dodajLokaciju(LokacijaComponent lokacija) {
    ulice.add(lokacija);
  }

  @Override
  public void dodajLokaciju(LokacijaComponent... lokacije) {
    ulice.addAll(Arrays.asList(lokacije));
  }

  @Override
  public ArrayList<LokacijaComponent> dajLokacije() {
    return this.ulice;
  }

  @Override
  public String prikaziDetalje() {
    return this.id + ": " + this.naziv;
  }
}
