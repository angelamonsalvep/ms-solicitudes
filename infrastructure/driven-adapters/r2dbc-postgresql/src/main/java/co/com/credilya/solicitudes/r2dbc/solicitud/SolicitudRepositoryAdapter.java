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
                .tipoPrestamo(new TipoPrestamo(entity.getId(), "", ""))
                .estado(entity.getEstado())
                .fechaCreacion(entity.getFechaCreacion())
                .build()
        );
    }

    @Override
    public Mono<Solicitud> crearSolicitud(Solicitud solicitud) {
        return save(solicitud);
    }
}
