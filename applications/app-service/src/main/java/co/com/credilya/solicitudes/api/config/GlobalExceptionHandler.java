package co.com.credilya.solicitudes.api.config;

import co.com.credilya.solicitudes.model.exception.DatosInvalidosException;
import co.com.credilya.solicitudes.model.exception.TipoPrestamoInvalidoException;
import co.com.credilya.solicitudes.model.exception.UsuarioNoExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNoExisteException.class)
    public ResponseEntity<String> handleUsuarioNoExisteException(UsuarioNoExisteException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DatosInvalidosException.class)
    public ResponseEntity<String> handleDatosInvalidosException(DatosInvalidosException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TipoPrestamoInvalidoException.class)
    public ResponseEntity<String> handleTipoPrestamoInvalidoException(TipoPrestamoInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Puedes agregar más manejadores de excepciones aquí
}
