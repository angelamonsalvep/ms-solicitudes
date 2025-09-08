package co.com.credilya.solicitudes.api.config;

import co.com.credilya.solicitudes.api.ApiRest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import co.com.credilya.solicitudes.usecase.solicitud.SolicitudUseCase;
import co.com.credilya.solicitudes.api.mapper.SolicitudDTOMapper;

import static org.mockito.Mockito.mock;

@ContextConfiguration(classes = {ApiRest.class, ConfigTest.TestBeans.class})
@WebFluxTest
@Import({CorsConfig.class, SecurityHeadersConfig.class})
class ConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private SolicitudDTOMapper mapper;

    @Autowired
    private SolicitudUseCase solicitudUseCase;

    @Test
    void corsConfigurationShouldAllowOrigins() {
        var request = new co.com.credilya.solicitudes.api.dto.SolicitudRequestDTO(
            "CC", "123456789", java.math.BigDecimal.valueOf(10000), 12, 1L
        );
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
            .expectStatus().isCreated()
            .expectHeader().valueEquals("Content-Security-Policy",
                    "default-src 'self'; frame-ancestors 'self'; form-action 'self'")
            .expectHeader().valueEquals("Strict-Transport-Security", "max-age=31536000;")
            .expectHeader().valueEquals("X-Content-Type-Options", "nosniff")
            .expectHeader().valueEquals("Server", "")
            .expectHeader().valueEquals("Cache-Control", "no-store")
            .expectHeader().valueEquals("Pragma", "no-cache")
            .expectHeader().valueEquals("Referrer-Policy", "strict-origin-when-cross-origin");
    }

    @TestConfiguration
    static class TestBeans {
        @Bean
        public SolicitudUseCase solicitudUseCase() {
            return mock(SolicitudUseCase.class);
        }
        @Bean
        public SolicitudDTOMapper solicitudDTOMapper() {
            return mock(SolicitudDTOMapper.class);
        }
    }

}
