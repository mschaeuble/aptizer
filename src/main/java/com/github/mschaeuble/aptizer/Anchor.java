package com.github.mschaeuble.aptizer;

public class Anchor extends AptElement {

  private final String name;
  
  public Anchor(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  String render() {
    return String.format("{%s}", name);
  }

}
