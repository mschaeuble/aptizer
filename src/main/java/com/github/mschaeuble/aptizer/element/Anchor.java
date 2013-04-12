package com.github.mschaeuble.aptizer.element;

public class Anchor implements AptElement {

  private final String name;
  
  public Anchor(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  public String render() {
    return String.format("{%s}", name);
  }

}
