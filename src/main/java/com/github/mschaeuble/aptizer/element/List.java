package com.github.mschaeuble.aptizer.element;

import java.util.ArrayList;

import static com.github.mschaeuble.aptizer.util.Consts.ASTERISK;
import static com.github.mschaeuble.aptizer.util.Consts.INDENTATION;
import static com.github.mschaeuble.aptizer.util.Consts.NEW_LINE;
import static com.github.mschaeuble.aptizer.util.Consts.SPACE;

public class List implements AptElement {

  private java.util.List<String> items = new ArrayList<String>();
  
  public List() {
  }
  
  public List addItem(String itemText) {
    items.add(itemText);
    return this;
  }
  
  public String render() {
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < items.size(); i++) {
      sb.append(INDENTATION).
         append(ASTERISK).
         append(SPACE).
         append(items.get(i));
      
      boolean isNotLast = i < items.size() - 1;
      if (isNotLast) {
        sb.append(NEW_LINE)
          .append(NEW_LINE);
      }
    }
    
    return sb.toString();
  }

}
