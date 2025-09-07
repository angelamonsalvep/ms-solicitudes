package co.com.credilya.solicitudes.r2dbc.solicitud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("solicitudes")
public class SolicitudEntity {
    @Id
    private Long id;

    private String tipoDocumento;
    private String numeroDocumento;
    private BigDecimal monto;
    private Integer plazo;
    private Long tipoPrestamoId;
    private String estado;
    private LocalDateTime fechaCreacion;

}
