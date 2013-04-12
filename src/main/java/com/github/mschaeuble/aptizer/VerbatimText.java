package com.github.mschaeuble.aptizer;

import static com.github.mschaeuble.aptizer.util.Consts.NEW_LINE;
import static com.github.mschaeuble.aptizer.util.Consts.THREE_DASHES;
import static com.github.mschaeuble.aptizer.util.Preconditions.checkNotNull;

public class VerbatimText extends AptElement {

  public enum Style {
    /** Without a box */
    UNFRAMED,
    
    /** With box */
    FRAMED;
    
    private static final String FRAMED_MARKUP = "+--+";
    
    private String render() {
      switch (this) {
        case UNFRAMED:
          return THREE_DASHES;
        case FRAMED:
          return FRAMED_MARKUP;
        default:
          String msg = String.format("Style '%s' is not implemented", this);
          throw new UnsupportedOperationException(msg);
      }
    }
  }
  
  private final String text;
  private final Style style;
  
  public VerbatimText(String text, Style style) {
    checkNotNull(text, "text must never be null");
    checkNotNull(style, "style must never be null");
    
    this.text = text;
    this.style = style;
  }
  
  String render() {
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
