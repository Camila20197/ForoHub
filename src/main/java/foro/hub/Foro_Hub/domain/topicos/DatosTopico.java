package foro.hub.Foro_Hub.domain.topicos;

import jakarta.validation.constraints.NotBlank;

/**
 * Clase de registro que representa los datos de un nuevo tópico.
 */
public record DatosTopico(

        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @NotBlank
        String autor,

        @NotBlank
        String curso

) {
}
