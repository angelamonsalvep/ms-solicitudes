package co.com.credilya.solicitudes.model.tipoprestamo;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class TipoPrestamo {
    private Long id;
    private String nombre;
    private String descripcion;
}
