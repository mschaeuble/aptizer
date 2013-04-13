package com.github.mschaeuble.aptizer;

import com.github.mschaeuble.aptizer.util.Escaper;

import static com.github.mschaeuble.aptizer.util.Preconditions.checkNotNull;

public class Link extends AptElement {

  private final String target;
  private final String alternateText;
  
  /**
   * Constructs a new link to an (external) target
   * (http:/, ftp:/, file:/, mailto:, ...).
   * The link text does not differ from the link target.
   */
  public Link(String target) {
    checkNotNull(target, "target must never be null");
    
    this.target = target;
    this.alternateText = null;
  }
  
  /**
   * Constructs a new link to an (external) target
   * (http:/, ftp:/, file:/, mailto:, ...) with the
   * provided alternate text.
   */
  public Link(String target, String alternateText) {
    checkNotNull(target, "target must never be null");
    checkNotNull(alternateText, "alternateText must never be null. Use constructor without alternateText instead.");
    
    this.target = target;
    this.alternateText = alternateText;
  }
  
  /**
   * Constructs a new link to an Anchor.
   * The link text does not differ from the link target.
   */
  public Link(Anchor targetAnchor) {
    checkNotNull(targetAnchor, "targetAnchor must never be null");
    
    this.target = targetAnchor.getName();
    this.alternateText = null;
  }
  
  /**
   * Constructs a new link to an Anchor with the provided alternate text.
   */
  public Link(Anchor targetAnchor, String alternateText) {
    checkNotNull(targetAnchor, "targetAnchor must never be null");
    checkNotNull(alternateText, "alternateText must never be null. Use constructor without alternateText instead.");
    
    this.target = targetAnchor.getName();
    this.alternateText = alternateText;
  }

  String render() {
    if (alternateText != null) {
      return String.format("{{{%s}%s}}", target, Escaper.escape(alternateText));
    } else {
      return String.format("{{%s}}", target);
    }
  }

}
