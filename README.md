# spring-integrationflow-dsl
Spring Integration Flow

# Spring Initializer Dependencies
![Spring Initializer Dependencies](src/main/resources/screenshots/0-spring-initializer.png)


# Simple Configuration
![Simple Configuration](src/main/resources/screenshots/1-Simple-Configuration.png)
1. To start SimpleOne Flow:
   1. Enable "simpleOne" profile in application.properties using:
   2. ```properties
        spring.profiles.active=simpleOne
      ```
   3. Start the Spring Boot application
   4. Spring boot will create bean of SimpleOneConfiguration class and instantiate the IntegrationFlow
2. To start SimpleTwo Flow:
    1. Enable "simpleTwo" profile in application.properties using:
    2. ```properties
        spring.profiles.active=simpleTwo
       ```
    3. Start the Spring Boot application
    4. Spring boot will create bean of SimpleTwoConfiguration class and instantiate the IntegrationFlow


# File Processing Configuration
1. To start FileOne Flow:
   1. Enable "fileOne" profile in application.properties using:
   2. ```properties
        spring.profiles.active=fileOne
      ```
   3. Start the Spring Boot application
   4. Spring boot will create bean of FileOneConfiguration class and instantiate the IntegrationFlow
2. To start FileTwo Flow:
   1. Enable "fileTwo" profile in application.properties using:
   2. ```properties
        spring.profiles.active=fileTwo
       ```
   3. Start the Spring Boot application
   4. Spring boot will create bean of FileTwoConfiguration class and instantiate the IntegrationFlow


