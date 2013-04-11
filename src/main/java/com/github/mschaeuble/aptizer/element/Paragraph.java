package com.github.mschaeuble.aptizer.element;


public class Paragraph implements AptElement {

  private static final String INDENTATION = "  ";
  
  private final String text;
  
  public Paragraph(String text) {
    this.text = text;
  }

  public String render() {
    return INDENTATION + text;
  }
  
}
