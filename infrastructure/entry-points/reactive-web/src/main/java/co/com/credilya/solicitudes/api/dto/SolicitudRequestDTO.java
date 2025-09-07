package co.com.credilya.solicitudes.api.dto;

import java.math.BigDecimal;

public record SolicitudRequestDTO(
    String tipoDocumento,
    String numeroDocumento,
    BigDecimal monto,
    Integer plazo,
    Long tipoPrestamoId
) {}
