package org.foi.uzdiz.mkovac.zadaca_1;

import java.util.regex.Pattern;

public class RegexSingleton {
  private static volatile RegexSingleton INSTANCE = new RegexSingleton();

  private Pattern patternKomanda;
  private Pattern patternArgumenti;

  private RegexSingleton() {
    String regexKomanda = "\\b(?:IP|VR (?:[1-9]|1\\d|2[0-3]))\\b";
    patternKomanda = Pattern.compile(regexKomanda);
  }

  public static RegexSingleton getInstance() {
    return INSTANCE;
  }

  public Pattern dajPatternKomanda() {
    return patternKomanda;
  }

  public Pattern dajPatternArgumenti() {
    return patternArgumenti;
  }
}
