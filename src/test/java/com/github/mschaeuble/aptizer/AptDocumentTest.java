package com.github.mschaeuble.aptizer;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.github.mschaeuble.aptizer.element.Paragraph;
import com.github.mschaeuble.aptizer.element.Section;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.apache.commons.io.FileUtils.toFile;
import static org.apache.commons.io.FileUtils.readFileToString;


public class AptDocumentTest {

  @Rule
  public TemporaryFolder tmpFolder = new TemporaryFolder();
  
  @Test
  public void shouldRenderAnAptDocument() throws IOException {
    // Given
    AptDocument document = new AptDocument();
    document.append(new Section("Section 1"));
    document.append(new Paragraph("A paragraph."));
    
    File outputFile = tmpFolder.newFile("test.apt");
    
    // When
    document.renderToFile(outputFile.getAbsolutePath());
    
    // Then
    assertThat(outputFile.exists(), equalTo(true));
    
    String referenceFile = readFileToString(toFile(getClass().getResource("/reference.apt")));
    assertThat(readFileToString(outputFile), equalTo(referenceFile));
  }
}
