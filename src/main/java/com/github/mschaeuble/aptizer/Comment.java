package com.github.mschaeuble.aptizer;

import static com.github.mschaeuble.aptizer.util.Consts.NEW_LINE;

public class Comment extends AptElement {

  private static final String COMMENT_MARKUP = "~~";
  
  private final String comment;
  
  public Comment(String comment) {
    this.comment = comment;
  }
  
  String render() {
    String[] commentLines = comment.split("\n");
    
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < commentLines.length; i++) {
      String line = commentLines[i];
      
      sb.append(COMMENT_MARKUP).
         append(line);
      
      boolean isNotLastLine = i < commentLines.length - 1;
      if (isNotLastLine) {
        sb.append(NEW_LINE);
      }
    }
    
    return sb.toString();
  }

}
