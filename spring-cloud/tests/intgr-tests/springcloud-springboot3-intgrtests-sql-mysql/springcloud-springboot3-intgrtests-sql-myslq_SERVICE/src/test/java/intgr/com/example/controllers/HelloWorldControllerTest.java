package intgr.com.example.controllers;

import com.example.Application;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class HelloWorldControllerTest {

    @ServiceConnection
    static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:5.7");

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    static {
        mysqlContainer.start();
    }

    @Test
    void testHelloWorld_public() {

        given()
        .when()
            .get("/message/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("text", equalTo("Hello World, Public!"))
            .body("portSecond", equalTo(String.valueOf(port)));

    }

    @Test
    void testHelloWorld_secured() {

        given()
                .when()
                .get("/message/2")
                .then()
                .statusCode(200)
                .body("id", equalTo(2))
                .body("text", equalTo("Hello World, Secured!"))
                .body("portSecond", equalTo(String.valueOf(port)));

    }

}