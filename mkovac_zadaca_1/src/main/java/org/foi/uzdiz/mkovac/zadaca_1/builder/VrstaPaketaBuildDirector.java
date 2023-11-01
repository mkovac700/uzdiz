package org.foi.uzdiz.mkovac.zadaca_1.builder;

/**
 * Director
 * 
 * @author Marijan Kovač
 *
 */
public class VrstaPaketaBuildDirector {

  private VrstaPaketaBuilder builder;

  public VrstaPaketaBuildDirector(final VrstaPaketaBuilder builder) {
    this.builder = builder;
  }

  public VrstaPaketa construct(String oznaka, String opis, float visina, float sirina, float duzina,
      float maksimalnaTezina, float cijena, float cijenaHitno, float cijenaP, float cijenaT) {
    return builder.setOznaka(oznaka).setOpis(opis).setVisina(visina).setSirina(sirina)
        .setDuzina(duzina).setMaksimalnaTezina(maksimalnaTezina).setCijena(cijena)
        .setCijenaHitno(cijenaHitno).setCijenaP(cijenaP).setCijenaT(cijenaT).build();
  }

}
