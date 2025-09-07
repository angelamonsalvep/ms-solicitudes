package co.com.credilya.solicitudes.model.exception;

public class UsuarioNoExisteException extends RuntimeException{
    public UsuarioNoExisteException(String tipoDocumento, String numeroDocumento) {
        super("El usuario con " + tipoDocumento + " " + numeroDocumento + " no existe en el sistema");
    }
}
