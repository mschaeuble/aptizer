package com.github.mschaeuble.aptizer;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.github.mschaeuble.aptizer.List.Style;
import com.github.mschaeuble.aptizer.Text.Format;

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
    Anchor topAnchor = new Anchor("TopAnchor");
    
    new AptDocument("Title", "Author").
        append(topAnchor).
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
                 addItem("List item 2.").
                 addParagraph(new Paragraph("paragraph contained in list item 2")).
                 addList(new List(Style.BULLETS).addItem("Sub-list item 1.")).
                 addItem("List item 3.")).
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
        append(new VerbatimText("Verbatim text,\n   preformatted,\n      escaped.", VerbatimText.Style.UNFRAMED)).
        append(new HorizontalRule()).
        append(new Link("http://www.google.com")).
        append(new Link("http://www.google.com", "Google")).
        append(new Link(topAnchor, "Goto top")).
        append(new Link(topAnchor)).
        append(new VerbatimText("Verbatim text in a box", VerbatimText.Style.FRAMED)).
        append(new Table(Table.Style.GRID, "Table demo").
                 addRow(new Cell("Centered", Cell.Alignment.CENTER),
                        new Cell("Left-aligned", Cell.Alignment.LEFT),
                        new Cell("Right-aligned", Cell.Alignment.RIGHT)).
                 addRow(new Cell("c", Cell.Alignment.CENTER),
                        new Cell("l", Cell.Alignment.LEFT),
                        new Cell("r", Cell.Alignment.RIGHT))).
        append(new Table(Table.Style.GRIDLESS).
                 addRow(new Cell("This"), new Cell("is a")).
                 addRow(new Cell("gridless"), new Cell("table"))).
        append(new Comment("comment line 1\ncomment line 2")).
        append(new PageBreak()).
        append(new Figure("doesnotexist", "Figure caption")).
        append(new Figure("doesnotexist")).
        append(new Paragraph("Escaped special characters: ~, =, -, +, *, [, ], <, >, {, }, \\.")).
        append(new Paragraph(new Text().append("Italic", Format.ITALIC).
                                        append(" font. ").
                                        append("Bold", Format.BOLD).
                                        append(" font. ").
                                        append("Monospaced", Format.MONOSPACED).
                                        append(" font."))).
        append(new List(Style.BULLETS).
                 addItem(new Text().append("Formatted list item", Format.BOLD))).
        append(new Table(Table.Style.GRID).
                 addRow(new Cell(new Text("formatted text in table cell", Format.ITALIC)))).
        append(new Paragraph(new Text("Force line").forceLineBreak().append("break."))).
        append(new Paragraph(new Text("Non").appendNonBreakingSpace().
                               append("breaking").appendNonBreakingSpace().
                               append("space"))).
        append(new Table(Table.Style.GRID).
                 addRow(new Cell(),
                        new Cell("empty cells"),
                        new Cell())).
        renderToFile(outputFile.getAbsolutePath());
    
    // Then
    assertThat(outputFile.exists(), equalTo(true));
    
    String referenceFile = readFileToString(toFile(getClass().getResource("/reference.apt")));
    assertThat(readFileToString(outputFile), equalTo(referenceFile));
  }
  
  @Test
  public void shouldRenderAnEmptyDocument() {
    // Given
    AptDocument document = new AptDocument();
    
    // When
    String renderedDocument = document.renderToString();
    
    // Then
    assertThat(renderedDocument, equalTo(""));
  }
  
  @Test
  public void shouldRenderDocumentWithATitle() {
    // Given
    AptDocument document = new AptDocument("title");
    
    // When
    String renderedDocument = document.renderToString();
    
    // Then
    assertThat(renderedDocument, equalTo("  ---\n  title\n"));
  }
  
  @Test
  public void shouldRenderDocumentWithATitleAndAuthor() {
    // Given
    AptDocument document = new AptDocument("title", "author");
    
    // When
    String renderedDocument = document.renderToString();
    
    // Then
    assertThat(renderedDocument, equalTo("  ---\n  title\n  ---\n  author\n"));
  }
  
  @Test
  public void shouldRenderDocumentWithATitleAndDate() {
    // Given
    AptDocument document = new AptDocument("title", new Date("date"));
    
    // When
    String renderedDocument = document.renderToString();
    
    // Then
    assertThat(renderedDocument, equalTo("  ---\n  title\n  ---\n  ---\n  date\n"));
  }
  
  @Test
  public void shouldRenderDocumentWithATitleAuthorAndDate() {
    // Given
    AptDocument document = new AptDocument("title", "author", new Date("date"));
    
    // When
    String renderedDocument = document.renderToString();
    
    // Then
    assertThat(renderedDocument, equalTo("  ---\n  title\n  ---\n  author\n  ---\n  date\n"));
  }
}
