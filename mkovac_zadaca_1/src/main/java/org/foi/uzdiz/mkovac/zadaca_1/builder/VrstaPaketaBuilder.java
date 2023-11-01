package org.foi.uzdiz.mkovac.zadaca_1.builder;

/**
 * The builder abstraction
 * 
 * @author Marijan Kovač
 *
 */
public interface VrstaPaketaBuilder {
  VrstaPaketa build();

  VrstaPaketaBuilder setOznaka(String oznaka);

  VrstaPaketaBuilder setOpis(String opis);

  VrstaPaketaBuilder setVisina(float visina);

  VrstaPaketaBuilder setSirina(float sirina);

  VrstaPaketaBuilder setDuzina(float duzina);

  VrstaPaketaBuilder setMaksimalnaTezina(float maksimalnaTezina);

  VrstaPaketaBuilder setCijena(float cijena);

  VrstaPaketaBuilder setCijenaHitno(float cijenaHitno);

  VrstaPaketaBuilder setCijenaP(float cijenaP);

  VrstaPaketaBuilder setCijenaT(float cijenaT);
}
