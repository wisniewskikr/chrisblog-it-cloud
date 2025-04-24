package unit.com.example.services;

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

import static org.junit.jupiter.api.Assertions.*;
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
    public void findById_success() {

        // Given
        Long id = 1L;
        String text = "Hello World!";
        String portSecond = "8081";
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
        assertEquals(text, result.text());
        assertEquals(portSecond, result.portSecond());
        verify(helloWorldRepository, times(1)).findById(id);

    }

    @Test
    public void findById_exception() {

        // Given
        Long id = 1L;
        when(helloWorldRepository.findById(id)).thenReturn(Optional.empty());

        // When
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            helloWorldService.findById(id);
        });

        // Then
        assertEquals("Message doesn't exist", exception.getMessage());

    }

}