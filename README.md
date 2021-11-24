# Rezijiser-backend

## First time running

Run the docker-compose command on docker-compose.yml file in root directory of this project to generate a local MySQL docker container with `user: root, password: root` credentials.
The default database that is generated is called `rezijiser`

Afterwards, you can manually start/stop the docker container that gets generated, or with any built-in tools you like.

## Spring Boot

To start the project consider using [one of these options](https://spring.io/tools).
Initial start should run the flyway module to generate sql tables defined in db/migration/init.sql script
Additional local-machine specific application properties should be handled in a seperate ~spring boot~ application properties profile?
