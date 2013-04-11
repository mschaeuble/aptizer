package com.github.mschaeuble.aptizer.element;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ParagraphTest {

  @Test
  public void shouldRenderASimpleParagraph() {
    // Given
    String text = "This is a simple paragraph.";
    Paragraph paragraph = new Paragraph(text);
    
    // When
    String renderedParagraph = paragraph.render();
    
    // Then
    assertThat(renderedParagraph, equalTo("  " + text));
  }

}
