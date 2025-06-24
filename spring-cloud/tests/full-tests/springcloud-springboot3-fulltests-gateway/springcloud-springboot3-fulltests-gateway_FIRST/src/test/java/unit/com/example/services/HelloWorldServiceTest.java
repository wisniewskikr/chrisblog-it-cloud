package unit.com.example.services;

import com.example.dtos.HelloWorldSecondDto;
import com.example.services.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HelloWorldServiceTest {

    @Mock
    private RestClient restClient;

    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RestClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @InjectMocks
    private HelloWorldService helloWorldService;

    @Test
    void findByIdTest_public() {

        // Given
        Long id = 1L;
        HelloWorldSecondDto mockDto = new HelloWorldSecondDto(id, "Hello World, Public!", "8081");
        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/message/{id}", id)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(HelloWorldSecondDto.class)).thenReturn(mockDto);

        // When
        HelloWorldSecondDto resultDto = helloWorldService.findById(id);

        // Then
        assertEquals(mockDto, resultDto);

    }

}