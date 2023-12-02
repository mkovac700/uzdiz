package org.foi.uzdiz.mkovac.zadaca_2.composite;

import java.util.ArrayList;


/**
 * Another composite
 * 
 * @author Marijan Kovač
 *
 */
public class PodrucjeC implements LokacijaComponent {
  private int id;
  // private int[][] gradUlica;
  private ArrayList<LokacijaComponent> mjesta;

  public PodrucjeC() {
    mjesta = new ArrayList<>();
  }

  public PodrucjeC(int id) {
    this.id = id;
    mjesta = new ArrayList<>();
  }

  @Override
  public void dodajLokaciju(LokacijaComponent lokacija) {
    mjesta.add(lokacija);
  }

  @Override
  public ArrayList<LokacijaComponent> dajLokacije() {
    return this.mjesta;
  }

  @Override
  public String prikaziDetalje() {
    return String.valueOf(id);
  }
}
