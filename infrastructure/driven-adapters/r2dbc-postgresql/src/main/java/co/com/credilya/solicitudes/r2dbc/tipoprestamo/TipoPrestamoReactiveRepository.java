package co.com.credilya.solicitudes.r2dbc.tipoprestamo;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPrestamoReactiveRepository
        extends ReactiveCrudRepository<TipoPrestamoEntity, Long>,
        ReactiveQueryByExampleExecutor<TipoPrestamoEntity> {
}
