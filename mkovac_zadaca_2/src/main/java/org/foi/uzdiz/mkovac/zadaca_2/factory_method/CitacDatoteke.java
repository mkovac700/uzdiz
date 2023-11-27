package org.foi.uzdiz.mkovac.zadaca_2.factory_method;

import java.io.IOException;
import java.util.List;

public interface CitacDatoteke<T> {
  List<T> citajDatoteku(String nazivDatoteke) throws IOException;
}
