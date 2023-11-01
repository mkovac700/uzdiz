package org.foi.uzdiz.mkovac.zadaca_1.builder;

import java.time.LocalDateTime;

/**
 * The builder abstraction
 * 
 * @author Marijan Kovač
 *
 */
public interface PaketBuilder {
  Paket build();

  PaketBuilder setOznaka(String oznaka);

  PaketBuilder setVrijemePrijema(LocalDateTime vrijemePrijema);

  PaketBuilder setPosiljatelj(String posiljatelj);

  PaketBuilder setPrimatelj(String primatelj);

  PaketBuilder setVrstaPaketa(VrstaPaketa vrstaPaketa);

  PaketBuilder setVisina(float visina);

  PaketBuilder setSirina(float sirina);

  PaketBuilder setDuzina(float duzina);

  PaketBuilder setTezina(float tezina);

  PaketBuilder setUslugaDostave(String uslugaDostave);

  PaketBuilder setIznosPouzeca(float iznosPouzeca);

}
