# springboot - microbase

springboot - microbase is an open-source starter project for quickly building `scalable` and `maintainable` Spring Boot-based microservices.

The project provides a solution for `centralized log collection` using Logstash, Elasticsearch, and Kibana, alongside `monitoring capabilities` with Prometheus, Jaeger, and Grafana. It also includes basic JPA functionality to facilitate database interactions.

For designing the `database schema`, we recommend using the following project: [Evocelot/liquibase-base ](https://github.com/Evocelot/liquibase-base).

## Technologies used

- Java 21
- SpringBoot 3.4.3
- Docker / Podman
- Make

## Core functions

- A Makefile simplifies the application's startup process.
- The application can be easily packaged as a Docker image.
- Built-in logging, metrics, and tracing (can be enabled/disabled as needed):
    - Centralized log collection using ELK Stack (Elasticsearch, Logstash, Kibana).
    - Metrics monitoring with Prometheus and Grafana.
    - Distributed tracing support with Jaeger.
- Provides sample endpoints for managing example entities.
- Includes basic error handling.
- Automatically logs all incoming requests and outgoing responses.
- Supports JPA-based database integration.

## Prerequisites

To ensure the application functions correctly, you must first `start the database container`.

To set up the database container properly, the following project will assist you: [Evocelot/liquibase-base](https://github.com/Evocelot/liquibase-base)

The liquibase-base project helps start the appropriate database container and create the necessary database schema.

## How to run

The project includes a `Makefile` to simplify application startup. Each Makefile target can be executed independently.

> **_NOTE:_** If you are using Docker instead of Podman, replace `podman` with `docker` in the Makefile commands.

### Run with full stack

To run the application along with ELK stack and observability features, execute:

```bash
make start-monitoring-containers
make start-kafka
make start-local-container
```

This command starts the following containers:

- elasticsearch
- logstash
- kibana
- jaeger
- prometheus
- grafana
- kafka
- zookeeper
- kafka-ui
- sample-module

By default, the sample-module runs on port `8080`.
The swagger UI can be accessed at: http://localhost:8080/swagger-ui/index.html

### Run the module only

To run only the module only:

```bash
make start-local-container
```

> **_NOTE:_** To disable log collection, tracing and communication via kafka, manually set the `LOGSTASH_ENABLED`,  `TRACING_ENABLED` and `KAFKA_ENABLED` environment variables to `"false"` in the `Makefile`.

## Documentation

Detailed documentation is available here: [Documentation](/docs/index.md)

## Contributions

Contributions to the project are welcome! If you find issues or have suggestions for improvements, feel free to open an issue or submit a pull request.
