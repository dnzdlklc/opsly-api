Opsly Coding Test

As per requirements simple API to retrieve data feed from provided dummy endpoints

The application can be executed and run via Maven with Spring Plugin once at the root of the application DIR:

**Build:** `mvn clean compile install Run: mvn spring-boot:run`

Alternatively a docker file is available to be built and run:

**Build First:** `mvn clean compile install`

**Docker:** `docker build -t opsly-coding-test . && docker run -p 8080:8080 --name opsly-api opsly-coding-test`

Few tests are available within the test directory too which run by default during build.