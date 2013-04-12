package com.github.mschaeuble.aptizer.element;

public class Link implements AptElement {

  private final String target;
  private final String alternateText;
  
  public Link(String target) {
    this.target = target;
    this.alternateText = null;
  }
  
  public Link(String target, String alternateText) {
    this.target = target;
    this.alternateText = alternateText;
  }
  
  public String render() {
    if (alternateText != null) {
      return String.format("{{{%s}%s}}", target, alternateText);
    } else {
      return String.format("{{%s}}", target);
    }
  }

}
