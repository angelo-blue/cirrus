# Cirrus

## Building
1. load project into eclipse

2. execute mvn install on the cirrus (top level) pom


## Running locally

Preconditions:

- Ensure cyras is running locally, with some changes:
  - modified CasePersonRestService to accept url param in service
  - PersonSearchResultListJSONView attribute name 'persons' not 'results'
  - CasePersonRestService exposed in application.properties

1. start cyras on port 8081 (in start_cyras_server1.cmd, add the arg -Djboss.socket.binding.port-offset=1)

2. start the local load balancer:

```
> java -jar target/cyras-local-alb.jar
```

3. start cirrus:

```
> java -Dserver.port=8080 -jar target/backend-0.0.1-SNAPSHOT.jar
```

4. navigate to http://localhost:10000/


## Running the front-end
### Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

```
D:\mikev\git\cirrus\frontend> ng serve
```

### Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

### Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

### Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

### Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

### Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.


### Notes for running locally:
run the client

1. run the mock rest server (assumes /server/database.json exists)

```
D:\mikev\git\cirrus\frontend> npm run server
```

2. run the ng server

```
D:\mikev\git\cirrus\frontend> ng serve --proxy-config local-proxy\proxy.config.json
```

3. navigate to page http://localhost:4200/home
