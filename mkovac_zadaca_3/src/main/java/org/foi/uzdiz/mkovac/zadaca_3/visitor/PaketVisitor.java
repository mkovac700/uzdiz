package org.foi.uzdiz.mkovac.zadaca_3.visitor;

import org.foi.uzdiz.mkovac.zadaca_3.builder.Paket;

public interface PaketVisitor {
  public String[] visit(Paket paket);
}
