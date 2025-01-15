package foro.hub.Foro_Hub.domain.topicos;

import java.time.LocalDateTime;

public record DatosListadoTopicos (

        String titulo,

        String mensaje,

       LocalDateTime fecha,

        String autor,

        String curso

) {
    public DatosListadoTopicos(Topico topico) {

        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getAutor(), topico.getCurso());
    }
}
