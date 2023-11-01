package org.foi.uzdiz.mkovac.zadaca_1.builder;

import java.time.LocalDateTime;

/**
 * Director
 * 
 * @author Marijan Kovač
 *
 */
public class PaketBuildDirector {

  private PaketBuilder builder;

  public PaketBuildDirector(final PaketBuilder builder) {
    this.builder = builder;
  }

  public Paket construct(String oznaka, LocalDateTime vrijemePrijema, String posiljatelj,
      String primatelj, VrstaPaketa vrstaPaketa, float visina, float sirina, float duzina,
      float tezina, String uslugaDostave, float iznosPouzeca) {
    return builder.setOznaka(oznaka).setVrijemePrijema(vrijemePrijema).setPosiljatelj(posiljatelj)
        .setPrimatelj(primatelj).setVrstaPaketa(vrstaPaketa).setVisina(visina).setSirina(sirina)
        .setDuzina(duzina).setTezina(tezina).setUslugaDostave(uslugaDostave)
        .setIznosPouzeca(iznosPouzeca).build();

  }

}
