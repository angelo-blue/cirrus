package cirrus.route;

import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cirrus.util.CirrusDateUtils;

/**
 * Use this class to log the standard route calls.
 *
 * @author mike
 */
public class RouteLoggingProperties {
  private static final Logger routeLogger = LoggerFactory.getLogger("RouteLogger");

  public ZonedDateTime start;
  public ZonedDateTime end;
  public String routeName;
  public String exception;
  private String logPattern = "route:%s start:%s end:%s";
  private String logPatternWithException = "route:%s start:%s end:%s error:%s";

  public static RouteLoggingProperties start(String routeName) {
    CirrusDateUtils sdu = new CirrusDateUtils();
    RouteLoggingProperties rlp = new RouteLoggingProperties();
    rlp.start = sdu.getNow();
    rlp.routeName = routeName;
    return rlp;
  }

  public void finish() {
    CirrusDateUtils sdu = new CirrusDateUtils();
    end = sdu.getNow();
    if (exception == null) {
      routeLogger.info(
          String.format(logPattern, routeName, sdu.getISOString(start), sdu.getISOString(end)));
    } else {
      routeLogger.info(
          String.format(
              logPatternWithException,
              routeName,
              sdu.getISOString(start),
              sdu.getISOString(end),
              exception));
    }
  }
}
