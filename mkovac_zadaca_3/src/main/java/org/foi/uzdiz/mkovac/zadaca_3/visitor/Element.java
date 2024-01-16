package org.foi.uzdiz.mkovac.zadaca_3.visitor;

public interface Element {
  public String[] accept(Visitor visitor);
}
