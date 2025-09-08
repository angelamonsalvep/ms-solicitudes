package co.com.credilya.solicitudes.model.solicitud;
import co.com.credilya.solicitudes.model.tipoprestamo.TipoPrestamo;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Solicitud {
    private Long id;           // ID autogenerado en BD
    private String tipoDocumento;    // ej: CC, CE, NIT, PAS
    private String numeroDocumento;  // hasta 20 caracteres
    private BigDecimal monto;        // monto solicitado (> 0)
    private Integer plazo;           // en meses (> 0)
    private TipoPrestamo tipoPrestamo;     // referencia a TipoPrestamo
    private String estado;           // por defecto "Pendiente de revisión"
    private LocalDateTime fechaCreacion; // seteada desde la aplicación
}
