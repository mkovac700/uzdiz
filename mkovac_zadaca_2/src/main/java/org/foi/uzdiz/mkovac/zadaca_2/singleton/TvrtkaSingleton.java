package org.foi.uzdiz.mkovac.zadaca_2.singleton;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

  public LocalDateTime virtualniSat;
  public int mnoziteljSekunde;
  public LocalTime pocetakRada;
  public LocalTime krajRada;


  private List<Parametar> parametri;
  private List<VrstaPaketa> vrstePaketa;

  public String getVirtualniSatFormatirano() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
    String virtualniSatFormatirano = virtualniSat.format(dtf);
    return virtualniSatFormatirano;
  }

  public void init(String argument) throws IOException, NeispravniParametri {
    this.ucitajParametre(argument);
    this.ucitajVrstePaketa(postavke.getProperty("vp"));

    // TODO izdvoji postavke za tvrtku:
    String datumVrijeme = postavke.getProperty("vs");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
    virtualniSat = LocalDateTime.parse(datumVrijeme, dtf);

    mnoziteljSekunde = Integer.parseInt(postavke.getProperty("ms"));

    String vrijemePocetak = postavke.getProperty("pr");
    String vrijemeKraj = postavke.getProperty("kr");
    DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
    pocetakRada = LocalTime.parse(vrijemePocetak, tf);
    krajRada = LocalTime.parse(vrijemeKraj, tf);
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
    else {
      // TODO remove
      System.out.println("Parametri OK");
    }
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
