package co.com.credilya.solicitudes.model.tipoprestamo.gateways;

import co.com.credilya.solicitudes.model.solicitud.Solicitud;
import co.com.credilya.solicitudes.model.tipoprestamo.TipoPrestamo;
import reactor.core.publisher.Mono;

public interface TipoPrestamoRepository {
    Mono<TipoPrestamo> obtenerTipoPrestamoPorId(Long idTipoPrestamo);
}
