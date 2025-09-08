package co.com.credilya.solicitudes.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SolicitudResponseDTO(
    Long id,
    String tipoDocumento,
    String numeroDocumento,
    BigDecimal monto,
    Integer plazo,
    String estado,
    LocalDateTime fechaCreacion,
    TipoPrestamoDTO tipoPrestamo
) {}
