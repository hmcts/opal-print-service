# NOTE
This repository is archived as this functionality will be part of opal-fines-service. 


# Opal Print Service

[![API Docs](https://img.shields.io/badge/API%20Docs-Print_Services-e140ad.svg)](https://hmcts.github.io/cnp-api-docs/swagger.html?url=https://hmcts.github.io/cnp-api-docs/specs/opal-print-service.json)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=uk.gov.hmcts.reform%3Aopal-print-service&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=uk.gov.hmcts.reform%3Aopal-print-service)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=uk.gov.hmcts.reform%3Aopal-print-service&metric=coverage)](https://sonarcloud.io/summary/new_code?id=uk.gov.hmcts.reform%3Aopal-print-service)

## Purpose

The purpose of this service is to generate documents for printing based on Apache FOP that generates PDF from a given template

## Building and deploying the application

### Building the application

The project uses [Gradle](https://gradle.org) as a build tool. It already contains
`./gradlew` wrapper script, so there's no need to install gradle.

To build the project execute the following command:

```bash
  ./gradlew clean build
```

### Running the application

Create the image of the application by executing the following command:

```bash
  ./gradlew assemble
```

Create docker image:

```bash
  docker-compose build
```

Run the distribution (created in `build/install/opal-print-service` directory)
by executing the following command:

```bash
  docker-compose up
```

This will start the API container exposing the application's port
(set to `4660` in this template app).

In order to test if the application is up, you can call its health endpoint:

```bash
  curl http://localhost:4660/health
```

You should get a response similar to this:

```
  {"status":"UP","diskSpace":{"status":"UP","total":249644974080,"free":137188298752,"threshold":10485760}}
```

### Alternative script to run application

To skip all the setting up and building, just execute the following command:

```bash
./bin/run-in-docker.sh
```

For more information:

```bash
./bin/run-in-docker.sh -h
```

Script includes bare minimum environment variables necessary to start api instance. Whenever any variable is changed or any other script regarding docker image/container build, the suggested way to ensure all is cleaned up properly is by this command:

```bash
docker-compose rm
```

It clears stopped containers correctly. Might consider removing clutter of images too, especially the ones fiddled with:

```bash
docker images

docker image rm <image-id>
```

There is no need to remove postgres and java or similar core images.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

