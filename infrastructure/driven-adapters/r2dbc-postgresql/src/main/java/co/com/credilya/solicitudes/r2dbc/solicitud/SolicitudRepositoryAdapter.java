package co.com.credilya.solicitudes.r2dbc.solicitud;

import co.com.credilya.solicitudes.model.solicitud.Solicitud;
import co.com.credilya.solicitudes.model.solicitud.gateways.SolicitudRepository;
import co.com.credilya.solicitudes.model.tipoprestamo.TipoPrestamo;
import co.com.credilya.solicitudes.r2dbc.helper.ReactiveAdapterOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class SolicitudRepositoryAdapter
        extends ReactiveAdapterOperations<Solicitud, SolicitudEntity, Long, SolicitudReactiveRepository>
        implements SolicitudRepository {

    public SolicitudRepositoryAdapter(SolicitudReactiveRepository solicitudReactiveRepository,
                                      org.reactivecommons.utils.ObjectMapper mapper) {
        super(solicitudReactiveRepository, mapper, entity -> Solicitud.builder()
                .id(entity.getId())
                .tipoDocumento(entity.getTipoDocumento())
                .numeroDocumento(entity.getNumeroDocumento())
                .monto(entity.getMonto())
                .plazo(entity.getPlazo())
                .tipoPrestamo(entity.getTipoPrestamoId() != null ? Solicitud.builder().tipoPrestamo(new TipoPrestamo(entity.getTipoPrestamoId(), null, null)).build().getTipoPrestamo() : null)
                .estado(entity.getEstado())
                .fechaCreacion(entity.getFechaCreacion())
                .build()
        );
    }

    @Override
    public Mono<Solicitud> crearSolicitud(Solicitud solicitud) {
        return save(solicitud)
            .map(saved -> {
                solicitud.setId(saved.getId()); // Asigna el id generado
                return solicitud; // Retorna el objeto original con TipoPrestamo completo
            });
    }

    @Override
    protected SolicitudEntity toData(Solicitud solicitud) {
        SolicitudEntity entity = super.toData(solicitud);
        // Asignar tipoPrestamoId manualmente
        entity.setTipoPrestamoId(solicitud.getTipoPrestamo() != null ? solicitud.getTipoPrestamo().getId() : null);
        return entity;
    }
}
