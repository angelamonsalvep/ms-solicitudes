package co.com.credilya.solicitudes.model.exception;

public class TipoPrestamoInvalidoException extends RuntimeException{
    public TipoPrestamoInvalidoException(Long idTipoPrestamo) {
        super("El tipo de prestamo con id " + idTipoPrestamo + " no es valido");
    }

    public TipoPrestamoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
