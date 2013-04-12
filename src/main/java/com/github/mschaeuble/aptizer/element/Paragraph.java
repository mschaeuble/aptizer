package com.github.mschaeuble.aptizer.element;

import java.util.ArrayList;
import java.util.List;

import static com.github.mschaeuble.aptizer.util.Consts.INDENTATION;
import static com.github.mschaeuble.aptizer.util.Consts.NEW_LINE;

public class Paragraph implements AptElement {

  private final List<String> lines = new ArrayList<String>();
  
  public Paragraph(String text) {
    addLine(text);
  }

  public Paragraph addLine(String text) {
    lines.add(text);
    return this;
  }

  public String render() {
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      
      sb.append(INDENTATION).append(line);
      
      boolean isNotLastLine = i < lines.size() - 1;
      if (isNotLastLine) {
        sb.append(NEW_LINE);
      }
    }
    
    return sb.toString();
  }
  
}
