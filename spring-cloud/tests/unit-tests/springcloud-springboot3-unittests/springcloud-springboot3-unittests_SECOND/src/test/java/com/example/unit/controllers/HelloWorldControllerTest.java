package com.example.unit.controllers;

import com.example.controllers.HelloWorldController;
import com.example.dtos.HelloWorldSecondDto;
import com.example.services.HelloWorldService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HelloWorldControllerTest {

    private HelloWorldService helloWorldService;
    private HelloWorldController helloWorldController;

    @BeforeEach
    public void setUp() {
        helloWorldService = mock(HelloWorldService.class);
        helloWorldController = new HelloWorldController(helloWorldService);
    }

    @Test
    public void testHelloWorld() {

        // Given
        Long id = 1L;
        String text = "Hello World!";
        String portSecond = "8082";
        HelloWorldSecondDto mockDto = new HelloWorldSecondDto(id, text, portSecond);
        when(helloWorldService.findById(id)).thenReturn(mockDto);

        // When
        ResponseEntity<HelloWorldSecondDto> response = helloWorldController.helloWorld(id);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockDto, response.getBody());
        verify(helloWorldService, times(1)).findById(id);

    }

}