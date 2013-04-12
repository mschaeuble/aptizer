package com.github.mschaeuble.aptizer;

public class Link extends AptElement {

  private final String target;
  private final String alternateText;
  
  /**
   * Constructs a new link to an (external) target
   * (http:/, ftp:/, file:/, mailto:, ...).
   * The link text does not differ from the link target.
   */
  public Link(String target) {
    this.target = target;
    this.alternateText = null;
  }
  
  /**
   * Constructs a new link to an (external) target
   * (http:/, ftp:/, file:/, mailto:, ...) with the
   * provided alternate text.
   */
  public Link(String target, String alternateText) {
    this.target = target;
    this.alternateText = alternateText;
  }
  
  /**
   * Constructs a new link to an Anchor.
   * The link text does not differ from the link target.
   */
  public Link(Anchor targetAnchor) {
    this.target = targetAnchor.getName();
    this.alternateText = null;
  }
  
  /**
   * Constructs a new link to an Anchor with the provided alternate text.
   */
  public Link(Anchor targetAnchor, String alternateText) {
    this.target = targetAnchor.getName();
    this.alternateText = alternateText;
  }

  String render() {
    if (alternateText != null) {
      return String.format("{{{%s}%s}}", target, alternateText);
    } else {
      return String.format("{{%s}}", target);
    }
  }

}
