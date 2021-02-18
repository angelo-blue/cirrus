package cirrus.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;

class CirrusDateUtilsTest {
  CirrusDateUtils cdu = new CirrusDateUtils();

  @Test
  void testGetNow() {
    assertNotNull(cdu.getNow());
  }

  @Test
  void testGetISOString() {
    ZonedDateTime z = cdu.getNow();
    String s = cdu.getISOString(z);
    assertNotNull(s);
    assertEquals(cdu.getDateFromISOString(s), z);
  }

  @Test
  void testGetShortDateString() {
    String s = "2021-02-18";
    ZonedDateTime z = cdu.getDateFromShortString(s);
    String s2 = cdu.getShortDateString(z);
    assertNotNull(s2);
    assertEquals(s, s2);
  }

}
