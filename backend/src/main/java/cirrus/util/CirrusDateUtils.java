package cirrus.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CirrusDateUtils {

  /** @return current datetime in nz zone */
  public ZonedDateTime getNow() {
    ZonedDateTime z = ZonedDateTime.now(ZoneId.of("NZ"));
    return z;
  }

  /**
   * @param z
   * @return string in ISO-8601 format
   */
  public String getISOString(ZonedDateTime z) {
    String result = z.format(DateTimeFormatter.ISO_DATE_TIME);
    return result;
  }

  /**
   * @param z
   * @return string in format yyyy-MM-dd
   */
  public String getShortDateString(ZonedDateTime z) {
    String result = z.format(DateTimeFormatter.ISO_LOCAL_DATE);
    return result;
  }

  /**
   * @param s iso-8601 format, e.g. "2018-02-01T01:02:03.004+13:00"
   * @return ZonedDateTime
   */
  public ZonedDateTime getDateFromISOString(String s) {
    ZonedDateTime z = ZonedDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME);
    return z;
  }

  /**
   * @param s
   * @return ZonedDateTime at start of day, in NZ.
   */
  public ZonedDateTime getDateFromShortString(String s) {
    ZonedDateTime z =
        LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay(ZoneId.of("NZ"));
    return z;
  }
}
