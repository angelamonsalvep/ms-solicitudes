package co.com.credilya.solicitudes.r2dbc.tipoprestamo;

import co.com.credilya.solicitudes.model.tipoprestamo.TipoPrestamo;
import co.com.credilya.solicitudes.model.tipoprestamo.gateways.TipoPrestamoRepository;
import co.com.credilya.solicitudes.r2dbc.helper.ReactiveAdapterOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class TipoPrestamoRepositoryAdapter extends ReactiveAdapterOperations <TipoPrestamo, TipoPrestamoEntity, Long, TipoPrestamoReactiveRepository>
        implements TipoPrestamoRepository {

    private final TipoPrestamoReactiveRepository repository;

    public TipoPrestamoRepositoryAdapter (TipoPrestamoReactiveRepository repository,
                                        org.reactivecommons.utils.ObjectMapper mapper) {
        super(repository, mapper, entity -> TipoPrestamo.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build()
        );
        this.repository = repository;
    }

    @Override
    public Mono<TipoPrestamo> obtenerTipoPrestamoPorId(Long idTipoPrestamo) {
        return findById(idTipoPrestamo);
    }
}
