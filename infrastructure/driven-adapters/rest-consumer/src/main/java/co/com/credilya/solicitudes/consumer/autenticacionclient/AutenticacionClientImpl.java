package co.com.credilya.solicitudes.consumer.autenticacionclient;

import co.com.credilya.solicitudes.model.autenticacion.AutenticacionGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class AutenticacionClientImpl implements AutenticacionClient, AutenticacionGateway {

    private final WebClient webClient;

    @Override
    public Mono<Boolean> existeUsuario(String tipoDocumento, String numeroDocumento) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/usuarios/existe")
                        .queryParam("tipoDocumento", tipoDocumento)
                        .queryParam("numeroDocumento", numeroDocumento)
                        .build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .doOnNext(response -> log.info("Respuesta de ms-autenticacion: {}", response))
                .onErrorResume(error -> Mono.error(new RuntimeException("Error al consumir ms-autenticacion", error)));
    }
}
