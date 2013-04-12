package com.github.mschaeuble.aptizer;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


public class DateTest {

  @Test
  public void shouldRenderFreestyleDate() {
    // Given
    Date date = new Date("freestyle");
    
    // When
    String renderedDate = date.render();
    
    // Then
    assertThat(renderedDate, equalTo("freestyle"));
  }
  
  @Test
  public void shouldRenderIsoDate() {
    // Given
    Date date = new Date(999, 7, 2);
    
    // When
    String renderedDate = date.render();
    
    // Then
    assertThat(renderedDate, equalTo("0999-07-02"));
  }
  
}
