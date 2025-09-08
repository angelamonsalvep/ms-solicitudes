package co.com.credilya.solicitudes.api;

import co.com.credilya.solicitudes.api.mapper.SolicitudDTOMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.mock;

@ContextConfiguration(classes = {ApiRest.class, ApiRestTest.TestBeans.class})
@WebFluxTest
class ApiRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private SolicitudDTOMapper mapper;

    @Autowired
    private co.com.credilya.solicitudes.usecase.solicitud.SolicitudUseCase solicitudUseCase;

    @Test
    void testCrearSolicitud() {
        // Datos de prueba
        var request = new co.com.credilya.solicitudes.api.dto.SolicitudRequestDTO(
            "CC", "123456789", java.math.BigDecimal.valueOf(10000), 12, 1L
        );
        // Configurar mocks
        org.mockito.Mockito.when(mapper.toModel(org.mockito.Mockito.any())).thenReturn(new co.com.credilya.solicitudes.model.solicitud.Solicitud());
        org.mockito.Mockito.when(mapper.toResponseDTO(org.mockito.Mockito.any())).thenReturn(
            new co.com.credilya.solicitudes.api.dto.SolicitudResponseDTO(
                1L, "CC", "123456789", java.math.BigDecimal.valueOf(10000), 12, "APROBADO", java.time.LocalDateTime.now(), 1L
            )
        );
        org.mockito.Mockito.when(solicitudUseCase.crearSolicitud(org.mockito.Mockito.any()))
            .thenReturn(reactor.core.publisher.Mono.just(new co.com.credilya.solicitudes.model.solicitud.Solicitud()));
        webTestClient.post()
            .uri("/api/v1/solicitud")
            .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isCreated();
    }

    @TestConfiguration
    static class TestBeans {
        @Bean
        public co.com.credilya.solicitudes.usecase.solicitud.SolicitudUseCase solicitudUseCase() {
            return mock(co.com.credilya.solicitudes.usecase.solicitud.SolicitudUseCase.class);
        }
        @Bean
        public co.com.credilya.solicitudes.api.mapper.SolicitudDTOMapper solicitudDTOMapper() {
            return mock(co.com.credilya.solicitudes.api.mapper.SolicitudDTOMapper.class);
        }
    }
}
