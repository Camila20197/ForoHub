package foro.hub.Foro_Hub.domain.topicos;

import jakarta.validation.constraints.NotBlank;

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
