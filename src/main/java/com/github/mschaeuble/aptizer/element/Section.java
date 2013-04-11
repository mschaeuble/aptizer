/*
 * Copyright (C) 2013 by Netcetera AG.
 * All rights reserved.
 *
 * The copyright to the computer program(s) herein is the property of Netcetera AG, Switzerland.
 * The program(s) may be used and/or copied only with the written permission of Netcetera AG or
 * in accordance with the terms and conditions stipulated in the agreement/contract under which 
 * the program(s) have been supplied.
 */
package com.github.mschaeuble.aptizer.element;

import static com.github.mschaeuble.aptizer.util.Preconditions.*;

public class Section implements AptElement {

  private final String sectionTitle;
  private final int level;
  
  /**
   * Creates a first level section title.
   */
  public Section(String sectionTitle) {
    this.sectionTitle = sectionTitle;
    this.level = 1;
  }
  
  /**
   * Creates a section title of the given level.
   * As an example, if level is set to 2, a second level title
   * will be created.
   * 
   * The level must always be >= 1
   */
  public Section(String sectionTitle, int level) {
    checkArgument(level >= 1, "level must be >= 1");
    
    this.sectionTitle = sectionTitle;
    this.level = level;
  }
  
  public String render() {
    StringBuilder sb = new StringBuilder();
    
    for (int i=1; i < level; i++) {
      sb.append("*");
    }
    
    if (level > 1) {
      sb.append(" ");
    }
    
    sb.append(sectionTitle);
    
    return sb.toString();
  }

}
