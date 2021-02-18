package cirrus.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * calls the backend api. allows for caching and thread pooling.
 *
 * @author mike
 */
@Component
public class BackendRoute extends RouteBuilder {
  private static final Logger logger = LoggerFactory.getLogger(BackendRoute.class);

  @Override
  public void configure() throws Exception {

    from("direct:backendPersonSearch")
        .process(addHeaders)
        .to("http:localhost:8081/cyras-rest/secure/person?bridgeEndpoint=true");
  }

  private Processor addHeaders =
      new Processor() {
        @Override
        public void process(Exchange exchange) throws Exception {
          exchange.getIn().setHeader("Content-Type", "application/json");

          // the other headers - e.g. jwt - will carry over from the incoming call
        }
      };
}
