package com.github.mschaeuble.aptizer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.mschaeuble.aptizer.element.AptElement;


public class AptDocument {

  List<AptElement> elements = new ArrayList<AptElement>();
  
  public AptDocument() {
  }
  
  public void append(AptElement element) {
    elements.add(element);
  }
  
  public void renderToFile(String fileName) throws IOException {
    String renderedDocument = renderDocument();
    writeToFile(renderedDocument, fileName);
  }

  private String renderDocument() {
    StringBuilder sb = new StringBuilder();
    
    
    for (int i = 0; i < elements.size(); i++) {
      AptElement element = elements.get(i);
      sb.append(element.render());
      
      boolean notLastElement = i != elements.size()-1;
      if (notLastElement) {
        sb.append("\n\n");
      }
    }
    
    return sb.toString();
  }

  private void writeToFile(String renderedDocument, String fileName) throws IOException {
    FileWriter fileWriter = new FileWriter(fileName);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    bufferedWriter.write(renderedDocument);
    bufferedWriter.close();
    fileWriter.close();
  }
  
}
