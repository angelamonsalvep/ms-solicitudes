package co.com.credilya.solicitudes.usecase.solicitud;

import co.com.credilya.solicitudes.model.autenticacion.AutenticacionGateway;
import co.com.credilya.solicitudes.model.exception.DatosInvalidosException;
import co.com.credilya.solicitudes.model.exception.TipoPrestamoInvalidoException;
import co.com.credilya.solicitudes.model.exception.UsuarioNoExisteException;
import co.com.credilya.solicitudes.model.solicitud.Solicitud;
import co.com.credilya.solicitudes.model.solicitud.gateways.SolicitudRepository;
import co.com.credilya.solicitudes.model.tipoprestamo.gateways.TipoPrestamoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class SolicitudUseCase {
    private final SolicitudRepository solicitudRepository;
    private final TipoPrestamoRepository tipoPrestamoRepository;
    private final AutenticacionGateway autenticacionGateway;

    public Mono<Solicitud> crearSolicitud(Solicitud solicitud) {
        return validarDatosBasicos(solicitud)
                .then(autenticacionGateway.existeUsuario(
                        solicitud.getTipoDocumento(),
                        solicitud.getNumeroDocumento()
                ).flatMap(existe -> {
                    if (Boolean.FALSE.equals(existe)) {
                        return Mono.error(new UsuarioNoExisteException(
                                solicitud.getTipoDocumento(),
                                solicitud.getNumeroDocumento()
                        ));
                    }
                    return Mono.just(solicitud);
                }))
                .flatMap(sol -> {
                    if (sol.getTipoPrestamo() == null) {
                        return Mono.error(new TipoPrestamoInvalidoException("TipoPrestamo es null"));
                    }
                    return tipoPrestamoRepository.obtenerTipoPrestamoPorId(sol.getTipoPrestamo().getId())
                            .switchIfEmpty(Mono.error(new TipoPrestamoInvalidoException(sol.getTipoPrestamo().getId())))
                            .map(tp -> {
                                sol.setTipoPrestamo(tp);
                                return sol;
                            });
                })
                .flatMap(sol -> {
                    sol.setEstado("Pendiente de revisión");
                    sol.setFechaCreacion(LocalDateTime.now());
                    return solicitudRepository.crearSolicitud(sol);
                });
    }

    private Mono<Void> validarDatosBasicos(Solicitud solicitud) {
        if (solicitud.getMonto() == null || solicitud.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            return Mono.error(new DatosInvalidosException("El monto debe ser mayor a 0"));
        }
        if (solicitud.getPlazo() == null || solicitud.getPlazo() <= 0) {
            return Mono.error(new DatosInvalidosException("El plazo debe ser mayor a 0"));
        }
        if (solicitud.getNumeroDocumento() == null || solicitud.getNumeroDocumento().isBlank()) {
            return Mono.error(new DatosInvalidosException("El número de documento es obligatorio"));
        }
        return Mono.empty();
    }
}
