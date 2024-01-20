package org.foi.uzdiz.mkovac.zadaca_3.visitor;

public interface PaketElement {
  public String[] accept(PaketVisitor visitor);
}
