package com.github.mschaeuble.aptizer;


public class HorizontalRule extends AptElement {

  private static final String HORIZONTAL_RULE_MARKUP = "===";
  
  String render() {
    return HORIZONTAL_RULE_MARKUP;
  }

}
