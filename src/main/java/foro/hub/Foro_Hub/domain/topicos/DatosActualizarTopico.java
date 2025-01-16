package foro.hub.Foro_Hub.domain.topicos;

/**
 * Clase de registro que representa los datos para actualizar un tópico.
 */
public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        EstadoTopico estado
) {}
