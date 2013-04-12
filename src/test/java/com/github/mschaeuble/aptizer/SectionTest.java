package com.github.mschaeuble.aptizer;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


public class SectionTest {

  @Test
  public void shouldRenderFirstLevelSection() throws Exception {
    // Given
    String sectionTitle = "A title";
    Section section = new Section(sectionTitle);
    
    // When
    String renderedSection = section.render();
    
    // Then
    assertThat(renderedSection, equalTo(sectionTitle));
  }
  
  @Test
  public void shouldRenderSecondLevelSection() throws Exception {
    // Given
    String sectionTitle = "A second level title";
    Section section = new Section(sectionTitle, 2);
    
    // When
    String renderedSection = section.render();
    
    // Then
    assertThat(renderedSection, equalTo("* A second level title"));
  }
  
  @Test
  public void shouldRefuseZeroLevel() throws Exception {
    // Given
    
    // When
    try {
      new Section("a title", 0);

    // Then
    } catch (IllegalArgumentException e) {
      return;
    }
    
    Assert.fail("Expected IllegalArgumentException");
  }
}
