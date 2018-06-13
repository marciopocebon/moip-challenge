# moip-challenge

Moip Technical challenge 

## Requirements

For building and running the application you need:

- [Gradle](https://gradle.org/)

## Running

First, clone the project:

```shell
git clone https://github.com/ertyui/moip-challenge.git
cd moip-challenge
```

Configure data source in application.properties:

```
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc\:mysql\://localhost/moip
spring.datasource.username=root
```

## Running

```shell
cd moip-challenge
mvn spring-boot:run
```

##  Testing

```shell
mvn test
```