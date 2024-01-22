package org.foi.uzdiz.mkovac.zadaca_3.strategy;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.mkovac.zadaca_3.builder.Paket;
import org.foi.uzdiz.mkovac.zadaca_3.composite.UlicaLeaf;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.Segment;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.Voznja;
import org.foi.uzdiz.mkovac.zadaca_3.singleton.TvrtkaSingleton;

public class IsporukaNajblizaDostava implements IsporukaStrategy {

  @Override
  public void obaviIzracune(List<Paket> paketi, Voznja voznja) {

    List<Segment> segmenti = new ArrayList<>();

    // kopija liste paketa kako se ne bi dirala originalna lista
    List<Paket> paketiTmp = new ArrayList<>(paketi);

    final String gpsUreda = TvrtkaSingleton.getInstance().gpsUreda;

    String trenutniGps = gpsUreda;

    while (!paketiTmp.isEmpty()) {
      float najmanjaUdaljenost = Float.MAX_VALUE;
      Paket najbliziPaket = null;

      for (Paket paket : paketiTmp) {
        float[] gpsPaketa = izracunajGpsPaketa(paket);

        float udaljenost = this
            .izracunajUdaljenostIzmeduDvijeTocke(konvertirajGpsKoordinate(trenutniGps), gpsPaketa);

        if (udaljenost < najmanjaUdaljenost) {
          najmanjaUdaljenost = udaljenost;
          najbliziPaket = paket;
        }
      }

      float[] gpsNajblizegPaketa = izracunajGpsPaketa(najbliziPaket);

      Segment segment = new Segment();
      segment.setOdGps(trenutniGps);
      segment.setDoGps(this.konvertirajGpsKoordinate(gpsNajblizegPaketa));
      segment.setUdaljenost(najmanjaUdaljenost);
      segment.setTrajanjeIsporuke(TvrtkaSingleton.getInstance().vrijemeIzvrsavanja);
      segment.setPaket(najbliziPaket);

      segmenti.add(segment);

      trenutniGps = konvertirajGpsKoordinate(gpsNajblizegPaketa);

      paketiTmp.remove(najbliziPaket);

    }

    // nakon zadnjeg paketa kreiraj i segment povratka u ured:
    float udaljenost = this.izracunajUdaljenostIzmeduDvijeTocke(
        konvertirajGpsKoordinate(trenutniGps), konvertirajGpsKoordinate(gpsUreda));
    Segment segment = new Segment();
    segment.setOdGps(trenutniGps);
    segment.setDoGps(gpsUreda);
    segment.setUdaljenost(udaljenost);
    // ostalo je sve null ukljucujuci i paket

    segmenti.add(segment);

    // na kraju dodijeli segmente voznji
    voznja.setSegmenti(segmenti);

  }

  private String konvertirajGpsKoordinate(float[] gpsKoordinate) {
    return gpsKoordinate[0] + "," + gpsKoordinate[1];
  }

  private float[] konvertirajGpsKoordinate(String gpsKoordinate) {
    String[] koordinate = gpsKoordinate.replace(" ", "").split(",");

    float lat = Float.parseFloat(koordinate[0]);
    float lon = Float.parseFloat(koordinate[1]);

    return new float[] {lat, lon};
  }

  private float[] izracunajGpsPaketa(Paket paket) {
    UlicaLeaf ulica = paket.getPrimatelj().getUlica();
    int kbrPaketa = paket.getPrimatelj().getKbr();
    int najveciKbr = ulica.getNajveciKucniBroj();

    if (kbrPaketa > najveciKbr)
      kbrPaketa = najveciKbr;

    float postotak = (float) (kbrPaketa / najveciKbr);

    float gpsLat1 = ulica.getGpsLat1();
    float gpsLon1 = ulica.getGpsLon1();
    float gpsLat2 = ulica.getGpsLat2();
    float gpsLon2 = ulica.getGpsLon2();

    float paketLat = (gpsLat2 - gpsLat1) * postotak + gpsLat1;
    float paketLon = (gpsLon2 - gpsLon1) * postotak + gpsLon1;

    return new float[] {paketLat, paketLon};
  }

  private float izracunajUdaljenostIzmeduDvijeTocke(float[] gps1, float[] gps2) {

    float kmLat = 111.32f;
    float kmLon = 111.32f;

    float gpsLat1 = gps1[0];
    float gpsLon1 = gps1[1];
    float gpsLat2 = gps2[0];
    float gpsLon2 = gps2[1];

    float dLat = gpsLat2 - gpsLat1;
    float dLon = gpsLon2 - gpsLon1;

    return (float) Math.abs(Math.sqrt(dLat * dLat * kmLat * kmLat + dLon * dLon * kmLon * kmLon));
  }

}
