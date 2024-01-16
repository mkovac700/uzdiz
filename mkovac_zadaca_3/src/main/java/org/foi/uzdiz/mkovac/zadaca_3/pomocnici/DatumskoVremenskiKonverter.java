package org.foi.uzdiz.mkovac.zadaca_3.pomocnici;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DatumskoVremenskiKonverter {

  private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
  private static final DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");

  public static String konvertirajDatumVrijeme(LocalDateTime datumVrijeme) {
    return datumVrijeme.format(dtf);
  }

  public static String konvertirajVrijeme(LocalTime vrijeme) {
    return vrijeme.format(tf);
  }
}
