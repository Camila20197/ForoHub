package foro.hub.Foro_Hub.errores;

import foro.hub.Foro_Hub.ValidacionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Clase que maneja las excepciones que se producen en la aplicación.
 */
@RestControllerAdvice
public class TratadorDeErrores {

    /**
     * Maneja la excepción EntityNotFoundException.
     *
     * @return Una respuesta HTTP con código 404 (Not Found).
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    /**
     * Maneja la excepción MethodArgumentNotValidException.
     *
     * @param e La excepción que contiene los detalles de los errores de validación.
     * @return Una respuesta HTTP con código 400 (Bad Request) y una lista de errores de validación.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    /**
     * Maneja la excepción ValidacionException.
     *
     * @param e La excepción de validación personalizada.
     * @return Una respuesta HTTP con código 400 (Bad Request) y el mensaje de error.
     */
    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity tratarErrorDeValidacion(ValidacionException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    /**
     * Clase de registro que representa un dato de error de validación.
     */
    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
