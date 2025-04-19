package unit.com.example.services;

import com.example.clients.SecondClient;
import com.example.dtos.HelloWorldFirstDto;
import com.example.dtos.HelloWorldSecondDto;
import com.example.services.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HelloWorldServiceTest {

    @Mock
    private SecondClient secondClient;

    @Mock
    private Environment environment;

    @InjectMocks
    private HelloWorldService helloWorldService;

    @Test
    void getPublicMessage() {

        // Given
        HelloWorldSecondDto secondDto = new HelloWorldSecondDto(1L, "Hello World, Public!", "8082");
        when(secondClient.findById(1L)).thenReturn(secondDto);
        when(environment.getProperty("local.server.port")).thenReturn("8081");

        // When
        HelloWorldFirstDto firstDto = helloWorldService.getPublicMessage();

        // Then
        assertEquals("Hello World, Public!", firstDto.text());
        assertEquals("8081", firstDto.portFirst());
        assertEquals("8082", firstDto.portSecond());
        verify(secondClient, times(1)).findById(1L);

    }

    @Test
    void getSecuredMessage() {

        // Given
        HelloWorldSecondDto secondDto = new HelloWorldSecondDto(1L, "Hello World, Secured!", "8082");
        when(secondClient.findById(2L)).thenReturn(secondDto);
        when(environment.getProperty("local.server.port")).thenReturn("8081");

        // When
        HelloWorldFirstDto firstDto = helloWorldService.getSecuredMessage();

        // Then
        assertEquals("Hello World, Secured!", firstDto.text());
        assertEquals("8081", firstDto.portFirst());
        assertEquals("8082", firstDto.portSecond());
        verify(secondClient, times(1)).findById(2L);

    }
}