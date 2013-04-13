aptizer
=======

A small library to simplify the creation of [APT ("Almost Plain Text")](http://maven.apache.org/doxia/references/apt-format.html) files

Usage
-----

A (rather complex) example is shown below demonstrating more or less all the features that aptizer supports

    File outputFile = newFile("demo.apt");
    
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
        renderToFile(outputFile.getAbsolutePath());
        
would result in the following document:

      ---
      Title
      ---
      Author
    
    {TopAnchor}
    
      Paragraph 1, line 1.
      Paragraph 1, line 2.
    
      Paragraph 2, line 1.
      Paragraph 2, line 2.
    
    Section title
    
    * Sub\-section title
    
    ** Sub\-sub\-section title
    
    *** Sub\-sub\-sub\-section title
    
    **** Sub\-sub\-sub\-sub\-section title
    
      * List item 1.
    
      * List item 2.
    
        paragraph contained in list item 2
    
        * Sub\-list item 1.
    
      []
    
      * List item 3.
    
      []
    
      [[1]] Numbered item 1.
    
      [[1]] Numbered item 2.
    
      []
    
      [[a]] Lower alpha item 1.
    
      [[a]] Lower alpha item 2.
    
      []
    
      [[A]] Upper alpha item 1.
    
      [[A]] Upper alpha item 2.
    
      []
    
      [[i]] Lower roman item 1.
    
      [[i]] Lower roman item 2.
    
      []
    
      [[I]] Upper roman item 1.
    
      [[I]] Upper roman item 2.
    
      []
    
    ---
    Verbatim text,
       preformatted,
          escaped.
    ---
    
    ===
    
    {{http://www.google.com}}
    
    {{{http://www.google.com}Google}}
    
    {{{TopAnchor}Goto top}}
    
    {{TopAnchor}}
    
    +--+
    Verbatim text in a box
    +--+
    
    *--*--+--:--
    |Centered|Left\-aligned|Right\-aligned
    *--*--+--:--
    |c|l|r
    *--*--+--:--
    Table demo
    
    *--+--+--
    This|is a
    *--+--+--
    gridless|table
    *--+--+--
    
    ~~comment line 1
    ~~comment line 2
    
    ^L
    
    [doesnotexist] Figure caption
    
    [doesnotexist]
    
      Escaped special characters: \~, \=, \-, \+, \*, \[, \], \<, \>, \{, \}, \\.
    
      <Italic> font. <<Bold>> font. <<<Monospaced>>> font.
    
      * <<Formatted list item>>
    
      []
    
    *--+--
    |<formatted text in table cell>
    *--+--
    
      Force line\
    break.
    
      Non\ breaking\ space