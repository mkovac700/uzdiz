package org.foi.uzdiz.mkovac.zadaca_2.builder;

import org.foi.uzdiz.mkovac.zadaca_2.composite.LokacijaComponent;
import org.foi.uzdiz.mkovac.zadaca_2.composite.Mjesto;


/**
 * 
 * 
 * @author Marijan Kovač
 *
 */
public class Podrucje {
  private int id;

  private Mjesto svaMjesta = new Mjesto();

  public Podrucje(int id) {
    this.id = id;
  }



  public int getId() {
    return id;
  }



  public void setId(int id) {
    this.id = id;
  }



  public Mjesto getSvaMjesta() {
    return svaMjesta;
  }



  public void setSvaMjesta(Mjesto svaMjesta) {
    this.svaMjesta = svaMjesta;
  }



  public void dodajMjesta(LokacijaComponent... lokacije) {
    svaMjesta.dodajLokaciju(lokacije);
  }
}
