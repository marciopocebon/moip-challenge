[![CircleCI](https://circleci.com/gh/marioalvial/moip-challenge/tree/master.svg?style=svg)](https://circleci.com/gh/marioalvial/moip-challenge/tree/master)
[![codecov](https://codecov.io/gh/marioalvial/moip-challenge/branch/master/graph/badge.svg)](https://codecov.io/gh/marioalvial/moip-challenge)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ce42dadb1f884bb5ad2ae0f9da452494)](https://www.codacy.com/app/marioalvial/moip-challenge?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=marioalvial/moip-challenge&amp;utm_campaign=Badge_Grade)
[![lifeclye](https://img.shields.io/badge/lifecycle-maturing-blue.svg)](https://codecov.io/gh/marioalvial/moip-challenge)

# moip-challenge

Moip Technical challenge 

## Requirements

For building and running the application you need:

- [Gradle](https://gradle.org/)
- [Docker](https://www.docker.com/)

## Documentation
[Postman](https://documenter.getpostman.com/view/2673922/RVnZhyTK)

## Running

First, clone the project:

```shell
git clone https://github.com/marioalvial/moip-challenge.git
cd moip-challenge
```
Running with docker:

```shell
./gradlew build && docker-compose up --build
```

Running locally:
Configure data source in application.yml:

```
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: dbc:mysql://localhost:{YOUR_PORT}/{YOUR_DB_NAME}
    username: {YOUR_DB_USERNAME}
    password: {YOUR_DB_PASSWORD}
```
If your port is 3306 and your database doesn't have password you can omit them

## Continuous Integration and Test Coverage
Test Coverage configured on CodeCov. Checkout the [test coverage here](https://codecov.io/gh/marioalvial/moip-challenge).

Continuous Integration is configured on CircleCI. Checkout the [continuous integration here](https://circleci.com/gh/marioalvial/moip-challenge)

##  Testing

```shell
./gradlew test
```

## Diagram

![Diagram](./Diagram.png)

## Built With

- [Kotlin](https://kotlinlang.org/) - Programming language
- [IntelliJ](https://www.jetbrains.com/idea/) - IDE
- [Spring](https://spring.io/) - Java Framework
- [Gradle](https://gradle.org/) - Dependency Management
- [Docker](https://www.docker.com/) - Containerization Platform
