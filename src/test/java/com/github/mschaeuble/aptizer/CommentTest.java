package com.github.mschaeuble.aptizer;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


public class CommentTest {

  @Test
  public void shouldRenderAOneLineComment() {
    // Given
    String text = "one line comment.";
    Comment comment = new Comment(text);
    
    // When
    String renderedComment = comment.render();
    
    // Then
    assertThat(renderedComment, equalTo("~~" + text));
  }
  
  @Test
  public void shouldRenderAUnixMultilineComment() {
    // Given
    String text = "first line\nsecond line";
    Comment comment = new Comment(text);
    
    // When
    String renderedComment = comment.render();
    
    // Then
    assertThat(renderedComment, equalTo("~~first line\n~~second line"));
  }
  
  @Test
  public void shouldRenderAWindowsMultilineComment() {
    // Given
    String text = "first line\r\nsecond line";
    Comment comment = new Comment(text);
    
    // When
    String renderedComment = comment.render();
    
    // Then
    assertThat(renderedComment, equalTo("~~first line\n~~second line"));
  }
  
}
