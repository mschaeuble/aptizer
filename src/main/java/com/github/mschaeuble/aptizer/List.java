package com.github.mschaeuble.aptizer;

import java.util.ArrayList;

import com.github.mschaeuble.aptizer.util.Escaper;

import static com.github.mschaeuble.aptizer.util.Consts.ASTERISK;
import static com.github.mschaeuble.aptizer.util.Consts.INDENTATION;
import static com.github.mschaeuble.aptizer.util.Consts.NEW_LINE;
import static com.github.mschaeuble.aptizer.util.Consts.SPACE;
import static com.github.mschaeuble.aptizer.util.Preconditions.checkNotNull;

public class List extends AptElement {

  private static final String END_OF_LIST_MARKUP = "[]";
  
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
    UPPER_ROMAN;
    
    private final String DECIMAL_LIST_MARKUP = "[[1]]";
    private final String LOWER_ALPHA_MARKUP  = "[[a]]";
    private final String UPPER_ALPHA_MARKUP  = "[[A]]";
    private final String LOWER_ROMAN_MARKUP  = "[[i]]";
    private final String UPPER_ROMAN_MARKUP  = "[[I]]";
    
    private String render() {
      switch (this) {
        case BULLETS:
          return ASTERISK;
        case DECIMAL:
          return DECIMAL_LIST_MARKUP;
        case LOWER_ALPHA:
          return LOWER_ALPHA_MARKUP;
        case UPPER_ALPHA:
          return UPPER_ALPHA_MARKUP;
        case LOWER_ROMAN:
          return LOWER_ROMAN_MARKUP;
        case UPPER_ROMAN:
          return UPPER_ROMAN_MARKUP;
        default:
          String msg = String.format("Style '%s' is not implemented", this);
          throw new UnsupportedOperationException(msg);
      }
    }
  }
  
  private final java.util.List<String> items = new ArrayList<String>();
  private final Style style;
  
  public List(Style style) {
    checkNotNull(style, "style must never be null");
    
    this.style = style;
  }
  
  public List addItem(String itemText) {
    checkNotNull(itemText, "itemText must never be null");
    
    items.add(Escaper.escape(itemText));
    return this;
  }
  
  public List addParagraph(Paragraph paragraph) {
    return addSubElement(paragraph);
  }
  
  public List addList(List list) {
    return addSubElement(list);
  }

  private List addSubElement(AptElement element) {
    checkNotNull(element, "argument must never be null");
    
    int lastItemIndex = items.size() - 1;
    
    if (lastItemIndex < 0) {
      throw new IllegalStateException("There must be an item in the list before you can add sub element");
    }
    
    String lastItemAdded = items.get(lastItemIndex);
    String newItem = lastItemAdded + NEW_LINE + NEW_LINE + INDENTATION + element.render();
    items.set(lastItemIndex, newItem);

    return this;
  }
  
  public AptElement addItem(Text formattedText) {
    checkNotNull(formattedText, "formattedText must never be null");
    
    items.add(formattedText.render());
    return this;
  }
  
  String render() {
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < items.size(); i++) {
      sb.append(INDENTATION).
         append(style.render()).
         append(SPACE).
         append(items.get(i)).
         append(NEW_LINE).
         append(NEW_LINE);
      
      boolean isLast = i == items.size() - 1;
      if (isLast) {
        sb.append(INDENTATION).
           append(END_OF_LIST_MARKUP);
      }
    }
    
    return sb.toString();
  }

}
