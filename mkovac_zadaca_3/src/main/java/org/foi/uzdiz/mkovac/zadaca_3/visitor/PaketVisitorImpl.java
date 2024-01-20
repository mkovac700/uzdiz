package org.foi.uzdiz.mkovac.zadaca_3.visitor;

import java.time.LocalDateTime;
import org.foi.uzdiz.mkovac.zadaca_3.builder.Paket;
import org.foi.uzdiz.mkovac.zadaca_3.pomocnici.DatumskoVremenskiKonverter;

public class PaketVisitorImpl implements PaketVisitor {

  @Override
  public String[] visit(Paket paket) {
    String oznaka = paket.getOznaka();
    LocalDateTime vrijemePrijema = paket.getVrijemePrijema();
    String vrstaPaketa = paket.getVrstaPaketa().oznaka;
    String vrstaUsluge = paket.getUslugaDostave();
    String statusIsporuke = paket.getStatus();
    LocalDateTime vrijemePreuzimanja = paket.getVrijemeIsporuke();
    float iznosDostave = paket.getIznosDostave();
    float iznosPouzeca = paket.getIznosPouzeca();

    String vrijemePrijemaKonvertirano = "-";
    String vrijemePreuzimanjaKonvertirano = "-";

    if (vrijemePrijema != null)
      vrijemePrijemaKonvertirano =
          DatumskoVremenskiKonverter.konvertirajDatumVrijeme(vrijemePrijema);
    if (vrijemePreuzimanja != null)
      vrijemePreuzimanjaKonvertirano =
          DatumskoVremenskiKonverter.konvertirajDatumVrijeme(vrijemePreuzimanja);

    return new String[] {oznaka, vrijemePrijemaKonvertirano, vrstaPaketa, vrstaUsluge,
        statusIsporuke, vrijemePreuzimanjaKonvertirano, String.valueOf(iznosDostave),
        String.valueOf(iznosPouzeca)};
  }

}
