package org.foi.uzdiz.mkovac.zadaca_3.visitor;

import org.foi.uzdiz.mkovac.zadaca_3.builder.Vozilo;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.Segment;
import org.foi.uzdiz.mkovac.zadaca_3.podaci.Voznja;

public interface VoziloVisitor {

  public String[] visit(Segment segment);

  public String[] visit(Voznja voznja);

  public String[] visit(Vozilo vozilo);

}
