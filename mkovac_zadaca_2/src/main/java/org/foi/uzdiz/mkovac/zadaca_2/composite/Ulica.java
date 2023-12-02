package org.foi.uzdiz.mkovac.zadaca_2.composite;


/**
 * Leaf
 * 
 * @author Marijan Kovač
 *
 */
public class Ulica implements LokacijaComponent {
  private int id;
  private String naziv;
  private float gpsLat1;
  private float gpsLon1;
  private float gpsLat2;
  private float gpsLon2;
  private int najveciKucniBroj;

  public Ulica() {}

  public Ulica(int id, String naziv, float gpsLat1, float gpsLon1, float gpsLat2, float gpsLon2,
      int najveciKucniBroj) {
    this.id = id;
    this.naziv = naziv;
    this.gpsLat1 = gpsLat1;
    this.gpsLon1 = gpsLon1;
    this.gpsLat2 = gpsLat2;
    this.gpsLon2 = gpsLon2;
    this.najveciKucniBroj = najveciKucniBroj;
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

  public float getGpsLat1() {
    return gpsLat1;
  }

  public void setGpsLat1(float gpsLat1) {
    this.gpsLat1 = gpsLat1;
  }

  public float getGpsLon1() {
    return gpsLon1;
  }

  public void setGpsLon1(float gpsLon1) {
    this.gpsLon1 = gpsLon1;
  }

  public float getGpsLat2() {
    return gpsLat2;
  }

  public void setGpsLat2(float gpsLat2) {
    this.gpsLat2 = gpsLat2;
  }

  public float getGpsLon2() {
    return gpsLon2;
  }

  public void setGpsLon2(float gpsLon2) {
    this.gpsLon2 = gpsLon2;
  }

  public int getNajveciKucniBroj() {
    return najveciKucniBroj;
  }

  public void setNajveciKucniBroj(int najveciKucniBroj) {
    this.najveciKucniBroj = najveciKucniBroj;
  }

  @Override
  public String prikaziDetalje() {
    return this.id + ": " + this.naziv;
  }


}
