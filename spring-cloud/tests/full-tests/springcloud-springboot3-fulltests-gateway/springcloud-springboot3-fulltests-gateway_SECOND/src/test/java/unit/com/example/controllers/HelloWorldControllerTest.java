package unit.com.example.controllers;

import com.example.controllers.HelloWorldController;
import com.example.dtos.HelloWorldSecondDto;
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
public class HelloWorldControllerTest {

    @Mock
    private HelloWorldService helloWorldService;

    @InjectMocks
    private HelloWorldController helloWorldController;

    @Test
    public void helloWorld() {

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