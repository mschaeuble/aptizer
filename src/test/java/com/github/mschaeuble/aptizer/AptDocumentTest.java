package com.github.mschaeuble.aptizer;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.github.mschaeuble.aptizer.element.Paragraph;
import com.github.mschaeuble.aptizer.element.Section;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.io.FileUtils.toFile;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


public class AptDocumentTest {

  @Rule
  public TemporaryFolder tmpFolder = new TemporaryFolder();
  
  @Test
  public void shouldRenderAnAptDocument() throws IOException {
    // Given
    AptDocument document = new AptDocument("Title", "Author");
    document.append(new Paragraph("Paragraph 1, line 1.").addLine("Paragraph 1, line 2."));
    document.append(new Section("Section title"));
    document.append(new Section("Sub-section title", 2));
    document.append(new Section("Sub-sub-section title", 3));
    document.append(new Section("Sub-sub-sub-section title", 4));
    document.append(new Section("Sub-sub-sub-sub-section title", 5));

    File outputFile = tmpFolder.newFile("test.apt");
    
    // When
    document.renderToFile(outputFile.getAbsolutePath());
    
    // Then
    assertThat(outputFile.exists(), equalTo(true));
    
    String referenceFile = readFileToString(toFile(getClass().getResource("/reference.apt")));
    assertThat(readFileToString(outputFile), equalTo(referenceFile));
  }
}
