package cirrus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CirrusApplication {

  public static void main(String[] args) {
    SpringApplication.run(CirrusApplication.class, args);
  }

  /**
   * Register the camel servlet for all urls starting rest/*
   *
   * @return the servlet
   */
  @Bean
  public ServletRegistrationBean<?> servletRegistrationBean() {
    ServletRegistrationBean<?> registration =
        new ServletRegistrationBean<CamelHttpTransportServlet>(
            new CamelHttpTransportServlet(), "/rest/*");
    registration.setName("CamelServlet");
    return registration;
  }
}
