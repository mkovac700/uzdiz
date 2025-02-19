package org.foi.uzdiz.mkovac.zadaca_3.singleton;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import org.foi.uzdiz.mkovac.zadaca_3.builder.Osoba;
import org.foi.uzdiz.mkovac.zadaca_3.builder.Paket;
import org.foi.uzdiz.mkovac.zadaca_3.builder.Podrucje;
import org.foi.uzdiz.mkovac.zadaca_3.builder.Vozilo;
import org.foi.uzdiz.mkovac.zadaca_3.composite.LokacijaComponent;
import org.foi.uzdiz.mkovac.zadaca_3.composite.MjestoComposite;
import org.foi.uzdiz.mkovac.zadaca_3.composite.UlicaLeaf;
import org.foi.uzdiz.mkovac.zadaca_3.iznimke.NeispravniParametri;
import org.foi.uzdiz.mkovac.zadaca_3.memento.TvrtkaMemento;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.Parametar;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.Segment;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.Voznja;
import org.foi.uzdiz.mkovac.zadaca_3.pomocnici.DatumskoVremenskiKonverter;
import org.foi.uzdiz.mkovac.zadaca_3.pomocnici.ParametriProvjera;
import org.foi.uzdiz.mkovac.zadaca_3.prototype.VrstaPaketa;
import org.foi.uzdiz.mkovac.zadaca_3.strategy.IsporukaNajblizaDostava;
import org.foi.uzdiz.mkovac.zadaca_3.strategy.IsporukaRedoslijed;
import org.foi.uzdiz.mkovac.zadaca_3.strategy.IsporukaStrategy;
import org.foi.uzdiz.mkovac.zadaca_3.template_method.CitacDatoteke;
import org.foi.uzdiz.mkovac.zadaca_3.template_method.CitacMjesta;
import org.foi.uzdiz.mkovac.zadaca_3.template_method.CitacOsoba;
import org.foi.uzdiz.mkovac.zadaca_3.template_method.CitacPaketa;
import org.foi.uzdiz.mkovac.zadaca_3.template_method.CitacParametara;
import org.foi.uzdiz.mkovac.zadaca_3.template_method.CitacPodrucja;
import org.foi.uzdiz.mkovac.zadaca_3.template_method.CitacUlica;
import org.foi.uzdiz.mkovac.zadaca_3.template_method.CitacVozila;
import org.foi.uzdiz.mkovac.zadaca_3.template_method.CitacVrstaPaketa;
import org.foi.uzdiz.mkovac.zadaca_3.visitor.VoziloVisitorImpl;

public class TvrtkaSingleton {
  private static volatile TvrtkaSingleton INSTANCE = new TvrtkaSingleton();

  private UredPrijem uredPrijem;
  private UredDostava uredDostava;

  private ParametriProvjera parametriProvjera;

  private TvrtkaSingleton() {
    uredPrijem = new UredPrijem();
    uredDostava = new UredDostava();
    parametriProvjera = new ParametriProvjera();
  }

  public static TvrtkaSingleton getInstance() {
    return INSTANCE;
  }

  private Properties postavke;

  public LocalDateTime virtualniSat;
  public int mnoziteljSekunde;
  public LocalTime pocetakRada;
  public LocalTime krajRada;

  public String gpsUreda;
  public int vrijemeIzvrsavanja;
  public int isporuka;

  private List<Parametar> parametri;
  private List<VrstaPaketa> vrstePaketa;
  private List<UlicaLeaf> ulice;
  private List<MjestoComposite> mjesta;
  private List<Podrucje> podrucja;
  private List<Vozilo> vozila;
  private List<Osoba> osobe;
  private List<Paket> paketi;

  public volatile boolean KRAJ = false;

  private float prikupljeniNovac;

  public TvrtkaMemento createMemento() {
    LocalDateTime virtualniSatCopy = LocalDateTime.of(virtualniSat.getYear(),
        virtualniSat.getMonth(), virtualniSat.getDayOfMonth(), virtualniSat.getHour(),
        virtualniSat.getMinute(), virtualniSat.getSecond());

    List<Paket> paketiCopy = new ArrayList<>();

    paketiCopy = this.paketi.stream().map(Paket::new).collect(Collectors.toList());

    // TODO dovrsit
    /*
     * List<Vozilo> vozilaCopy = new ArrayList<>(); for (Vozilo vozilo : vozila) { Vozilo _vozilo =
     * new Vozilo(); for (Voznja voznja : vozilo.getVoznje()) { Voznja _voznja = new Voznja(); for
     * (Segment segment : voznja.getSegmenti()) { Segment _segment = new Segment(); } } }
     */

    return new TvrtkaMemento(virtualniSatCopy, paketiCopy, null);
  }

  public void restoreMemento(TvrtkaMemento memento) {
    if (memento != null) {
      this.paketi = memento.getPaketi();
      this.vozila = memento.getVozila();
      this.virtualniSat = memento.getVirtualniSat();
    } else {
      System.out.println("Memento je null");
    }
  }

  public String getVirtualniSatFormatirano() {
    return DatumskoVremenskiKonverter.konvertirajDatumVrijeme(virtualniSat);
  }

  public void init(String argument) throws IOException, NeispravniParametri {
    postavke = new Properties();

    this.ucitajParametre(argument);
    this.ucitajVrstePaketa(postavke.getProperty("vp"));
    this.ucitajUlice(postavke.getProperty("pu"));
    this.ucitajMjesta(postavke.getProperty("pm"));
    this.ucitajPodrucja(postavke.getProperty("pmu"));
    this.ucitajVozila(postavke.getProperty("pv"));
    this.ucitajOsobe(postavke.getProperty("po"));
    this.ucitajPakete(postavke.getProperty("pp"));

    this.izdvojiPostavke();

    uredDostava.setPodrucja(podrucja);
    uredDostava.setVozila(vozila);
  }

  private void izdvojiPostavke() {
    String datumVrijeme = postavke.getProperty("vs");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
    virtualniSat = LocalDateTime.parse(datumVrijeme, dtf);

    mnoziteljSekunde = Integer.parseInt(postavke.getProperty("ms"));

    String vrijemePocetak = postavke.getProperty("pr");
    String vrijemeKraj = postavke.getProperty("kr");
    DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
    pocetakRada = LocalTime.parse(vrijemePocetak, tf);
    krajRada = LocalTime.parse(vrijemeKraj, tf);

    gpsUreda = postavke.getProperty("gps");
    vrijemeIzvrsavanja = Integer.parseInt(postavke.getProperty("vi"));
    isporuka = Integer.parseInt(postavke.getProperty("isporuka"));
  }

  private void ucitajParametre(String datoteka) throws IOException, NeispravniParametri {
    CitacDatoteke<Parametar> citac = new CitacParametara();
    parametri = citac.citajDatoteku(datoteka);

    for (Parametar parametar : parametri) {
      if (!postavke.containsKey(parametar.getKljuc()))
        postavke.put(parametar.getKljuc(), parametar.getVrijednost());
      else
        throw new NeispravniParametri("Datoteka parametara sadrži duplikat ključa!");
    }

    if (!parametriProvjera.provjeriParametre(postavke))
      throw new NeispravniParametri(
          "Datoteka parametara ne sadrži sve parametre ili parametri nisu ispravni!");

  }

  private void ucitajVrstePaketa(String datoteka) throws IOException {
    CitacDatoteke<VrstaPaketa> citac = new CitacVrstaPaketa();
    vrstePaketa = citac.citajDatoteku(datoteka);
  }

  private void ucitajVozila(String datoteka) throws IOException {
    CitacDatoteke<Vozilo> citac = new CitacVozila();
    vozila = citac.citajDatoteku(datoteka);
  }

  private void ucitajUlice(String datoteka) throws IOException {
    CitacDatoteke<UlicaLeaf> citac = new CitacUlica();
    ulice = citac.citajDatoteku(datoteka);
  }

  private void ucitajMjesta(String datoteka) throws IOException {
    CitacDatoteke<MjestoComposite> citac = new CitacMjesta();
    mjesta = citac.citajDatoteku(datoteka);
  }

  private void ucitajPodrucja(String datoteka) throws IOException {
    CitacDatoteke<Podrucje> citac = new CitacPodrucja();
    podrucja = citac.citajDatoteku(datoteka);
  }

  private void ucitajOsobe(String datoteka) throws IOException {
    CitacDatoteke<Osoba> citac = new CitacOsoba();
    osobe = citac.citajDatoteku(datoteka);

  }

  private void ucitajPakete(String datoteka) throws IOException {
    CitacDatoteke<Paket> citac = new CitacPaketa();
    paketi = citac.citajDatoteku(datoteka);

  }

  public String dajPostavku(String kljuc) {
    return postavke.getProperty(kljuc);
  }

  public List<UlicaLeaf> getUlice() {
    return ulice;
  }

  public List<MjestoComposite> getMjesta() {
    return mjesta;
  }

  public List<Podrucje> getPodrucja() {
    return podrucja;
  }

  public List<Osoba> getOsobe() {
    return osobe;
  }

  public List<VrstaPaketa> getVrstePaketa() {
    return vrstePaketa;
  }

  public List<Paket> getPaketi() {
    return paketi;
  }

  public void ispisPodrucja() {
    for (Podrucje podrucje : podrucja) {
      System.out.println("Područje " + podrucje.getId());
      for (LokacijaComponent mjesto : podrucje.getSvaMjesta().dajLokacije()) {
        System.out.println("    " + ((MjestoComposite) mjesto).getId() + ": "
            + ((MjestoComposite) mjesto).getNaziv());
        for (LokacijaComponent ulica : mjesto.dajLokacije()) {
          System.out.println(
              "        " + ((UlicaLeaf) ulica).getId() + ": " + ((UlicaLeaf) ulica).getNaziv());
        }
      }
    }
  }

  public void promjeniStatusSlanjaObavijesti(String osoba, String oznakaPaketa, String naredba) {

    if (paketi.stream().anyMatch(p -> p.getOznaka().equals(oznakaPaketa))) {
      Paket paket =
          paketi.stream().filter(p -> p.getOznaka().equals(oznakaPaketa)).findFirst().get();

      if (paket.getPosiljatelj().getOsoba().equals(osoba)) {
        Osoba o = paket.getPosiljatelj();

        if (naredba.equals("N")) {
          paket.removeObserver(o);

          System.out.println("Pošiljatelj " + o.getOsoba() + " ne želi primati obavijesti za paket "
              + paket.getOznaka());
        } else {
          paket.addObserver(o);

          System.out.println("Pošiljatelj " + o.getOsoba() + " želi primati obavijesti za paket "
              + paket.getOznaka());
        }

      }

      else if (paket.getPrimatelj().getOsoba().equals(osoba)) {
        Osoba o = paket.getPrimatelj();

        if (naredba.equals("N")) {
          paket.removeObserver(o);

          System.out.println("Primatelj " + o.getOsoba() + " ne želi primati obavijesti za paket "
              + paket.getOznaka());
        } else {
          paket.addObserver(o);

          System.out.println("Primatelj " + o.getOsoba() + " želi primati obavijesti za paket "
              + paket.getOznaka());
        }
      } else {
        System.out.println("Osoba " + osoba + " nije primatelj/pošiljatelj paketa!");
      }
    } else {
      System.out.println("Paket " + oznakaPaketa + " nije pronađen!");
    }

  }

  public void promijeniStatusVozila(String registracija, String status) {
    if (vozila.stream().anyMatch(v -> v.getRegistracija().equals(registracija))) {
      Vozilo vozilo =
          vozila.stream().filter(v -> v.getRegistracija().equals(registracija)).findFirst().get();

      if (status.equals("A"))
        vozilo.aktiviraj();
      if (status.equals("NA"))
        vozilo.deaktiviraj();
      if (status.equals("NI"))
        vozilo.setNeispravno();
    } else {
      System.out.println("Vozilo nije pronađeno!");
    }
  }

  public void ispisiSegmente(String registracija, String brojVoznje) {
    final int RAZMAK_KRACI = 3;
    final int RAZMAK_DUZI = 20;

    if (!this.vozila.stream().anyMatch(v -> v.getRegistracija().equals(registracija))) {
      System.out.println("Ne postoji vozilo " + registracija);
      return;
    }

    List<Voznja> voznje = this.vozila.stream().filter(v -> v.getRegistracija().equals(registracija))
        .findFirst().get().getVoznje();

    int n = 0;

    try {
      n = Integer.parseInt(brojVoznje);
    } catch (NumberFormatException e) {
      System.out.println("Neispravan broj vožnje!");
      return;
    }

    if (n < 1) {
      System.out.println("Neispravan broj vožnje!");
      return;
    }
    if (voznje.isEmpty()) {
      System.out.println("Nema podataka za prikaz!");
      return;
    }
    if (n > voznje.size()) {
      System.out.println("Nema podataka o vožnji " + n + " za vozilo " + registracija
          + ". Ukupan broj vožnji trenutno: " + voznje.size());
      return;
    }

    Voznja trazenaVoznja = voznje.get(n - 1);

    VoziloVisitorImpl podaciVoziloVisitorImpl = new VoziloVisitorImpl();

    String[] zaglavlje =
        {"#", "VRIJEME POČETKA", "VRIJEME KRAJA", "TRAJANJE", "ODVOŽENO KM", "PAKET"};

    // PRINTAJ GORNJI BORDER ZAGLAVLJA
    for (int i = 0; i <= zaglavlje.length; i++) {
      System.out.print("+");
      if (i == zaglavlje.length)
        break;
      for (int j = 0; i != 0 ? j < RAZMAK_DUZI : j < RAZMAK_KRACI; j++) {
        System.out.print("-");
      }
    }

    System.out.println();

    // PRINTAJ ZAGLAVLJE

    System.out.print("|");

    int totalSpaces;
    int padding;

    int razmak;

    for (int i = 0; i < zaglavlje.length; i++) {
      razmak = i == 0 ? RAZMAK_KRACI : RAZMAK_DUZI;
      totalSpaces = razmak - zaglavlje[i].length();
      padding = totalSpaces / 2;
      System.out.print(String.format("%-" + razmak + "s|",
          String.format("%-" + (zaglavlje[i].length() + padding) + "s",
              String.format("%" + (zaglavlje[i].length() + padding) + "s", zaglavlje[i]))));
    }

    System.out.println();

    // PRINTAJ DONJI BORDER ZAGLAVLJA
    for (int i = 0; i <= zaglavlje.length; i++) {
      System.out.print("+");
      if (i == zaglavlje.length)
        break;
      for (int j = 0; i != 0 ? j < RAZMAK_DUZI : j < RAZMAK_KRACI; j++) {
        System.out.print("-");
      }
    }

    System.out.println();

    // PRINTAJ PODATKE

    int brojac = 0;
    for (Segment s : trazenaVoznja.getSegmenti()) {
      String[] podaci = s.accept(podaciVoziloVisitorImpl);
      System.out.print("|");
      System.out.print(String.format("%-" + RAZMAK_KRACI + "s|", ++brojac));
      for (String p : podaci) {
        System.out.print(String.format("%-" + RAZMAK_DUZI + "s|", p));
      }
      System.out.println();
    }

    // PRINTAJ DONJI BORDER
    for (int i = 0; i <= zaglavlje.length; i++) {
      System.out.print("+");
      if (i == zaglavlje.length)
        break;
      for (int j = 0; i != 0 ? j < RAZMAK_DUZI : j < RAZMAK_KRACI; j++) {
        System.out.print("-");
      }
    }

    System.out.println();
  }

  public void ispisiVoznje(String registracija) {
    final int RAZMAK_KRACI = 3;
    final int RAZMAK_DUZI = 20;

    if (!this.vozila.stream().anyMatch(v -> v.getRegistracija().equals(registracija))) {
      System.out.println("Ne postoji vozilo " + registracija);
      return;
    }

    Vozilo vozilo =
        vozila.stream().filter(v -> v.getRegistracija().equals(registracija)).findFirst().get();

    List<Voznja> voznje = vozilo.getVoznje();

    if (voznje.isEmpty()) {
      System.out.println("Nema podataka za prikaz!");
      return;
    }

    VoziloVisitorImpl podaciVoziloVisitorImpl = new VoziloVisitorImpl();

    String[] zaglavlje = {"#", "VRIJEME POČETKA", "VRIJEME POVRATKA", "TRAJANJE",
        "UKUPNO ODVOŽENO KM", "BROJ HITNIH", "BROJ OBIČNIH", "BROJ ISPORUČENIH",
        "ZAUZEĆE PROSTORA (%)", "ZAUZEĆE TEŽINE (%)"};

    // PRINTAJ GORNJI BORDER ZAGLAVLJA
    for (int i = 0; i <= zaglavlje.length; i++) {
      System.out.print("+");
      if (i == zaglavlje.length)
        break;
      for (int j = 0; i != 0 ? j < RAZMAK_DUZI : j < RAZMAK_KRACI; j++) {
        System.out.print("-");
      }
    }

    System.out.println();

    // PRINTAJ ZAGLAVLJE

    System.out.print("|");

    int totalSpaces;
    int padding;

    int razmak;

    for (int i = 0; i < zaglavlje.length; i++) {
      razmak = i == 0 ? RAZMAK_KRACI : RAZMAK_DUZI;
      totalSpaces = razmak - zaglavlje[i].length();
      padding = totalSpaces / 2;
      System.out.print(String.format("%-" + razmak + "s|",
          String.format("%-" + (zaglavlje[i].length() + padding) + "s",
              String.format("%" + (zaglavlje[i].length() + padding) + "s", zaglavlje[i]))));
    }

    System.out.println();

    // PRINTAJ DONJI BORDER ZAGLAVLJA
    for (int i = 0; i <= zaglavlje.length; i++) {
      System.out.print("+");
      if (i == zaglavlje.length)
        break;
      for (int j = 0; i != 0 ? j < RAZMAK_DUZI : j < RAZMAK_KRACI; j++) {
        System.out.print("-");
      }
    }

    System.out.println();

    // PRINTAJ PODATKE

    int brojac = 0;
    for (Voznja v : vozilo.getVoznje()) {
      String[] podaci = v.accept(podaciVoziloVisitorImpl);
      System.out.print("|");
      System.out.print(String.format("%-" + RAZMAK_KRACI + "s|", ++brojac));
      for (String p : podaci) {
        System.out.print(String.format("%-" + RAZMAK_DUZI + "s|", p));
      }
      System.out.println();
    }

    // PRINTAJ DONJI BORDER
    for (int i = 0; i <= zaglavlje.length; i++) {
      System.out.print("+");
      if (i == zaglavlje.length)
        break;
      for (int j = 0; i != 0 ? j < RAZMAK_DUZI : j < RAZMAK_KRACI; j++) {
        System.out.print("-");
      }
    }

    System.out.println();
  }

  public void ispisiVozila() {
    final int RAZMAK_KRACI = 3;
    final int RAZMAK_DUZI = 20;

    if (this.vozila == null || this.vozila.isEmpty()) {
      System.out.println("Nema podataka za prikaz!");
      return;
    }

    VoziloVisitorImpl podaciVoziloVisitorImpl = new VoziloVisitorImpl();

    String[] zaglavlje =
        {"#", "REGISTRACIJA", "STATUS", "UKUPNO ODVOŽENO KM", "BROJ HITNIH", "BROJ OBIČNIH",
            "BROJ ISPORUČENIH", "ZAUZEĆE PROSTORA (%)", "ZAUZEĆE TEŽINE (%)", "BROJ VOŽNJI"};

    // PRINTAJ GORNJI BORDER ZAGLAVLJA
    for (int i = 0; i <= zaglavlje.length; i++) {
      System.out.print("+");
      if (i == zaglavlje.length)
        break;
      for (int j = 0; i != 0 ? j < RAZMAK_DUZI : j < RAZMAK_KRACI; j++) {
        System.out.print("-");
      }
    }

    System.out.println();

    // PRINTAJ ZAGLAVLJE

    System.out.print("|");

    int totalSpaces;
    int padding;

    int razmak;

    for (int i = 0; i < zaglavlje.length; i++) {
      razmak = i == 0 ? RAZMAK_KRACI : RAZMAK_DUZI;
      totalSpaces = razmak - zaglavlje[i].length();
      padding = totalSpaces / 2;
      System.out.print(String.format("%-" + razmak + "s|",
          String.format("%-" + (zaglavlje[i].length() + padding) + "s",
              String.format("%" + (zaglavlje[i].length() + padding) + "s", zaglavlje[i]))));
    }

    System.out.println();

    // PRINTAJ DONJI BORDER ZAGLAVLJA
    for (int i = 0; i <= zaglavlje.length; i++) {
      System.out.print("+");
      if (i == zaglavlje.length)
        break;
      for (int j = 0; i != 0 ? j < RAZMAK_DUZI : j < RAZMAK_KRACI; j++) {
        System.out.print("-");
      }
    }

    System.out.println();

    // PRINTAJ PODATKE

    int brojac = 0;
    for (Vozilo v : this.vozila) {
      String[] podaci = v.accept(podaciVoziloVisitorImpl);
      System.out.print("|");
      System.out.print(String.format("%-" + RAZMAK_KRACI + "s|", ++brojac));
      for (String p : podaci) {
        System.out.print(String.format("%-" + RAZMAK_DUZI + "s|", p));
      }
      System.out.println();
    }

    // PRINTAJ DONJI BORDER
    for (int i = 0; i <= zaglavlje.length; i++) {
      System.out.print("+");
      if (i == zaglavlje.length)
        break;
      for (int j = 0; i != 0 ? j < RAZMAK_DUZI : j < RAZMAK_KRACI; j++) {
        System.out.print("-");
      }
    }

    System.out.println();
  }

  public UredPrijem getUredPrijem() {
    return uredPrijem;
  }

  public UredDostava getUredDostava() {
    return uredDostava;
  }

  public float getPrikupljeniNovac() {
    return prikupljeniNovac;
  }

  public void setPrikupljeniNovac(float prikupljeniNovac) {
    this.prikupljeniNovac = prikupljeniNovac;
  }

  public void azurirajPrikupljeniNovac(float iznos) {
    this.prikupljeniNovac += iznos;
  }

  public class UredPrijem {
    // private static int brojZaprimljenihPaketa;

    /**
     * Trajno ostaju radi statistike
     */
    private List<Paket> primljeniPaketi;

    /**
     * Privremeno dok se ne ukrcaju
     */
    private List<Paket> primljeniPaketiTmp;

    public UredPrijem() {
      primljeniPaketi = new ArrayList<Paket>();
      primljeniPaketiTmp = new ArrayList<Paket>();

    }

    public List<Paket> getSviPrimljeniPaketi() {
      return primljeniPaketi;
    }

    public List<Paket> getPrimljeniPaketi() {
      return primljeniPaketiTmp;
    }

    public void zaprimiPaket(Paket paket) {
      primljeniPaketiTmp.add(paket);
      primljeniPaketi.add(paket);

      System.out.println("Zaprimljen paket " + paket.getOznaka() + " -> VRIJEME PRIJEMA: "
          + DatumskoVremenskiKonverter.konvertirajDatumVrijeme(paket.getVrijemePrijema())
          + " POŠILJATELJ: " + paket.getPosiljatelj().getOsoba() + " PRIMATELJ: "
          + paket.getPrimatelj().getOsoba());

      paket.setStatus("ZAPRIMLJEN");
    }

    public void zaprimiPakete() {
      TvrtkaSingleton tvrtka = TvrtkaSingleton.getInstance();

      Iterator<Paket> itr = tvrtka.getPaketi().iterator();

      while (itr.hasNext()) {
        Paket p = itr.next();
        if (p.getStatus().equals("") && p.getVrijemePrijema().isBefore(tvrtka.virtualniSat)) {
          tvrtka.getUredPrijem().zaprimiPaket(p);
          tvrtka.azurirajPrikupljeniNovac(p.getIznosDostave());
        }
      }
    }

    public void prebaciPaketeUUredZaDostavu() {
      UredDostava uredDostava = TvrtkaSingleton.getInstance().getUredDostava();

      Iterator<Paket> itr = primljeniPaketiTmp.iterator();

      while (itr.hasNext()) {
        Paket p = itr.next();
        uredDostava.zaprimiPaket(p);
        itr.remove();
      }
    }

  }

  public class UredDostava {

    // TODO prostor
    private List<Podrucje> podrucja;

    // TODO vozni park
    private List<Vozilo> vozila;

    private List<Paket> paketi;

    public UredDostava() {
      podrucja = new ArrayList<>();
      vozila = new ArrayList<>();
      paketi = new ArrayList<>();
    }

    public List<Podrucje> getPodrucja() {
      return podrucja;
    }

    public void setPodrucja(List<Podrucje> podrucja) {
      this.podrucja = podrucja;
    }

    public List<Vozilo> getVozila() {
      return vozila;
    }

    public void setVozila(List<Vozilo> vozila) {
      this.vozila = vozila;
    }

    public void zaprimiPaket(Paket paket) {
      this.paketi.add(paket);
    }

    public Podrucje odrediPodrucjePaketa(Paket paket) {
      MjestoComposite gradPosiljatelja = paket.getPrimatelj().getGrad();
      UlicaLeaf ulicaPosiljatelja = paket.getPrimatelj().getUlica();

      Podrucje odabranoPodrucje = null;

      for (Podrucje p : podrucja) {
        // ako u podrucju postoji grad koji odgovara gradu posiljatelja
        if (p.getSvaMjesta().dajLokacije().stream()
            .anyMatch(m -> ((MjestoComposite) m).getId() == gradPosiljatelja.getId())) {

          // ako u gradu iz podrucja postoji ulica koja odgovara ulici posiljatelja
          if (p.getSvaMjesta().dajLokacije().stream()
              .filter(m -> ((MjestoComposite) m).getId() == gradPosiljatelja.getId()).findFirst()
              .get().dajLokacije().stream()
              .anyMatch(u -> ((UlicaLeaf) u).getId() == ulicaPosiljatelja.getId())) {

            odabranoPodrucje = p;
            break;
          }
        }
      }

      if (odabranoPodrucje == null) {
        System.out.println("Za paket ne postoji područje koje pokriva ured!");
      }

      return odabranoPodrucje;

    }

    public Vozilo pronadiVoziloZaHitniPaket(Paket paket) {

      Podrucje podrucjePaketa = this.odrediPodrucjePaketa(paket);

      if (podrucjePaketa == null) {
        return null;
      } else {
        // TODO traži vozilo koje već vozi hitni paket s istim područjem
        for (Vozilo vozilo : vozila) {
          if (vozilo.getTrenutnoPodrucje() != null
              && vozilo.getStatusVoznje().getOznaka().equals("UKRCAVANJE")
              && vozilo.getTrenutnoPodrucje().equals(podrucjePaketa)
              && vozilo.getPaketi().stream().anyMatch(p -> p.getUslugaDostave().equals("H"))) {

            // ako trenutna tezina u vozilu + tezina novog paketa premasuje kapacitet vozila,
            // preskoci
            if (vozilo.izracunajTrenutnuTezinu() + paket.getTezina() > vozilo.getKapacitetTezine())
              continue;
            // ako trenutno zauzece prostora u vozilu + prostor novog paketa premasuje kapacitet
            // prostora u vozilu, preskoci
            if (vozilo.izracunajTrenutnoZauzeceProstora() + paket.getM3() > vozilo
                .getKapacitetProstora())
              continue;

            return vozilo;
          }
        }

        // TODO traži ispravno slobodno vozilo koje je najviše rangirano za područje paketa
        int minRang = -1; // zapravo onaj minimalni, tj na prvom mjestu
        int trenutniRang = 0;
        // Vozilo voziloTmp;
        List<Vozilo> tmpVozila = new ArrayList<>();

        // prvo pronadi koji je minimalni rang podrucja
        for (Vozilo vozilo : vozila) {
          if (vozilo.getStatus().getOznaka().equals("A")
              && vozilo.getStatusVoznje().getOznaka().equals("UKRCAVANJE")) {
            if (vozilo.getPodrucjaPoRangu().contains(podrucjePaketa)) {
              tmpVozila.add(vozilo);

              trenutniRang = vozilo.getPodrucjaPoRangu().indexOf(podrucjePaketa);
              if (minRang == -1)
                minRang = trenutniRang;
              else if (trenutniRang < minRang)
                minRang = trenutniRang;
            }
          }
        }

        // zatim opet prodi kroz petlju i nadi vozilo koji ima objekt podrucja na tom indeksu
        if (minRang != -1) {
          for (Vozilo vozilo : tmpVozila) {
            if (vozilo.getPodrucjaPoRangu().indexOf(podrucjePaketa) == minRang) {

              // ako trenutna tezina u vozilu + tezina novog paketa premasuje kapacitet vozila,
              // preskoci
              if (vozilo.izracunajTrenutnuTezinu() + paket.getTezina() > vozilo
                  .getKapacitetTezine())
                continue;
              // ako trenutno zauzece prostora u vozilu + prostor novog paketa premasuje kapacitet
              // prostora u vozilu, preskoci
              if (vozilo.izracunajTrenutnoZauzeceProstora() + paket.getM3() > vozilo
                  .getKapacitetProstora())
                continue;

              return vozilo;
            }
          }
        }

      }
      // nijedno odgovarajuće vozilo nije pronađeno (paket čeka idući puni sat)
      return null;
    }

    public Vozilo pronadiVoziloZaOstalePakete(Paket paket) {

      Podrucje podrucjePaketa = this.odrediPodrucjePaketa(paket);

      if (podrucjePaketa == null) {
        return null;
      } else {
        // TODO traži vozilo koje već vozi paket (koji nije hitan) s istim područjem
        for (Vozilo vozilo : vozila) {
          if (vozilo.getTrenutnoPodrucje() != null
              && vozilo.getStatusVoznje().getOznaka().equals("UKRCAVANJE")
              && vozilo.getTrenutnoPodrucje().equals(podrucjePaketa)
              && vozilo.getPaketi().stream().anyMatch(p -> !p.getUslugaDostave().equals("H"))) {

            // ako trenutna tezina u vozilu + tezina novog paketa premasuje kapacitet vozila,
            // preskoci
            if (vozilo.izracunajTrenutnuTezinu() + paket.getTezina() > vozilo.getKapacitetTezine())
              continue;
            // ako trenutno zauzece prostora u vozilu + prostor novog paketa premasuje kapacitet
            // prostora u vozilu, preskoci
            if (vozilo.izracunajTrenutnoZauzeceProstora() + paket.getM3() > vozilo
                .getKapacitetProstora())
              continue;

            return vozilo;
          }
        }

        // TODO traži ispravno slobodno vozilo koje je najviše rangirano za područje paketa
        int minRang = -1; // zapravo onaj minimalni, tj na prvom mjestu
        int trenutniRang = 0;
        // Vozilo voziloTmp;
        List<Vozilo> tmpVozila = new ArrayList<>();

        // prvo pronadi koji je minimalni rang podrucja
        for (Vozilo vozilo : vozila) {
          if (vozilo.getStatus().getOznaka().equals("A")
              && vozilo.getStatusVoznje().getOznaka().equals("UKRCAVANJE")) {
            if (vozilo.getPodrucjaPoRangu().contains(podrucjePaketa)) {
              tmpVozila.add(vozilo);

              trenutniRang = vozilo.getPodrucjaPoRangu().indexOf(podrucjePaketa);
              if (minRang == -1)
                minRang = trenutniRang;
              else if (trenutniRang < minRang)
                minRang = trenutniRang;
            }
          }
        }

        // zatim opet prodi kroz petlju i nadi vozilo koji ima objekt podrucja na tom indeksu
        if (minRang != -1) {
          for (Vozilo vozilo : tmpVozila) {
            if (vozilo.getPodrucjaPoRangu().indexOf(podrucjePaketa) == minRang) {

              // ako trenutna tezina u vozilu + tezina novog paketa premasuje kapacitet vozila,
              // preskoci
              if (vozilo.izracunajTrenutnuTezinu() + paket.getTezina() > vozilo
                  .getKapacitetTezine())
                continue;
              // ako trenutno zauzece prostora u vozilu + prostor novog paketa premasuje kapacitet
              // prostora u vozilu, preskoci
              if (vozilo.izracunajTrenutnoZauzeceProstora() + paket.getM3() > vozilo
                  .getKapacitetProstora())
                continue;

              return vozilo;
            }
          }
        }

      }
      // nijedno odgovarajuće vozilo nije pronađeno (paket čeka idući puni sat)
      return null;
    }

    public boolean utovariHitniPaket(Paket paket) {
      Vozilo vozilo = this.pronadiVoziloZaHitniPaket(paket);
      Podrucje podrucje = this.odrediPodrucjePaketa(paket);
      if (vozilo == null) {
        System.out.println("Nije pronađeno nijedno slobodno vozilo za paket " + paket.getOznaka());
        return false;
      } else {
        vozilo.ukrcajPaket(paket, podrucje);
        System.out
            .println("Paket " + paket.getOznaka() + " ukrcan u vozilo " + vozilo.getRegistracija());
        paket.setStatus("UKRCAN U VOZILO");
        return true;
      }
    }

    public boolean utovariObicniPaket(Paket paket) {
      Vozilo vozilo = this.pronadiVoziloZaOstalePakete(paket);
      Podrucje podrucje = this.odrediPodrucjePaketa(paket);
      if (vozilo == null) {
        System.out.println("Nije pronađeno nijedno slobodno vozilo za paket " + paket.getOznaka());
        return false;
      } else {
        vozilo.ukrcajPaket(paket, podrucje);
        System.out
            .println("Paket " + paket.getOznaka() + " ukrcan u vozilo " + vozilo.getRegistracija());
        paket.setStatus("UKRCAN U VOZILO");
        return true;
      }
    }

    public void utovariHitnePakete() {

      Iterator<Paket> itr = paketi.iterator();

      while (itr.hasNext()) {
        Paket p = itr.next();
        if (p.getUslugaDostave().equals("H")) {
          if (utovariHitniPaket(p))
            itr.remove();
        }
      }
    }

    public void utovariOstalePakete() {
      Iterator<Paket> itr = paketi.iterator();

      while (itr.hasNext()) {
        Paket p = itr.next();
        if (!p.getUslugaDostave().equals("H")) {
          if (utovariObicniPaket(p))
            itr.remove();
        }
      }
    }

    public void odrediVrstuIsporuke(Vozilo vozilo) {
      switch (isporuka) {
        case 1:
          IsporukaStrategy isporukaRedoslijed = new IsporukaRedoslijed();
          vozilo.setIsporukaStrategy(isporukaRedoslijed);
          vozilo.odrediVrstuIsporuke();
          break;

        case 2:
          IsporukaStrategy isporukaNajblizaDostava = new IsporukaNajblizaDostava();
          vozilo.setIsporukaStrategy(isporukaNajblizaDostava);
          vozilo.odrediVrstuIsporuke();

          break;

        default:
          break;
      }
    }

    public void obaviIsporuku(Vozilo vozilo) {
      vozilo.obaviIsporuku();
    }

    public void pokreniIsporuku() {
      for (Vozilo vozilo : vozila) {

        if (vozilo.getStatus().getOznaka().equals("A")
            && vozilo.getStatusVoznje().getOznaka().equals("UKRCAVANJE")
            && vozilo.getTrenutnaVoznja() != null) {
          odrediVrstuIsporuke(vozilo); // tu se zapravo izrošadiraju paketi ovisno o strategiji
          // TODO pokreniVozila() ili bolje receno obaviIsporuku()
          obaviIsporuku(vozilo);

        }

        // ako je vec u statusu isporuke, onda nastavi isporucivat
        if (vozilo.getStatus().getOznaka().equals("A")
            && vozilo.getStatusVoznje().getOznaka().equals("ISPORUKA")) {
          // TODO obaviIsporuku()
          obaviIsporuku(vozilo);
        }

        // ako je vozilo pak u povratku, onda provjeri vrijeme kad bi se trebalo vratit te mu
        // promijeni status natrag u ukrcavanje ako se vratilo
        if (vozilo.getStatus().getOznaka().equals("A")
            && vozilo.getStatusVoznje().getOznaka().equals("POVRATAK")) {

          if (vozilo.getVrijemePovratka().isBefore(virtualniSat)
              || vozilo.getVrijemePovratka().isEqual(virtualniSat)) {
            vozilo.setUkrcavanje();
          }
        }


      }


    }
  }
}
