package com.github.mschaeuble.aptizer;


public class Text {
  
  private final static String ITALIC_START_MARKUP     = "<";
  private final static String ITALIC_END_MARKUP       = ">";
  private final static String BOLD_START_MARKUP       = "<<";
  private final static String BOLD_END_MARKUP         = ">>";
  private final static String MONOSPACED_START_MARKUP = "<<<";
  private final static String MONOSPACED_END_MARKUP   = ">>>";
  
  private final StringBuilder sb = new StringBuilder();
  
  public enum Format {
    ITALIC,
    
    BOLD,
    
    /** monospaced, typwriter like font */
    MONOSPACED;
  }

  public Text append(String defaultFormattedText) {
    sb.append(defaultFormattedText);
    
    return this;
  }
  
  public Text append(String text, Format format) {
    switch (format) {
      case ITALIC:
        sb.append(ITALIC_START_MARKUP).
           append(text).
           append(ITALIC_END_MARKUP);
        break;
      case BOLD:
        sb.append(BOLD_START_MARKUP).
           append(text).
           append(BOLD_END_MARKUP);
        break;
      case MONOSPACED:
        sb.append(MONOSPACED_START_MARKUP).
           append(text).
           append(MONOSPACED_END_MARKUP);
        break;
      default:
        String msg = String.format("Format '%s' is not implemented", format);
        throw new UnsupportedOperationException(msg);
    }
    
    return this;
  }
  
  String render() {
    return sb.toString();
  }
}
