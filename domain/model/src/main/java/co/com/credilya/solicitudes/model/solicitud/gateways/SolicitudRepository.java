package co.com.credilya.solicitudes.model.solicitud.gateways;

import co.com.credilya.solicitudes.model.solicitud.Solicitud;
import reactor.core.publisher.Mono;

public interface SolicitudRepository {
    Mono<Solicitud> crearSolicitud(Solicitud solicitud);
}
