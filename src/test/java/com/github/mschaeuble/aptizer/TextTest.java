package com.github.mschaeuble.aptizer;

import org.junit.Test;

import com.github.mschaeuble.aptizer.Text.Format;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


public class TextTest {

  @Test
  public void shouldRenderItalicText() throws Exception {
    // Given
    Text text = new Text().append("italic: ").
                           append("italic", Format.ITALIC);
    
    // When
    String renderedText = text.render();
    
    // Then
    assertThat(renderedText, equalTo("italic: <italic>"));
  }
  
  @Test
  public void shouldRenderBoldText() throws Exception {
    // Given
    Text text = new Text().append("bold: ").
                           append("bold", Format.BOLD);
    
    // When
    String renderedText = text.render();
    
    // Then
    assertThat(renderedText, equalTo("bold: <<bold>>"));
  }
  
  @Test
  public void shouldRenderMonospacedText() throws Exception {
    // Given
    Text text = new Text().append("monospaced: ").
                           append("monospaced", Format.MONOSPACED);
    
    // When
    String renderedText = text.render();
    
    // Then
    assertThat(renderedText, equalTo("monospaced: <<<monospaced>>>"));
  }
  
  @Test
  public void shouldRenderTextWithALink() throws Exception {
    // Given
    Text text = new Text().append("text with a link to ").
                           append(new Link("http://www.google.com", "Google"));
    
    // When
    String renderedText = text.render();
    
    // Then
    assertThat(renderedText, equalTo("text with a link to {{{http://www.google.com}Google}}"));
  }
}
