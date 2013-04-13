package com.github.mschaeuble.aptizer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.github.mschaeuble.aptizer.util.Escaper;

import static com.github.mschaeuble.aptizer.util.Consts.INDENTATION;
import static com.github.mschaeuble.aptizer.util.Consts.NEW_LINE;
import static com.github.mschaeuble.aptizer.util.Consts.THREE_DASHES;
import static com.github.mschaeuble.aptizer.util.Preconditions.checkNotNull;

public class AptDocument {

  private static final String defaultEncoding = "UTF-8";
  
  private final String title;
  private final String author;
  private final Date date;
  private final List<AptElement> content = new ArrayList<AptElement>();
  
  /**
   * Constructs an empty document without title, author and date.
   */
  public AptDocument() {
    title = null;
    author = null;
    date = null;
  }
  
  public AptDocument(String title) {
    checkNotNull(title, "title must never be null");
    
    this.title = title;
    this.author = null;
    this.date = null;
  }
  
  public AptDocument(String title, String author) {
    checkNotNull(title, "title must never be null");
    checkNotNull(author, "author must never be null");
    
    this.title = title;
    this.author = author;
    this.date = null;
  }
  
  public AptDocument(String title, Date date) {
    checkNotNull(title, "title must never be null");
    checkNotNull(date, "date must never be null");
    
    this.title = title;
    this.author = null;
    this.date = date;
  }
  
  public AptDocument(String title, String author, Date date) {
    checkNotNull(title, "title must never be null");
    checkNotNull(author, "author must never be null");
    checkNotNull(date, "date must never be null");
    
    this.title = title;
    this.author = author;
    this.date = date;
  }
  
  public AptDocument append(AptElement element) {
    checkNotNull(element, "element must never be null");
    
    content.add(element);
    return this;
  }
  
  /**
   * Renders document to a UTF-8 encoded file.
   */
  public void renderToFile(String fileName) throws IOException {
    checkNotNull(fileName, "fileName must never be null");
    
    String renderedDocument = renderToString();
    writeToFile(renderedDocument, fileName);
  }

  public String renderToString() {
    StringBuilder sb = new StringBuilder();
    
    renderHeader(sb);
    appendNewLineIfNeeded(sb);
    renderContent(sb);
    
    return sb.toString();
  }

  private void renderHeader(StringBuilder sb) {
    renderHeaderTextIfNeeded(title, sb);
    renderHeaderTextIfNeeded(author, sb);
    
    if (date != null) {
      if (author == null) {
        sb.append(INDENTATION).append(THREE_DASHES).append(NEW_LINE);
      }
      renderHeaderTextIfNeeded(date.render(), sb);
    }
  }

  private void appendNewLineIfNeeded(StringBuilder sb) {
    boolean hasAHeader = (title != null || author != null | date != null);
    boolean hasContent = content.size() >= 1;
    
    if (hasAHeader && hasContent) {
      sb.append(NEW_LINE);
    }
  }

  private void renderHeaderTextIfNeeded(String headerText, StringBuilder sb) {
    if (headerText != null) {
      sb.append(INDENTATION).append(THREE_DASHES);
      sb.append(NEW_LINE);
      sb.append(INDENTATION).append(Escaper.escape(headerText));
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
    OutputStreamWriter outStreamWriter = new OutputStreamWriter(new FileOutputStream(fileName), defaultEncoding);
    BufferedWriter bufferedWriter = new BufferedWriter(outStreamWriter);
    bufferedWriter.write(renderedDocument);
    bufferedWriter.close();
    outStreamWriter.close();
  }
  
}
