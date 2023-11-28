package org.foi.uzdiz.mkovac.zadaca_2.singleton;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.foi.uzdiz.mkovac.zadaca_2.factory_method.CitacDatoteke;
import org.foi.uzdiz.mkovac.zadaca_2.factory_method.CitacParametara;
import org.foi.uzdiz.mkovac.zadaca_2.factory_method.CitacVrstaPaketa;
import org.foi.uzdiz.mkovac.zadaca_2.iznimke.NeispravniParametri;
import org.foi.uzdiz.mkovac.zadaca_2.podaci.Parametar;
import org.foi.uzdiz.mkovac.zadaca_2.prototype.VrstaPaketa;

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
  private List<VrstaPaketa> vrstePaketa;

  public void init(String argument) throws IOException, NeispravniParametri {
    this.ucitajParametre(argument);
    this.ucitajVrstePaketa(postavke.getProperty("vp"));
  }

  private void ucitajParametre(String datoteka) throws IOException, NeispravniParametri {
    CitacDatoteke<Parametar> citac = new CitacParametara();
    parametri = citac.citajDatoteku(datoteka);

    for (Parametar parametar : parametri) {
      if (!postavke.containsKey(parametar.getKljuc()))
        postavke.put(parametar.getKljuc(), parametar.getVrijednost());
      else
        throw new NeispravniParametri("Datoteka parametara sadrži duplikat ključa!");

      // TODO remove
      System.out.println(
          "k: " + parametar.getKljuc() + " v: " + postavke.getProperty(parametar.getKljuc()));
    }

    if (!ParametriProvjeraSingleton.getInstance().provjeriParametre(postavke))
      throw new NeispravniParametri(
          "Datoteka parametara ne sadrži sve parametre ili parametri nisu ispravni!");
    else
      System.out.println("OK");
  }

  private void ucitajVrstePaketa(String datoteka) throws IOException {
    CitacDatoteke<VrstaPaketa> citac = new CitacVrstaPaketa();
    vrstePaketa = citac.citajDatoteku(datoteka);

    // TODO remove
    System.out.println("Ucitane vrste paketa: ");

    // TODO remove
    for (VrstaPaketa vrstaPaketa : vrstePaketa) {
      System.out.println(vrstaPaketa.oznaka);
    }
  }

  public String dajPostavku(String kljuc) {
    return postavke.getProperty(kljuc);
  }
}
