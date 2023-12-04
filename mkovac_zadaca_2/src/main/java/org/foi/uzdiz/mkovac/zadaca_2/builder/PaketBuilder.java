package org.foi.uzdiz.mkovac.zadaca_2.builder;

import java.time.LocalDateTime;
import org.foi.uzdiz.mkovac.zadaca_2.prototype.VrstaPaketa;

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

  PaketBuilder setPosiljatelj(Osoba posiljatelj);

  PaketBuilder setPrimatelj(Osoba primatelj);

  PaketBuilder setVrstaPaketa(VrstaPaketa vrstaPaketa);

  PaketBuilder setVisina(float visina);

  PaketBuilder setSirina(float sirina);

  PaketBuilder setDuzina(float duzina);

  PaketBuilder setTezina(float tezina);

  PaketBuilder setUslugaDostave(String uslugaDostave);

  PaketBuilder setIznosPouzeca(float iznosPouzeca);

}
