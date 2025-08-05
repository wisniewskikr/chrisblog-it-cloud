package intgr.com.example.controllers;

import com.example.Application;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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

    @BeforeAll
    static void setUp() {
        secondServiceContainer.start();
    }

    @AfterAll
    static void tearDown() {
        secondServiceContainer.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        String apiUrl = "http://" + secondServiceContainer.getHost() + ":" + secondServiceContainer.getMappedPort(8082);
        registry.add("api.url", () -> apiUrl);
    }

    @Test
    void defaultHelloWorld() {

        given()
            .when()
                .get()
            .then()
                .statusCode(200)
                .body("text", equalTo("Hello World, Public!"))
                .body("portFirst", equalTo(String.valueOf(port)))
                .body("portSecond", equalTo("8082"));

    }

    @Test
    void publicHelloWorld() {

        given()
            .when()
                .get("/public")
            .then()
                .statusCode(200)
                .body("text", equalTo("Hello World, Public!"))
                .body("portFirst", equalTo(String.valueOf(port)))
                .body("portSecond", equalTo("8082"));

    }

    @Test
    void securedHelloWorld() {

        given()
            .when()
                .get("/secured")
            .then()
                .statusCode(200)
                .body("text", equalTo("Hello World, Secured!"))
                .body("portFirst", equalTo(String.valueOf(port)))
                .body("portSecond", equalTo("8082"));

    }

}