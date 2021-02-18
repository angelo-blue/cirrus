package cirrus.person.service;

import org.apache.camel.Body;
import org.springframework.stereotype.Service;
import cirrus.person.json.PersonResponse;

@Service
public class PersonService {

  /* Placeholder for if transformation is required */
  public PersonResponse createPersonResponse(@Body String backendReponse) throws Exception {
    PersonResponse result = new PersonResponse();
    // result.message = backendReponse;
    return result;
  }
}
