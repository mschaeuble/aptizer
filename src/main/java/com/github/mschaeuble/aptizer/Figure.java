package com.github.mschaeuble.aptizer;

import com.github.mschaeuble.aptizer.util.Escaper;

import static com.github.mschaeuble.aptizer.util.Preconditions.checkNotNull;


public class Figure extends AptElement {

  private final String figureName;
  private final String caption;
  
  /**
   * The figure name is the pathname of the file containing the figure without an extension.
   * Example: if your figure is contained in /home/joe/docs/mylogo.jpeg,
   * the figure name is /home/joe/docs/mylogo.
   *
   * It is recommended to use relative paths.
   */
  public Figure(String figureName) {
    checkNotNull(figureName, "figureName must never be null");
    
    this.figureName = figureName;
    this.caption = null;
  }
  
  /**
   * The figure name is the pathname of the file containing the figure without an extension.
   * Example: if your figure is contained in /home/joe/docs/mylogo.jpeg,
   * the figure name is /home/joe/docs/mylogo.
   *
   * It is recommended to use relative paths.
   */
  public Figure(String figureName, String caption) {
    checkNotNull(figureName, "figureName must never be null");
    checkNotNull(figureName, "caption must never be null. If you don't need a caption, use constructor without caption argument.");
    
    this.figureName = figureName;
    this.caption = caption;
  }
  
  @Override
  String render() {
    if (caption != null) {
      return String.format("[%s] %s", figureName, Escaper.escape(caption));
    }
    
    return String.format("[%s]", figureName);
  }

}
