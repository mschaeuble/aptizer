package com.github.mschaeuble.aptizer;


public class Cell {

  
  public enum Alignment  {
    LEFT,
    RIGHT,
    CENTER;
    
    private final String LEFT_ALIGNMENT_MARKUP   = "+--";
    private final String RIGHT_ALIGNMENT_MARKUP  = ":--";
    private final String CENTER_ALIGNMENT_MARKUP = "*--";

    String render() {
      switch (this) {
        case LEFT:
          return LEFT_ALIGNMENT_MARKUP;
        case RIGHT:
          return RIGHT_ALIGNMENT_MARKUP;
        case CENTER:
          return CENTER_ALIGNMENT_MARKUP;
        default:
          String msg = String.format("Alignment '%s' is not implemented", this);
          throw new UnsupportedOperationException(msg);
      }
    }
  }
  
  private final String content;
  private final Alignment alignment;
  
  /**
   * Constructs a new cell with default alignment (left).
   */
  public Cell(String content) {
    this.content = content;
    this.alignment = Alignment.LEFT;
  }
  
  public Cell(String content, Alignment alignment) {
    this.content = content;
    this.alignment = alignment;
  }
 
  Alignment getAlignment() {
    return alignment;
  }
  
  String render() {
    return content;
  }
}