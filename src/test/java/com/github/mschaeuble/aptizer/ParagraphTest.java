package com.github.mschaeuble.aptizer;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ParagraphTest {

  @Test
  public void shouldRenderAOneLineParagraph() {
    // Given
    String text = "This is a simple paragraph.";
    Paragraph paragraph = new Paragraph(text);
    
    // When
    String renderedParagraph = paragraph.render();
    
    // Then
    assertThat(renderedParagraph, equalTo("  " + text));
  }
  
  @Test
  public void shouldRenderATwoLineParagraph() {
    // Given
    String line1 = "Paragraph 1, line 1.";
    String line2 = "Paragraph 1, line 2.";
    
    Paragraph paragraph = new Paragraph(line1).addLine(line2);
    
    // When
    String renderedParagraph = paragraph.render();
    
    // Then
    assertThat(renderedParagraph, equalTo("  " + line1 + "\n" + "  " + line2));
  }

}
