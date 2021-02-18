package cirrus.person.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cirrus.person.json.PersonResponse;
import cirrus.person.service.PersonService;
import cirrus.route.ExternalRoute;

/**
 * Custom route to handle Persons.
 *
 * @author mike
 */
@Component
public class PersonRoute extends ExternalRoute {
  private static final Logger logger = LoggerFactory.getLogger(PersonRoute.class);

  @Autowired private PersonService personService;

  @Override
  public void configure() throws Exception {
    super.configure();

    rest("person").get("v1").outType(PersonResponse.class).to("direct:person");

    from("direct:person")
        .process(startExternalRoute("Person"))

        // .outType(PersonResponse.class)
        .to("direct:backendPersonSearch")

        // transform to client response
        // comment out for now - just return backend response directly
        // .setBody(method(personService, "createClientResponse"))
        // .marshal().json(JsonLibrary.Jackson)

        .process(endExternalRoute());
  }
}
