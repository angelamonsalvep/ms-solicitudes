package co.com.credilya.solicitudes.model.exception;

public class DatosInvalidosException extends RuntimeException{
    public DatosInvalidosException(String mensaje) {
        super(mensaje);
    }
}
