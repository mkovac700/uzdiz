package org.foi.uzdiz.mkovac.zadaca_3.pomocnici;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.RegexVrsta;
import org.foi.uzdiz.mkovac.zadaca_3.singleton.RegexProvjeraSingleton;

public class ParametriProvjera {

  public ParametriProvjera() {}

  private final String[] kljucevi_staro = {"vp", "pv", "pp", "mt", "vi", "vs", "ms", "pr", "kr"};
  private final String[] kljucevi_novo = {"po", "pm", "pu", "pmu", "gps", "isporuka"};

  public boolean provjeriParametre(Properties postavke) {
    RegexProvjera rp = new RegexProvjera();

    List<String> argumenti_staro = new ArrayList<>();

    for (String k : kljucevi_staro) {
      if (!postavke.containsKey(k))
        return false;
      else {
        argumenti_staro.add("--" + k + " " + postavke.getProperty(k));
      }
    }

    if (!RegexProvjeraSingleton.getInstance()
        .provjeriUlazneArgumente(String.join(" ", argumenti_staro)))
      return false;

    for (String k : kljucevi_novo) {
      if (!postavke.containsKey(k))
        return false;
      else {
        if ((k.equals("po") || k.equals("pm") || k.equals("pu"))
            && !rp.provjeriCsvDatoteku(postavke.getProperty(k)))
          return false;
        if (k.equals("gps") && !rp.provjeriGpsKoordinate(postavke.getProperty(k)))
          return false;
        if (k.equals("isporuka") && !rp.provjeriIsporuku(postavke.getProperty(k)))
          return false;
      }
    }

    return true;
  }

  static class RegexProvjera {
    private final String regexCsvDatoteka = "[a-zA-ZÀ-ÖØ-öø-ÿČčĆćŽžĐđŠš0-9_-]+\\.csv";
    private final String regexKoordinate =
        "[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$";
    private final String regexIsporuka = "(1|2)$";

    private Pattern patternCsvDatoteka = null;
    private Pattern patternKoordinate = null;
    private Pattern patternIsporuka = null;

    private Pattern dajPatternCsvDatoteka() {
      if (patternCsvDatoteka == null)
        patternCsvDatoteka = Pattern.compile(regexCsvDatoteka);
      return patternCsvDatoteka;
    }

    private Pattern dajPatternKoordinate() {
      if (patternKoordinate == null)
        patternKoordinate = Pattern.compile(regexKoordinate);
      return patternKoordinate;
    }

    private Pattern dajPatternBroj() {
      if (patternIsporuka == null)
        patternIsporuka = Pattern.compile(regexIsporuka);
      return patternIsporuka;
    }

    private boolean provjeriIzraz(String izraz, RegexVrsta vrsta) {
      Matcher matcher;

      if (vrsta == RegexVrsta.csvDatoteka)
        matcher = this.dajPatternCsvDatoteka().matcher(izraz);
      else if (vrsta == RegexVrsta.koordinate)
        matcher = this.dajPatternKoordinate().matcher(izraz);
      else if (vrsta == RegexVrsta.isporuka)
        matcher = this.dajPatternBroj().matcher(izraz);
      else
        matcher = null;

      if (matcher != null)
        return matcher.matches();
      else
        return false;
    }

    public boolean provjeriCsvDatoteku(String izraz) {
      return this.provjeriIzraz(izraz, RegexVrsta.csvDatoteka);
    }

    public boolean provjeriGpsKoordinate(String izraz) {
      return this.provjeriIzraz(izraz, RegexVrsta.koordinate);
    }

    public boolean provjeriIsporuku(String izraz) {
      return this.provjeriIzraz(izraz, RegexVrsta.isporuka);
    }
  }
}
