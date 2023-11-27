package org.foi.uzdiz.mkovac.zadaca_2.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.uzdiz.mkovac.zadaca_2.podaci.RegexVrsta;

public class RegexProvjeraSingleton {
  private static volatile RegexProvjeraSingleton INSTANCE = new RegexProvjeraSingleton();

  private Pattern patternKomanda = null;
  private Pattern patternArgumenti = null;
  private Pattern patternTxtDatoteka = null;

  private final String regexKomanda = "\\b(?:Q|IP|VR (?:[1-9]|1\\d|2[0-3]))\\b";

  private final String regexArgumenti =
      "^(?=.*(--vp [a-zA-ZÀ-ÖØ-öø-ÿČčĆćŽžĐđŠš0-9_-]+\\.csv))(?=.*(--pv [a-zA-ZÀ-ÖØ-öø-ÿČčĆćŽžĐđŠš0-9_-]+\\.csv))(?=.*(--pp [a-zA-ZÀ-ÖØ-öø-ÿČčĆćŽžĐđŠš0-9_-]+\\.csv))(?=.*(--mt \\d+))(?=.*(--vi \\d+))(?=.*(--vs \\d{2}\\.\\d{2}\\.\\d{4}\\. (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])))(?=.*(--ms \\d+))(?=.*(--pr (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])))(?=.*(--kr (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]))).*$";

  private final String regexTxtDatoteka = "[a-zA-ZÀ-ÖØ-öø-ÿČčĆćŽžĐđŠš0-9_-]+\\.txt";

  private RegexProvjeraSingleton() {}

  public static RegexProvjeraSingleton getInstance() {
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

  private Pattern dajPatternTxtDatoteka() {
    if (patternTxtDatoteka == null)
      patternTxtDatoteka = Pattern.compile(regexTxtDatoteka);
    return patternTxtDatoteka;
  }

  public boolean provjeriUlazneArgumente(String argumenti) {
    return this.provjeriIzraz(argumenti, RegexVrsta.argumenti);
  }

  public boolean provjeriIzraz(String izraz, RegexVrsta vrsta) {
    Matcher matcher;

    if (vrsta == RegexVrsta.komanda)
      matcher = dajPatternKomanda().matcher(izraz);
    else if (vrsta == RegexVrsta.argumenti)
      matcher = dajPatternArgumenti().matcher(izraz);
    else if (vrsta == RegexVrsta.txtDatoteka)
      matcher = dajPatternTxtDatoteka().matcher(izraz);
    else
      matcher = null;

    if (matcher != null)
      return matcher.matches();
    else
      return false;
  }

  public String[] razdvojiIzraz(String izraz, RegexVrsta vrsta) {
    List<String> rezultat = new ArrayList<>();

    String s = izraz.trim();
    Matcher matcher = null;

    if (vrsta == RegexVrsta.argumenti)
      matcher = dajPatternArgumenti().matcher(s);
    else if (vrsta == RegexVrsta.komanda)
      matcher = dajPatternKomanda().matcher(s);
    else if (vrsta == RegexVrsta.txtDatoteka)
      matcher = dajPatternTxtDatoteka().matcher(s);

    if (matcher != null && matcher.matches()) {
      int poc = 0;
      int kraj = matcher.groupCount();
      for (int i = poc; i <= kraj; i++) {
        rezultat.add(matcher.group(i));
      }
    } else
      rezultat = null;

    return rezultat.toArray(new String[rezultat.size()]);
  }
}
