package com.github.mschaeuble.aptizer;

import org.junit.Test;

import com.github.mschaeuble.aptizer.Table.Style;
import com.github.mschaeuble.aptizer.Text.Format;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


public class TableTest {

  @Test
  public void shouldRenderEmptyTable() throws Exception {
    // Given
    Table table = new Table(Style.GRID);
    
    // When
    String renderedTable = table.render();
    
    // Then
    assertThat(renderedTable, equalTo(""));
  }
  
  @Test
  public void shouldRenderOneRightAlignedCellTableWithGrid() throws Exception {
    // Given
    Table table = new Table(Style.GRID).addRow(new Cell("cell", Cell.Alignment.RIGHT));
    
    // When
    String renderedTable = table.render();
    
    // Then
    assertThat(renderedTable, equalTo("*--:--\n|cell\n*--:--"));
  }
  
  @Test
  public void shouldRenderOneDefaultAlignedCellTableWithCaption() throws Exception {
    // Given
    Table table = new Table(Style.GRID, "caption").addRow(new Cell("cell"));
    
    // When
    String renderedTable = table.render();
    
    // Then
    assertThat(renderedTable, equalTo("*--+--\n|cell\n*--+--\ncaption"));
  }
  
  @Test
  public void shouldRenderOneCenterAlignedCellGridlessTable() throws Exception {
    // Given
    Table table = new Table(Style.GRIDLESS).addRow(new Cell("cell", Cell.Alignment.CENTER));
    
    // When
    String renderedTable = table.render();
    
    // Then
    assertThat(renderedTable, equalTo("*--*--\ncell\n*--*--"));
  }
  
  @Test
  public void shouldRenderCellWithFormattedContent() throws Exception {
    // Given
    Table table = new Table(Style.GRID).
                    addRow(new Cell(new Text("formatted text in table cell", Format.ITALIC)));
    
    // When
    String renderedTable = table.render();
    
    // Then
    assertThat(renderedTable, equalTo("*--+--\n|<formatted text in table cell>\n*--+--"));
  }
}
