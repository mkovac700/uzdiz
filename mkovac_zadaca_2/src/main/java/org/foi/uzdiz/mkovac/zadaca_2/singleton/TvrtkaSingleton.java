package org.foi.uzdiz.mkovac.zadaca_2.singleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.foi.uzdiz.mkovac.zadaca_2.factory_method.CitacDatoteke;
import org.foi.uzdiz.mkovac.zadaca_2.factory_method.CitacParametara;
import org.foi.uzdiz.mkovac.zadaca_2.iznimke.NeispravniParametri;
import org.foi.uzdiz.mkovac.zadaca_2.podaci.Parametar;

public class TvrtkaSingleton {
  private static volatile TvrtkaSingleton INSTANCE = new TvrtkaSingleton();

  private TvrtkaSingleton() {
    postavke = new Properties();
  }

  public static TvrtkaSingleton getInstance() {
    return INSTANCE;
  }

  private Properties postavke;

  private List<Parametar> parametri;

  private String[] kljucevi_staro = {"vp", "pv", "pp", "mt", "vi", "vs", "ms", "pr", "kr"};
  private String[] kljucevi_novo = {"po", "pm", "pu", "pmu", "gps", "isporuka"};

  public void init(String argument) throws IOException, NeispravniParametri {
    this.ucitajParametre(argument);

  }

  private void ucitajParametre(String argument) throws IOException, NeispravniParametri {
    CitacDatoteke<Parametar> citac = new CitacParametara();
    parametri = citac.citajDatoteku(argument);

    for (Parametar parametar : parametri) {
      if (!postavke.containsKey(parametar.getKljuc()))
        postavke.put(parametar.getKljuc(), parametar.getVrijednost());
      else
        throw new NeispravniParametri("Datoteka parametara sadrži duplikat ključa!");

      System.out.println(
          "k: " + parametar.getKljuc() + " v: " + postavke.getProperty(parametar.getKljuc()));
    }

    List<String> argumenti_staro = new ArrayList<>();

    for (String k : kljucevi_staro) {
      if (!postavke.containsKey(k))
        throw new NeispravniParametri("Datoteka parametara ne sadrži sve potrebne parametre!");
      else {
        argumenti_staro.add("--" + k + " " + postavke.getProperty(k));
      }
    }

    System.out.println(String.join(" ", argumenti_staro));

    if (RegexProvjeraSingleton.getInstance()
        .provjeriUlazneArgumente(String.join(" ", argumenti_staro))) {
      System.out.println("bomba");
    } else {
      System.out.println("rip");
    }
  }
}
