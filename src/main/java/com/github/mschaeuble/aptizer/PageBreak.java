package com.github.mschaeuble.aptizer;


public class PageBreak extends AptElement {

  private static final String PAGE_BREAK_MARKUP = "^L";
  
  @Override
  String render() {
    return PAGE_BREAK_MARKUP;
  }

}
