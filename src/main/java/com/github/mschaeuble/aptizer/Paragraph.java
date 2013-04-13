package com.github.mschaeuble.aptizer;

import java.util.ArrayList;
import java.util.List;

import com.github.mschaeuble.aptizer.util.Escaper;

import static com.github.mschaeuble.aptizer.util.Consts.INDENTATION;
import static com.github.mschaeuble.aptizer.util.Consts.NEW_LINE;
import static com.github.mschaeuble.aptizer.util.Preconditions.checkNotNull;

public class Paragraph extends AptElement {

  private final List<String> lines = new ArrayList<String>();
  
  public Paragraph(String text) {
    checkNotNull(text, "text must never be null");
    
    lines.add(Escaper.escape(text));
  }
  
  public Paragraph(Text formattedText) {
    checkNotNull(formattedText, "formattedText must never be null");
    
    lines.add(formattedText.render());
  }

  public Paragraph addLine(String text) {
    checkNotNull(text, "text must never be null");
    
    lines.add(Escaper.escape(text));
    return this;
  }

  String render() {
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
