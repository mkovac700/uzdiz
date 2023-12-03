package org.foi.uzdiz.mkovac.zadaca_2.builder;

import org.foi.uzdiz.mkovac.zadaca_2.composite.LokacijaComponent;
import org.foi.uzdiz.mkovac.zadaca_2.composite.MjestoComposite;


/**
 * 
 * 
 * @author Marijan Kovač
 *
 */
public class Podrucje {
  private int id;

  private MjestoComposite svaMjesta;

  public Podrucje() {
    svaMjesta = new MjestoComposite();
  }

  public Podrucje(int id) {
    this.id = id;
    svaMjesta = new MjestoComposite();
  }


  public int getId() {
    return id;
  }


  public void setId(int id) {
    this.id = id;
  }



  public MjestoComposite getSvaMjesta() {
    return svaMjesta;
  }



  public void setSvaMjesta(MjestoComposite svaMjesta) {
    this.svaMjesta = svaMjesta;
  }



  public void dodajMjesta(LokacijaComponent... lokacije) {
    svaMjesta.dodajLokaciju(lokacije);
  }
}
