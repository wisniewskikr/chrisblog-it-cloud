package unit.com.example.controllers;

import com.example.controllers.HelloWorldController;
import com.example.dtos.HelloWorldFirstDto;
import com.example.dtos.HelloWorldSecondDto;
import com.example.services.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HelloWorldControllerTest {

    @Mock
    private HelloWorldService helloWorldService;
    @Mock
    private Environment environment;
    @InjectMocks
    private HelloWorldController helloWorldController;

    @Test
    public void publicMethod() {

        // Given
        Long id = 1L;
        HelloWorldSecondDto mockDto = new HelloWorldSecondDto(id, "Hello World, Public!", "8081");
        when(helloWorldService.findById(id)).thenReturn(mockDto);
        when(environment.getProperty("local.server.port")).thenReturn("8080");

        // When
        ResponseEntity<HelloWorldFirstDto> responseEntity = helloWorldController.publicMethod();

        // Then
        assertEquals(ResponseEntity.ok(new HelloWorldFirstDto(mockDto.id(), mockDto.text(), environment.getProperty("local.server.port"), mockDto.portSecond())), responseEntity);

    }

    @Test
    public void securedMethod() {

        // Given
        Long id = 2L;
        HelloWorldSecondDto mockDto = new HelloWorldSecondDto(id, "Hello World, Secured!", "8081");
        when(helloWorldService.findById(id)).thenReturn(mockDto);
        when(environment.getProperty("local.server.port")).thenReturn("8080");

        // When
        ResponseEntity<HelloWorldFirstDto> responseEntity = helloWorldController.securedMethod();

        // Then
        assertEquals(ResponseEntity.ok(new HelloWorldFirstDto(mockDto.id(), mockDto.text(), environment.getProperty("local.server.port"), mockDto.portSecond())), responseEntity);

    }

}