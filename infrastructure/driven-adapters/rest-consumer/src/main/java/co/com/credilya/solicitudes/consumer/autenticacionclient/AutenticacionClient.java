package co.com.credilya.solicitudes.consumer.autenticacionclient;

import reactor.core.publisher.Mono;

public interface AutenticacionClient {
    /**
     * Verifica si un cliente existe en el sistema de autenticación.
     *
     * @param tipoDocumento tipo de documento (CC, TI, etc.)
     * @param numeroDocumento número del documento
     * @return Mono<Boolean> true si existe, false si no existe
     */
    Mono<Boolean> existeUsuario(String tipoDocumento, String numeroDocumento);
}
