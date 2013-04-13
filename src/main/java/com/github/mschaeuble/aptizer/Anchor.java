package com.github.mschaeuble.aptizer;

import com.github.mschaeuble.aptizer.util.Escaper;

import static com.github.mschaeuble.aptizer.util.Preconditions.checkNotNull;

public class Anchor extends AptElement {

  private final String name;
  
  public Anchor(String name) {
    checkNotNull(name, "name must never be null");
    
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  String render() {
    return String.format("{%s}", Escaper.escape(name));
  }

}
