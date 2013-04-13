package com.github.mschaeuble.aptizer.util;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


public class EscaperTest {

  @Test
  public void shouldEscape() {
    // Given
    String unescpaedText = "~ = - + * [ ] < > { } \\";
    
    // When
    String escapedText = Escaper.escape(unescpaedText);
    
    // Then
    assertThat(escapedText, equalTo("\\~ \\= \\- \\+ \\* \\[ \\] \\< \\> \\{ \\} \\\\"));
  }
}
