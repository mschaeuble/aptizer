package com.github.mschaeuble.aptizer.element;

import static com.github.mschaeuble.aptizer.util.Consts.NEW_LINE;
import static com.github.mschaeuble.aptizer.util.Consts.THREE_DASHES;

public class VerbatimText implements AptElement {

  public enum Style {
    /** Without a box */
    UNFRAMED,
    
    /** With box */
    FRAMED;
    
    private String render() {
      switch (this) {
        case UNFRAMED:
          return THREE_DASHES;
        case FRAMED:
          return "+--+";
        default:
          String msg = String.format("Style '%s' is not implemented", this);
          throw new UnsupportedOperationException(msg);
      }
    }
  }
  
  private final String text;
  private final Style style;
  
  public VerbatimText(String text, Style style) {
    this.text = text;
    this.style = style;
  }
  
  public String render() {
    StringBuilder sb = new StringBuilder();
    
    String renderedFrameStyle = style.render();
    
    sb.append(renderedFrameStyle).
       append(NEW_LINE).
       append(text).
       append(NEW_LINE).
       append(renderedFrameStyle);
    
    return sb.toString();
  }
  
}
