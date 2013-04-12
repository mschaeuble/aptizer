package com.github.mschaeuble.aptizer.element;

import java.util.ArrayList;

import static com.github.mschaeuble.aptizer.util.Consts.ASTERISK;
import static com.github.mschaeuble.aptizer.util.Consts.INDENTATION;
import static com.github.mschaeuble.aptizer.util.Consts.NEW_LINE;
import static com.github.mschaeuble.aptizer.util.Consts.SPACE;

public class List implements AptElement {

  public enum Style {
    /** bullet points. */
    BULLETS,
    
    /** Decimal numbering: 1, 2, 3, 4, , etc. */
    DECIMAL,
    
    /** Lower-alpha numbering: a, b, c, d, etc. */
    LOWER_ALPHA,
    
    /** Upper-alpha numbering: A, B, C, D, etc. */
    UPPER_ALPHA,
    
    /** Lower-roman numbering: i, ii, iii, iv, etc. */
    LOWER_ROMAN,
    
    /** Upper-roman numbering: I, II, III, IV, etc. */
    UPPER_ROMAN
  }
  
  private final java.util.List<String> items = new ArrayList<String>();
  private final Style style;
  
  public List(Style style) {
    this.style = style;
  }
  
  public List addItem(String itemText) {
    items.add(itemText);
    return this;
  }
  
  public String render() {
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < items.size(); i++) {
      sb.append(INDENTATION).
         append(getRenderedListStyle()).
         append(SPACE).
         append(items.get(i)).
         append(NEW_LINE).
         append(NEW_LINE);
      
      boolean isLast = i == items.size() - 1;
      if (isLast) {
        sb.append(INDENTATION).
           append("[]");
      }
    }
    
    return sb.toString();
  }
  
  private String getRenderedListStyle() {
    switch (style) {
      case BULLETS:
        return ASTERISK;
      case DECIMAL:
        return "[[1]]";
      case LOWER_ALPHA:
        return "[[a]]";
      case UPPER_ALPHA:
        return "[[A]]";
      case LOWER_ROMAN:
        return "[[i]]";
      case UPPER_ROMAN:
        return "[[I]]";
      default:
        String msg = String.format("Style '%s' is not implemented", style);
        throw new UnsupportedOperationException(msg);
    }
  }
}
