[![CircleCI](https://circleci.com/gh/james-freitas/springboot-kotlin-postgres.svg?style=svg)](https://circleci.com/gh/james-freitas/springboot-kotlin-postgres)

## Spring boot Kotlin application

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.7.RELEASE/gradle-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.7.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


### Environment variables

- Set these before run the application
```bash
  export SPRING_DATASOURCE_URL=<db_url>
  export SPRING_DATASOURCE_USERNAME=<db_username>
  export SPRING_DATASOURCE_PASSWORD=<db_password>
```
### Features

#### Health Check
 1. Run the application: `./gradlew bootRun`
 2. Execute on terminal: `curl http://localhost:8080/actuator/health`
 
#### Jacoco
 1. Build the project: `./gradlew build`
 2. Run jacoco report: `./gradlew jacocoTestReport`
 
#### Detekt
 - Run jacoco report: `./gradlew detekt`
 
#### Ktlint
 1. Build the project: `./gradlew build`
 2. Run ktlint check: `./gradlew ktlintCheck`
 
 - If you find ktlint issues with tabs run: `./gradlew ktlintFormat`

#### Swagger
 1. Run the application: `./gradlew bootRun`
 2. Open in any browser: `http://localhost:8080/swagger-ui.html`
 

### How to set up and run the project

1. Update the project name in the following files:
  - `settings.gradle.kts`
  - `build.gradle.kts` (jacoco exclusion configs)
  - `SampleApplication` (change to your application name)
  - `api-docs-swagger.yaml` (change to your application name)
  

2. Create a postgres database and grant privileges on it to a database user

3. Set the environment variables
```bash
  export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/<database_name>
  export SPRING_DATASOURCE_USERNAME=<database_username>
  export SPRING_DATASOURCE_PASSWORD=<database_password>
```

4. Run the app with `./gradlew bootRun` 


5. Test it is working on terminal: `curl http://localhost:8080/actuator/health` (should get a 200)