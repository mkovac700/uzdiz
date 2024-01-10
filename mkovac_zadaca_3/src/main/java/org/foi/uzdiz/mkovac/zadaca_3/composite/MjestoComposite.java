package org.foi.uzdiz.mkovac.zadaca_3.composite;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Composite
 * 
 * @author Marijan Kovač
 *
 */
public class MjestoComposite implements LokacijaComponent {
  private int id;
  private String naziv;

  private ArrayList<LokacijaComponent> komponente;

  public MjestoComposite() {
    this.komponente = new ArrayList<>();
  }

  public MjestoComposite(int id, String naziv) {
    this.id = id;
    this.naziv = naziv;
    this.komponente = new ArrayList<>();
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
    komponente.add(lokacija);
  }

  @Override
  public void dodajLokaciju(LokacijaComponent... lokacije) {
    komponente.addAll(Arrays.asList(lokacije));
  }

  @Override
  public ArrayList<LokacijaComponent> dajLokacije() {
    return this.komponente;
  }

  @Override
  public String prikaziDetalje() {
    return this.id + ": " + this.naziv;
  }
}
