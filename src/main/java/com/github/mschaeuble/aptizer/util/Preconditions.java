package com.github.mschaeuble.aptizer.util;


public final class Preconditions {

  private Preconditions() {}
  
  /**
   * Ensures that the provided expression is true. If the expression does not hold,
   * an {@link IllegalArgumentException} with the provided error message is thrown.
   */
  public static void checkArgument(boolean expression, String errorMessage) {
    if (!expression) {
      throw new IllegalArgumentException(errorMessage);
    }
  }
  
}
