package com.github.mschaeuble.aptizer;

import static com.github.mschaeuble.aptizer.util.Preconditions.checkArgument;
import static com.github.mschaeuble.aptizer.util.Preconditions.checkNotNull;


public class Date {

  private final String date;
  
  /**
   * Constructs a free text date. Please note however, that it is
   * recommended to use the ISO-8601 standard to represent dates
   * using the other constructor.
   */
  public Date(String freetextDate) {
    checkNotNull(freetextDate, "freetextDate must never be null");
    
    this.date = freetextDate;
  }
  
  /**
   * Constructs a date in the recommended ISO-8601 standard (YYYY-MM-DD).
   */
  public Date(int year, int month, int day) {
    checkArgument(year >= 0 && year <= 9999, "0 <= year <= 9999");
    checkArgument(month >= 1 && month <= 12, "1 <= month <= 12");
    checkArgument(day >= 1 && day <= 12, "1 <= day <= 31");
    
    this.date = String.format("%04d-%02d-%02d", year, month, day);
  }
  
  String render() {
    return date;
  }
}
