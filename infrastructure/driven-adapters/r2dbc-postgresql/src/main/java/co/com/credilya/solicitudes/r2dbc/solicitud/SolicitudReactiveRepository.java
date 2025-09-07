package co.com.credilya.solicitudes.r2dbc.solicitud;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudReactiveRepository
        extends ReactiveCrudRepository<SolicitudEntity, Long>,
        ReactiveQueryByExampleExecutor<SolicitudEntity> {
}
