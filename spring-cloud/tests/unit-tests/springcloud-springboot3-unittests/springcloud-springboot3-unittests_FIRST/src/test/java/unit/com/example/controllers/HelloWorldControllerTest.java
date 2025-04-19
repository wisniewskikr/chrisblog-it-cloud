package unit.com.example.controllers;

import com.example.controllers.HelloWorldController;
import com.example.dtos.HelloWorldFirstDto;
import com.example.services.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HelloWorldControllerTest {

    @Mock
    private HelloWorldService helloWorldService;

    @InjectMocks
    private HelloWorldController helloWorldController;

    @Test
    void defaultHelloWorld() {

        // Given
        HelloWorldFirstDto mockDto = new HelloWorldFirstDto("Hello World, Public", "8081", "8082");
        when(helloWorldService.getPublicMessage()).thenReturn(mockDto);

        // When
        ResponseEntity<HelloWorldFirstDto> response = helloWorldController.defaultHelloWorld();

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockDto, response.getBody());
        verify(helloWorldService, times(1)).getPublicMessage();

    }

    @Test
    void publicHelloWorld() {

        // Given
        HelloWorldFirstDto mockDto = new HelloWorldFirstDto("Hello World, Public", "8081", "8082");
        when(helloWorldService.getPublicMessage()).thenReturn(mockDto);

        // When
        ResponseEntity<HelloWorldFirstDto> response = helloWorldController.publicHelloWorld();

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockDto, response.getBody());
        verify(helloWorldService, times(1)).getPublicMessage();

    }

    @Test
    void securedHelloWorld() {

        // Given
        HelloWorldFirstDto mockDto = new HelloWorldFirstDto("Hello World, Secured", "8081", "8082");
        when(helloWorldService.getSecuredMessage()).thenReturn(mockDto);

        // When
        ResponseEntity<HelloWorldFirstDto> response = helloWorldController.securedHelloWorld();

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockDto, response.getBody());
        verify(helloWorldService, times(1)).getSecuredMessage();

    }
}