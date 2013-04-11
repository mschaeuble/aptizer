package com.github.mschaeuble.aptizer.util;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


public class PreconditionsTest {

  @Test
  public void shouldNotThrowAnExceptionWhenBooleanExpressionIsTrue() {
    // When
    Preconditions.checkArgument(true, "this error message is not relevant");
    
    // Then
    // should not throw any exception...
  }
  
  @Test
  public void shouldThrowAnExceptionWhenBooleanExpressionIsFalse() {
    // Given
    String errorMessage = "this error message is not relevant";
    
    // When
    try {
      Preconditions.checkArgument(false, errorMessage);
    
    // Then
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage(), equalTo(errorMessage));
      return;
    }
    
    Assert.fail("Expected IllegalArgumentException");
  }
  
}
