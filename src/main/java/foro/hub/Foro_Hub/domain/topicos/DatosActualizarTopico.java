package foro.hub.Foro_Hub.domain.topicos;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        EstadoTopico estado
) {}
