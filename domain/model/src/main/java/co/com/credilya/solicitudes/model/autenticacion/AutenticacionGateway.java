package co.com.credilya.solicitudes.model.autenticacion;

import reactor.core.publisher.Mono;

public interface AutenticacionGateway {
    Mono<Boolean> existeUsuario(String tipoDocumento, String numeroDocumento);
}
