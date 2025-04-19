package com.example.unit.services;

import com.example.dtos.HelloWorldSecondDto;
import com.example.entities.HelloWorldEntity;
import com.example.repositories.HelloWorldRepository;
import com.example.services.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HelloWorldServiceTest {

    @Mock
    private HelloWorldRepository helloWorldRepository;

    @Mock
    private Environment environment;

    @InjectMocks
    private HelloWorldService helloWorldService;

    @Test
    public void testFindById() {

        // Given
        Long id = 1L;
        String text = "Hello World!";
        String portSecond = "8082";
        HelloWorldEntity mockEntity = new HelloWorldEntity();
        mockEntity.setId(id);
        mockEntity.setText(text);
        when(helloWorldRepository.findById(id)).thenReturn(Optional.of(mockEntity));
        when(environment.getProperty("local.server.port")).thenReturn(portSecond);

        // When
        HelloWorldSecondDto result = helloWorldService.findById(id);

        // Then
        assertNotNull(result);
        assertEquals(id, result.id());
        assertEquals("Hello World!", result.text());
        assertEquals("8082", result.portSecond());
        verify(helloWorldRepository, times(1)).findById(id);

    }

}