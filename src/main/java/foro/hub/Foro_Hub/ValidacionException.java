package foro.hub.Foro_Hub;

/**
 * Excepción personalizada para representar errores de validación.
 */
public class ValidacionException extends RuntimeException {

    /**
     * Constructor de la excepción de validación.
     *
     * @param mensaje El mensaje de error de validación.
     */
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}
