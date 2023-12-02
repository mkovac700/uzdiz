package org.foi.uzdiz.mkovac.zadaca_2.builder;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_2.composite.Mjesto;


/**
 * Another composite
 * 
 * @author Marijan Kovač
 *
 */
public class Podrucje {
  private int id;
  // private int[][] gradUlica;
  private List<Mjesto> mjesta;

  public Podrucje(int id) {
    this.id = id;
    mjesta = new ArrayList<>();
  }

  // public ucitajMjesta(Mjesto mjesto) {
  //
  // }
}
