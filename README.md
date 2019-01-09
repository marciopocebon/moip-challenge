```
  __  __    ___    ___   ____       ____   _   _      _      _       _       _____   _   _    ____   _____
 |  \/  |  / _ \  |_ _| |  _ \     / ___| | | | |    / \    | |     | |     | ____| | \ | |  / ___| | ____|
 | |\/| | | | | |  | |  | |_) |   | |     | |_| |   / _ \   | |     | |     |  _|   |  \| | | |  _  |  _|
 | |  | | | |_| |  | |  |  __/    | |___  |  _  |  / ___ \  | |___  | |___  | |___  | |\  | | |_| | | |___
 |_|  |_|  \___/  |___| |_|        \____| |_| |_| /_/   \_\ |_____| |_____| |_____| |_| \_|  \____| |_____|
```
[![CircleCI](https://circleci.com/gh/marioalvial/moip-challenge/tree/master.svg?style=svg)](https://circleci.com/gh/marioalvial/moip-challenge/tree/master)
[![codecov](https://codecov.io/gh/marioalvial/moip-challenge/branch/master/graph/badge.svg)](https://codecov.io/gh/marioalvial/moip-challenge)
[![BCH compliance](https://bettercodehub.com/edge/badge/marioalvial/moip-challenge?branch=master)](https://bettercodehub.com/)
[![lifeclye](https://img.shields.io/badge/lifecycle-maturing-blue.svg)](https://codecov.io/gh/marioalvial/moip-challenge)

Technical challenge for Moip selective process

## Requirements

For building and running the application you need:

- [Gradle](https://gradle.org/)
- [Docker](https://www.docker.com/)

## Documentation
[Postman](https://documenter.getpostman.com/view/4456023/RzfmG7dV)

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/b5899ab4c00b3868e5fc#?env%5BMoip%20Challenge%5D=W3sia2V5IjoibG9jYWwiLCJ2YWx1ZSI6ImxvY2FsaG9zdDo4MDgwIiwiZW5hYmxlZCI6dHJ1ZX0seyJrZXkiOiJwcm9kIiwidmFsdWUiOiJodHRwczovL21vaXAtY2hhbGxlbmdlLXByb2QuaGVyb2t1YXBwLmNvbSIsImVuYWJsZWQiOnRydWV9XQ==)

## Running

First, clone the project:

```shell
git clone https://github.com/marioalvial/moip-challenge.git
cd moip-challenge
```

#### Running with docker

```shell
./gradlew build && docker-compose up --build
```

#### Running locally 
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

![Diagram](./diagram.png)

## Built With

- [Kotlin](https://kotlinlang.org/) - Programming language
- [IntelliJ](https://www.jetbrains.com/idea/) - IDE
- [Spring](https://spring.io/) - Java Framework
- [Gradle](https://gradle.org/) - Dependency Management
- [Docker](https://www.docker.com/) - Containerization Platform
