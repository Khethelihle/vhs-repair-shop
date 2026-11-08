## VHS Store API 

How to run this application and test all endpoints. Ensure you at the project root folder before running the commands below.

#### Run the Application 

`mvn spring-boot:run`

### Run all tests 
`mvn clean test`

### Run individual test 

`mvn test -Dtest=ProductServiceTest`

`mvn test -Dtest=ProductControllerTest`

`mvn test -Dtest=OrderServiceTest`

`mvn test -Dtest=OrderControllerTest`

`mvn test -Dtest=SecurityIntegrationTest`

##### Once the application is running, access the following links to test the API and run queries against the database to see Products and Orders. Check [GetProductTest.sql](https://github.com/Khethelihle/vhs-repair-shop/blob/features/src/test/java/com/khethelihle/vhs/sql/GetProductTest.sql)

http://localhost:8080/swagger-ui/index.html#/

http://localhost:8080/h2-console/ 

### Code Dev (Assumptions)
* All test cases were written with assistance of an AI (Codex).
* Research on H2 Database was done with assistance of an AI (Gemini).
* Research on Springboot security was done with assistance of an AI (Gemini).
* Java SDK used -- Amazon Corretto 21.0.13
* Application should be portable hence exposed the credentials via [application property](https://github.com/Khethelihle/vhs-repair-shop/blob/features/src/main/resources/application.properties) file. 

