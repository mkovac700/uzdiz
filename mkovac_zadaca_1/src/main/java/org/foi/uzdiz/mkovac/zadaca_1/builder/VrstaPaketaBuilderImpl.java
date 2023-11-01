package org.foi.uzdiz.mkovac.zadaca_1.builder;

/**
 * Concrete builder
 * 
 * @author Marijan Kovač
 *
 */
public class VrstaPaketaBuilderImpl implements VrstaPaketaBuilder {

  private VrstaPaketa vrstaPaketa;

  public VrstaPaketaBuilderImpl() {
    vrstaPaketa = new VrstaPaketa();
  }

  @Override
  public VrstaPaketa build() {
    return vrstaPaketa;
  }

  @Override
  public VrstaPaketaBuilder setOznaka(String oznaka) {
    vrstaPaketa.setOznaka(oznaka);
    return this;
  }

  @Override
  public VrstaPaketaBuilder setOpis(String opis) {
    vrstaPaketa.setOpis(opis);
    return this;
  }

  @Override
  public VrstaPaketaBuilder setVisina(float visina) {
    vrstaPaketa.setVisina(visina);
    return this;
  }

  @Override
  public VrstaPaketaBuilder setSirina(float sirina) {
    vrstaPaketa.setSirina(sirina);
    return this;
  }

  @Override
  public VrstaPaketaBuilder setDuzina(float duzina) {
    vrstaPaketa.setDuzina(duzina);
    return this;
  }

  @Override
  public VrstaPaketaBuilder setMaksimalnaTezina(float maksimalnaTezina) {
    vrstaPaketa.setMaksimalnaTezina(maksimalnaTezina);
    return this;
  }

  @Override
  public VrstaPaketaBuilder setCijena(float cijena) {
    vrstaPaketa.setCijena(cijena);
    return this;
  }

  @Override
  public VrstaPaketaBuilder setCijenaHitno(float cijenaHitno) {
    vrstaPaketa.setCijenaHitno(cijenaHitno);
    return this;
  }

  @Override
  public VrstaPaketaBuilder setCijenaP(float cijenaP) {
    vrstaPaketa.setCijenaP(cijenaP);
    return this;
  }

  @Override
  public VrstaPaketaBuilder setCijenaT(float cijenaT) {
    vrstaPaketa.setCijenaT(cijenaT);
    return this;
  }



}
