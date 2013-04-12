package com.github.mschaeuble.aptizer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static com.github.mschaeuble.aptizer.util.Consts.INDENTATION;
import static com.github.mschaeuble.aptizer.util.Consts.NEW_LINE;
import static com.github.mschaeuble.aptizer.util.Consts.THREE_DASHES;

public class AptDocument {

  private final String title;
  private final String author;
  private final List<AptElement> content = new ArrayList<AptElement>();
  
  /**
   * Constructs an empty document without title, author and date.
   */
  public AptDocument() {
    title = null;
    author = null;
  }
  
  public AptDocument(String title, String author) {
    this.title = title;
    this.author = author;
  }
  
  public AptDocument append(AptElement element) {
    content.add(element);
    return this;
  }
  
  public void renderToFile(String fileName) throws IOException {
    String renderedDocument = renderDocument();
    writeToFile(renderedDocument, fileName);
  }

  private String renderDocument() {
    StringBuilder sb = new StringBuilder();
    
    renderHeader(sb);
    renderContent(sb);
    
    return sb.toString();
  }

  private void renderHeader(StringBuilder sb) {
    renderHeaderTextIfNeeded(title, sb);
    renderHeaderTextIfNeeded(author, sb);
    
    if (title != null || author != null) {
      sb.append(NEW_LINE);
    }
  }

  private void renderHeaderTextIfNeeded(String headerText, StringBuilder sb) {
    if (headerText != null) {
      sb.append(INDENTATION).append(THREE_DASHES);
      sb.append(NEW_LINE);
      sb.append(INDENTATION).append(headerText);
      sb.append(NEW_LINE);
    }
  }

  private void renderContent(StringBuilder sb) {
    for (int i = 0; i < content.size(); i++) {
      AptElement element = content.get(i);
      sb.append(element.render());
      
      boolean notLastElement = i != content.size()-1;
      if (notLastElement) {
        sb.append(NEW_LINE).
           append(NEW_LINE);
      }
    }
  }

  private void writeToFile(String renderedDocument, String fileName) throws IOException {
    FileWriter fileWriter = new FileWriter(fileName);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    bufferedWriter.write(renderedDocument);
    bufferedWriter.close();
    fileWriter.close();
  }
  
}
