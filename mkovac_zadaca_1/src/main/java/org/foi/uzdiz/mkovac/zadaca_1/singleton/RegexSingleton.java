package org.foi.uzdiz.mkovac.zadaca_1.singleton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.uzdiz.mkovac.zadaca_1.pomocnici.RegexVrsta;

public class RegexSingleton {
  private static volatile RegexSingleton INSTANCE = new RegexSingleton();

  private Pattern patternKomanda = null;
  private Pattern patternArgumenti = null;

  private final String regexKomanda = "\\b(?:IP|VR (?:[1-9]|1\\d|2[0-3]))\\b";
  private final String regexArgumenti =
      "(--vp [a-zA-ZÀ-ÖØ-öø-ÿČčĆćŽžĐđŠš0-9_-]+\\.csv)\\s+(--pv [a-zA-ZÀ-ÖØ-öø-ÿČčĆćŽžĐđŠš0-9_-]+\\.csv)\\s+(--pp [a-zA-ZÀ-ÖØ-öø-ÿČčĆćŽžĐđŠš0-9_-]+\\.csv)\\s+(--mt \\d+)\\s+(--vi \\d+)\\s+(--vs \\d{2}\\.\\d{2}.\\d{4}\\. (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))\\s+(--ms \\d+)\\s+(--pr (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]))\\s+(--kr (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]))$";

  private RegexSingleton() {}

  public static RegexSingleton getInstance() {
    return INSTANCE;
  }

  private Pattern dajPatternKomanda() {
    if (patternKomanda == null)
      patternKomanda = Pattern.compile(regexKomanda);
    return patternKomanda;
  }

  private Pattern dajPatternArgumenti() {
    if (patternArgumenti == null)
      patternArgumenti = Pattern.compile(regexArgumenti);
    return patternArgumenti;
  }

  public boolean provjeriIzraz(String izraz, RegexVrsta vrsta) {
    Matcher matcher = null;

    if (vrsta == RegexVrsta.komanda)
      matcher = dajPatternKomanda().matcher(izraz);
    else if (vrsta == RegexVrsta.argumenti)
      matcher = dajPatternArgumenti().matcher(izraz);

    if (matcher != null)
      return matcher.matches();
    else
      return false;
  }
}
