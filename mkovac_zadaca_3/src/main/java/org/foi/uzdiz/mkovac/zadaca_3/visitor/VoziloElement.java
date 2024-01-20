package org.foi.uzdiz.mkovac.zadaca_3.visitor;

public interface VoziloElement {
  public String[] accept(VoziloVisitor visitor);
}
