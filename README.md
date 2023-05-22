# Avios Points

Avios Points is a Spring Boot application that calculates the Avios points for flights based on the departure airport, arrival airport, and optional cabin code. It provides a RESTful API to retrieve the calculated Avios points.

## Prerequisites

- Java 11
- Maven
- Docker || MySQL

## Setup

1. Clone the repository:

```
git clone https://github.com/leedward/Avios-Points.git
```

2. Start a MySQL server instance in Docker:

```
docker run --name avios-db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=avios -d mysql:8.0.26
```

This will start a MySQL instance in a Docker container named `avios-db`, accessible via `localhost:3306`. The database name is `avios` and the root user password is `root`.

3. Update the application.properties file in your project's src/main/resources directory:

Example:
```
spring.datasource.url=jdbc:mysql://localhost:3306/avios
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

This will set up your Spring Boot application to connect to the MySQL database running in Docker.

Also choose an appropriate logging level

```
logging.level.com.iagl=DEBUG||INFO
```

4. Build the project using Maven:

```
cd Avios-Points
mvn clean install
```

5. Run the application:

```
mvn spring-boot:run
```

The application will start running on `http://localhost:8080`.

## Usage

You can calculate the Avios points by sending a GET request to the `/avios` endpoint with the following parameters:

- `departureAirportCode`: The code of the departure airport.
- `arrivalAirportCode`: The code of the arrival airport.
- `cabinCode` (optional): The cabin code (e.g., "M", "W", "J", "F").

Example usage with cURL:

```
curl -X POST -H "Content-Type: application/json" -d '{"departureAirportCode":"LHR","arrivalAirportCode":"JFK","cabinCode":"M"}' "http://localhost:8080/avios"
```

## Swagger UI
You can also access the Swagger UI at:
http://localhost:8080/swagger-ui.html

## API Response

The API will return a JSON object containing the calculated Avios points based on the provided parameters. The response format is as follows:

```json
{
  "M": 3200
}
```

## Technologies Used

- Spring Boot
- Spring Data JPA
- Lombok
- MySQL
- Docker
- Swagger UI

### Reference Documentation

For further reference, please consider the following sections related to the technologies used in this project:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [MySQL](https://dev.mysql.com/doc/)
* [Docker](https://docs.docker.com/)
* [Project Lombok](https://projectlombok.org/features/all)
* [Swagger](https://swagger.io/docs/)
* [Java 11 Documentation](https://docs.oracle.com/en/java/javase/11/)

## License

This project is licensed under the terms of the MIT license. See the [LICENSE](LICENSE.md) file for license rights and limitations.


