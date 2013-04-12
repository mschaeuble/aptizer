package com.github.mschaeuble.aptizer;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.github.mschaeuble.aptizer.element.HorizontalRule;
import com.github.mschaeuble.aptizer.element.Link;
import com.github.mschaeuble.aptizer.element.List;
import com.github.mschaeuble.aptizer.element.List.Style;
import com.github.mschaeuble.aptizer.element.Paragraph;
import com.github.mschaeuble.aptizer.element.Section;
import com.github.mschaeuble.aptizer.element.VerbatimText;

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
    File outputFile = tmpFolder.newFile("test.apt");
    
    // When
    new AptDocument("Title", "Author").
        append(new Paragraph("Paragraph 1, line 1.").
                     addLine("Paragraph 1, line 2.")).
        append(new Paragraph("Paragraph 2, line 1.").
                     addLine("Paragraph 2, line 2.")).
        append(new Section("Section title")).
        append(new Section("Sub-section title", 2)).
        append(new Section("Sub-sub-section title", 3)).
        append(new Section("Sub-sub-sub-section title", 4)).
        append(new Section("Sub-sub-sub-sub-section title", 5)).
        append(new List(Style.BULLETS).
                 addItem("List item 1.").
                 addItem("List item 2.")).
        append(new List(Style.DECIMAL).
                 addItem("Numbered item 1.").
                 addItem("Numbered item 2.")).
        append(new List(Style.LOWER_ALPHA).
                 addItem("Lower alpha item 1.").
                 addItem("Lower alpha item 2.")).
        append(new List(Style.UPPER_ALPHA).
                 addItem("Upper alpha item 1.").
                 addItem("Upper alpha item 2.")).
        append(new List(Style.LOWER_ROMAN).
                 addItem("Lower roman item 1.").
                 addItem("Lower roman item 2.")).
        append(new List(Style.UPPER_ROMAN).
                 addItem("Upper roman item 1.").
                 addItem("Upper roman item 2.")).
        append(new VerbatimText("Verbatim text,\n   preformatted,\n      escaped.")).
        append(new HorizontalRule()).
        append(new Link("http://www.google.com")).
        append(new Link("http://www.google.com", "Google")).
        renderToFile(outputFile.getAbsolutePath());
    
    // Then
    assertThat(outputFile.exists(), equalTo(true));
    
    String referenceFile = readFileToString(toFile(getClass().getResource("/reference.apt")));
    assertThat(readFileToString(outputFile), equalTo(referenceFile));
  }
}
