package cirrus.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * All routes exposing front-end rest calls should extend this class. A global exception handler
 * will catch any exceptions and return http 500.
 *
 * @author mike
 */
public abstract class ExternalRoute extends RouteBuilder {
  private static final Logger logger = LoggerFactory.getLogger(ExternalRoute.class);
  protected static String exchangePropertyRLP = "RouteLoggingProperties";

  @Override
  public void configure() throws Exception {

    onException(Exception.class).process(handleGenericException).handled(true);
  }

  /** Handle an exception in the route. Log something. Set response to http 500. */
  private Processor handleGenericException =
      new Processor() {
        @Override
        public void process(Exchange exchange) throws Exception {
          Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
          if (exchange.getProperty(exchangePropertyRLP) != null) {
            RouteLoggingProperties rlp =
                exchange.getProperty(exchangePropertyRLP, RouteLoggingProperties.class);
            rlp.exception = cause.getMessage();
            rlp.finish();
          }
          exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, 500L);
        }
      };

  /**
   * starts the external route
   *
   * @param routeName
   * @return the processor
   */
  protected Processor startExternalRoute(final String routeName) {
    return new Processor() {
      @Override
      public void process(Exchange exchange) throws Exception {
        RouteLoggingProperties rlp = RouteLoggingProperties.start(routeName);
        exchange.setProperty(exchangePropertyRLP, rlp);
      }
    };
  }

  /**
   * ends the external route and logs the details
   *
   * @return the processor
   */
  protected Processor endExternalRoute() {
    return new Processor() {
      @Override
      public void process(Exchange exchange) throws Exception {
        RouteLoggingProperties rlp =
            exchange.getProperty(exchangePropertyRLP, RouteLoggingProperties.class);
        rlp.finish();
      }
    };
  }
}
