# Java-api-automation

This project is an API automated testing

## Pre-requisites:

- NodeJS
- Java 8 JDK
- Maven
- Rest-assured
- Docker


## Maven Profile ##

Maven profiles can be used to create customized build configurations, so in this project has been used profiles to set the environment and all URLs, such as:

- For `env=prod` (production):

```console
$ base_uri=https://jsonplaceholder.typicode.com
```


## Running tests ##

- To install all dependencies

```console
$ mvn clean install
```

- Run all tests, the parameter `env=prod` is default

```console
$ mvn clean test -Denv=prod
$ mvn clean test
```


## Running tests using docker

Dockerfile was obtained through [Docker Hub site](https://hub.docker.com/_/maven).


- Build Dockerfile and run all tests in docker container

```console
$ docker build .
```

- Once container is created, the tests can be run locally

```console
$ docker image ls
$ docker run -i -t <image_id> /bin/bash
$ mvn clean test
```

- Removing image after creation

```console
$ docker image ls
$ docker rmi -f <image_id>
```
