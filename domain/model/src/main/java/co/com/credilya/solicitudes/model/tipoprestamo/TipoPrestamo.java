package co.com.credilya.solicitudes.model.tipoprestamo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TipoPrestamo {
    private Long id;
    private String nombre;
    private String descripcion;
}
