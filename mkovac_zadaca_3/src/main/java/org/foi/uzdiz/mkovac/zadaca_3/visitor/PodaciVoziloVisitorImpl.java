package org.foi.uzdiz.mkovac.zadaca_3.visitor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.foi.uzdiz.mkovac.zadaca_3.builder.Paket;
import org.foi.uzdiz.mkovac.zadaca_3.builder.Vozilo;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.Segment;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.Voznja;
import org.foi.uzdiz.mkovac.zadaca_3.singleton.TvrtkaSingleton;

public class PodaciVoziloVisitorImpl implements Visitor {

  @Override
  public String[] visit(Segment segment) {
    LocalDateTime vrijemePocetka = segment.getVrijemePocetka();
    LocalDateTime vrijemeKraja = segment.getVrijemeKraja();
    // float trajanje = segment.getUkupnoTrajanjeSegmenta();
    long trajanjeMillis = (long) (segment.getUkupnoTrajanjeSegmenta() * 60 * 1000);
    LocalTime trajanje = LocalTime.MIDNIGHT.plus(Duration.ofMillis(trajanjeMillis));
    float odvozenoKm = segment.getUdaljenost();
    Paket paket = segment.getPaket();
    String oznakaPaketa = "-";
    if (paket != null)
      oznakaPaketa = paket.getOznaka();

    return new String[] {TvrtkaSingleton.getInstance().konvertirajDatumVrijeme(vrijemePocetka),
        TvrtkaSingleton.getInstance().konvertirajDatumVrijeme(vrijemeKraja),
        TvrtkaSingleton.getInstance().konvertirajVrijeme(trajanje), String.valueOf(odvozenoKm),
        oznakaPaketa};
  }

  @Override
  public String[] visit(Voznja voznja) {
    LocalDateTime vrijemePocetka = voznja.getVrijemePocetka();
    LocalDateTime vrijemeKraja = voznja.getVrijemePovratka();
    LocalTime trajanje = voznja.getTrajanje();
    float odvozenoKm = voznja.getUkupnoKm();
    int brojHitnih = voznja.getBrojHitnih();
    int brojObicnih = voznja.getBrojObicnih();
    int brojIsporucenih = voznja.getBrojIsporucenih();
    float zauzeceProstoraNaPocetku = voznja.getZauzeceProstoraNaPocetku();
    float zauzeceTezineNaPocetku = voznja.getZauzeceTezineNaPocetku();

    return new String[] {TvrtkaSingleton.getInstance().konvertirajDatumVrijeme(vrijemePocetka),
        TvrtkaSingleton.getInstance().konvertirajDatumVrijeme(vrijemeKraja),
        TvrtkaSingleton.getInstance().konvertirajVrijeme(trajanje), String.valueOf(odvozenoKm),
        String.valueOf(brojHitnih), String.valueOf(brojObicnih), String.valueOf(brojIsporucenih),
        String.valueOf(zauzeceProstoraNaPocetku), String.valueOf(zauzeceTezineNaPocetku)};
  }

  @Override
  public String[] visit(Vozilo vozilo) {
    String status = vozilo.getStatus().getOznaka();
    float odvozenoKm = vozilo.getOdvozenoKm();
    int brojHitnih = vozilo.getBrojHitnih();
    int brojObicnih = vozilo.getBrojObicnih();
    int brojIsporucenih = vozilo.getBrojIsporucenih();
    float trenutniPostotakZauzecaProstora = vozilo.getTrenutniPostotakZauzecaProstora();
    float trenutniPostotakZauzecaTezine = vozilo.getTrenutniPostotakZauzecaTezine();
    int brojVoznji = vozilo.getBrojVoznji();

    return new String[] {status, String.valueOf(odvozenoKm), String.valueOf(brojHitnih),
        String.valueOf(brojObicnih), String.valueOf(brojIsporucenih),
        String.valueOf(trenutniPostotakZauzecaProstora),
        String.valueOf(trenutniPostotakZauzecaTezine), String.valueOf(brojVoznji)};

  }

}
