package com.github.mschaeuble.aptizer.element;


public class Cell {

  public enum Alignment  {
    LEFT,
    RIGHT,
    CENTER;

    String render() {
      switch (this) {
        case LEFT:
          return "+--";
        case RIGHT:
          return ":--";
        case CENTER:
          return "*--";
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