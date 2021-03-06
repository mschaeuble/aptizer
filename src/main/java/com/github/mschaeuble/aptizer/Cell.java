package com.github.mschaeuble.aptizer;

import com.github.mschaeuble.aptizer.util.Escaper;

import static com.github.mschaeuble.aptizer.util.Preconditions.checkNotNull;


public class Cell {

  private static final String EMPTY_CELL_MARKUP = " ";
  
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
   * Constructs a new empty cell.
   */
  public Cell() {
    this.content = null;
    this.alignment = Alignment.LEFT;
  }
  
  /**
   * Constructs a new cell with default alignment (left).
   */
  public Cell(String content) {
    checkNotNull(content, "content must never be null");
    
    this.content = Escaper.escape(content);
    this.alignment = Alignment.LEFT;
  }
  
  public Cell(String content, Alignment alignment) {
    checkNotNull(content, "content must never be null");
    checkNotNull(alignment, "alignment must never be null. Use constructor without alignment parameter instead if you wanna use default alignment");
    
    this.content = Escaper.escape(content);
    this.alignment = alignment;
  }
 
  public Cell(Text formattedContent) {
    checkNotNull(formattedContent, "formattedContent must never be null");
    
    this.content = formattedContent.render();
    this.alignment = Alignment.LEFT;
  }
  
  public Cell(Text formattedContent, Alignment alignment) {
    checkNotNull(formattedContent, "formattedContent must never be null");
    checkNotNull(alignment, "alignment must never be null. Use constructor without alignment parameter instead if you wanna use default alignment");
    
    this.content = formattedContent.render();
    this.alignment = alignment;
  }

  Alignment getAlignment() {
    return alignment;
  }
  
  String render() {
    return content != null ? content : EMPTY_CELL_MARKUP;
  }
}