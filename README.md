[![CircleCI](https://circleci.com/gh/marioalvial/moip-challenge/tree/master.svg?style=svg)](https://circleci.com/gh/marioalvial/moip-challenge/tree/master)
[![codecov](https://codecov.io/gh/marioalvial/moip-challenge/branch/master/graph/badge.svg)](https://codecov.io/gh/marioalvial/moip-challenge)
[![lifeclye](https://img.shields.io/badge/lifecycle-maturing-blue.svg)](https://codecov.io/gh/marioalvial/moip-challenge)

# moip-challenge

Moip Technical challenge 

## Requirements

For building and running the application you need:

- [Gradle](https://gradle.org/)

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
If your port is 3306 and your db doesn't have password you can omit them

##  Testing

```shell
./gradlew test
```

## Built With

- [IntelliJ](https://www.jetbrains.com/idea/) - IDE
- [Spring](https://spring.io/) - Java Framework
- [Gradle](https://gradle.org/) - Dependency Management
- [Docker](https://www.docker.com/) - Containerization Platform
