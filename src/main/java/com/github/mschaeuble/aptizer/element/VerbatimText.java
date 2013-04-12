package com.github.mschaeuble.aptizer.element;

import static com.github.mschaeuble.aptizer.util.Consts.NEW_LINE;
import static com.github.mschaeuble.aptizer.util.Consts.THREE_DASHES;

public class VerbatimText implements AptElement {

  private String text;
  
  public VerbatimText(String text) {
    this.text = text;
  }
  
  public String render() {
    StringBuilder sb = new StringBuilder();
    
    sb.append(THREE_DASHES).
       append(NEW_LINE).
       append(text).
       append(NEW_LINE).
       append(THREE_DASHES);
    
    return sb.toString();
  }

}
