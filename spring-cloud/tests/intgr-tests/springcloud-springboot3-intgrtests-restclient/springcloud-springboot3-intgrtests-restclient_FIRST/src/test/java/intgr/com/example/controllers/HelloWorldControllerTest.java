package intgr.com.example.controllers;

import com.example.Application;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class HelloWorldControllerTest {

    @Container
    static GenericContainer<?> secondServiceContainer = new GenericContainer<>("wisniewskikr/springcloud-springboot3-intgrtests-restclient_second:0.0.1")
            .withExposedPorts(8082);

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    static {
        secondServiceContainer.start();
    }

    @Test
    void defaultHelloWorld() {

        given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("text", equalTo("Hello World, Public!"))
                .body("portSecond", equalTo(String.valueOf(port)));

    }

    @Test
    void publicHelloWorld() {
    }

    @Test
    void securedHelloWorld() {
    }
}