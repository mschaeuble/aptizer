package com.github.mschaeuble.aptizer.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Escaper {

  private final static Pattern pattern = Pattern.compile("(~|=|-|\\+|\\*|\\[|\\]|<|>|\\{|\\})");
  
  public static String escape(String unescapedText) {
    String escapedBackslash = unescapedText.replace("\\", "\\\\");
    
    Matcher matcher = pattern.matcher(escapedBackslash);

    StringBuffer sb = new StringBuffer();
    while(matcher.find()) {
        matcher.appendReplacement(sb, "\\\\" + matcher.group(1));
    }
    matcher.appendTail(sb);

    return sb.toString();
  }
}
