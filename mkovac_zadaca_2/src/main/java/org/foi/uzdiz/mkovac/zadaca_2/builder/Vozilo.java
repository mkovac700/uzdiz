package org.foi.uzdiz.mkovac.zadaca_2.builder;

import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_2.state.State;

public class Vozilo {
  private String registracija;
  private String opis;
  private float kapacitetTezine;
  private float kapacitetProstora;
  private int redoslijed;
  private float prosjecnaBrzina;
  private List<Podrucje> podrucjaPoRangu;
  private State status;

  public Vozilo() {}

  public String getRegistracija() {
    return registracija;
  }

  public void setRegistracija(String registracija) {
    this.registracija = registracija;
  }

  public String getOpis() {
    return opis;
  }

  public void setOpis(String opis) {
    this.opis = opis;
  }

  public float getKapacitetTezine() {
    return kapacitetTezine;
  }

  public void setKapacitetTezine(float kapacitetTezine) {
    this.kapacitetTezine = kapacitetTezine;
  }

  public float getKapacitetProstora() {
    return kapacitetProstora;
  }

  public void setKapacitetProstora(float kapacitetProstora) {
    this.kapacitetProstora = kapacitetProstora;
  }

  public int getRedoslijed() {
    return redoslijed;
  }

  public void setRedoslijed(int redoslijed) {
    this.redoslijed = redoslijed;
  }

  public float getProsjecnaBrzina() {
    return prosjecnaBrzina;
  }

  public void setProsjecnaBrzina(float prosjecnaBrzina) {
    this.prosjecnaBrzina = prosjecnaBrzina;
  }

  public List<Podrucje> getPodrucjaPoRangu() {
    return podrucjaPoRangu;
  }

  public void setPodrucjaPoRangu(List<Podrucje> podrucjaPoRangu) {
    this.podrucjaPoRangu = podrucjaPoRangu;
  }

  public State getStatus() {
    return status;
  }

  public void setStatus(State status) {
    this.status = status;
  }

  public void aktiviraj() {
    this.status.aktiviraj(this);
  }

  public void deaktiviraj() {
    this.status.deaktiviraj(this);
  }

  public void setNeispravno() {
    this.status.setNeispravno(this);
  }

  @Override
  public String toString() {
    return this.opis;
  }


}
