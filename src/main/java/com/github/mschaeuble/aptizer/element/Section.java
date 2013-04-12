package com.github.mschaeuble.aptizer.element;

import static com.github.mschaeuble.aptizer.util.Consts.SPACE;
import static com.github.mschaeuble.aptizer.util.Consts.ASTERISK;
import static com.github.mschaeuble.aptizer.util.Preconditions.checkArgument;

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
      sb.append(ASTERISK);
    }
    
    if (level > 1) {
      sb.append(SPACE);
    }
    
    sb.append(sectionTitle);
    
    return sb.toString();
  }

}
