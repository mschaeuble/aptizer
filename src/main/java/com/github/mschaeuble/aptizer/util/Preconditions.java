package com.github.mschaeuble.aptizer.util;


public final class Preconditions {

  private Preconditions() {}
  
  public static void checkArgument(boolean expression, String errorMessage) {
    if (!expression) {
      throw new IllegalArgumentException(errorMessage);
    }
  }
  
  public static <T>void checkNotNull(T reference, String errorMessage) {
    if (reference == null) {
      throw new NullPointerException(errorMessage);
    }
  }
  
}
