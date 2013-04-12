package com.github.mschaeuble.aptizer.element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.mschaeuble.aptizer.util.Consts.NEW_LINE;

public class Table implements AptElement {

  public enum Style {
    /** Draws the table with a grid around table cells. */
    GRID,
    
    /** Draws the table without any grid. */
    GRIDLESS;
    
    private String render() {
      switch (this) {
        case GRID:
          return "|";
        case GRIDLESS:
          return "";
        default:
          String msg = String.format("Style '%s' is not implemented", this);
          throw new UnsupportedOperationException(msg);
      }
    }
  }
  
  private final List<List<Cell>> content = new ArrayList<List<Cell>>();
  private final Style style;
  private final String caption;
  
  public Table(Style style, String caption) {
    this.style = style;
    this.caption = caption;
  }
  
  public Table(Style style) {
    this.style = style;
    this.caption = null;
  }
  
  public Table addRow(Cell... cells) {
    content.add(Arrays.asList(cells));
    return this;
  }
  
  public String render() {
    boolean isEmptyTable = content.size() <= 0;
    if (isEmptyTable) {
      return "";
    }
    
    return renderTable();
  }

  private String renderTable() {
    StringBuilder sb = new StringBuilder();
    
    renderTable(sb);
    renderCaption(sb);
    
    return sb.toString();
  }

  private void renderTable(StringBuilder sb) {
    for (int currentRow = 0; currentRow < content.size(); currentRow++) {
      renderRow(sb, currentRow);
    }
    List<Cell> cellsInLastRow = content.get(content.size()-1);
    renderColumnDefinitions(sb, cellsInLastRow);
  }

  private void renderRow(StringBuilder sb, int currentRow) {
    List<Cell> cellsInCurrentRow = content.get(currentRow);
    renderColumnDefinitions(sb, cellsInCurrentRow);
    sb.append(NEW_LINE);
    
    for (int i = 0; i < cellsInCurrentRow.size(); i++) {
      Cell cell = cellsInCurrentRow.get(i);
      
      if (i == 0) {
        sb.append(style.render());
      } else {
        sb.append("|");
      }
      
      sb.append(cell.render());
    }
    sb.append(NEW_LINE);
  }

  private void renderCaption(StringBuilder sb) {
    if (caption != null) {
      sb.append(NEW_LINE);
      sb.append(caption);
    }
  }

  private void renderColumnDefinitions(StringBuilder sb, List<Cell> cells) {
    for (int i = 0; i < cells.size(); i++) {
      if (i == 0) {
        sb.append("*--");
      }
      
      sb.append(cells.get(i).getAlignment().render());
    }
  }
}
