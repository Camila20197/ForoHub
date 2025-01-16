package foro.hub.Foro_Hub.domain.topicos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad Topico.
 *
 * Define métodos personalizados para realizar consultas específicas sobre los tópicos.
 */
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    /**
     * Encuentra un tópico por su título y mensaje.
     *
     * @param titulo El título del tópico.
     * @param mensaje El mensaje del tópico.
     * @return Un Optional que contiene el tópico si se encuentra, o Optional.empty() si no se encuentra.
     */
    Optional<Topico> findByTituloAndMensaje(String titulo, String mensaje);

    /**
     * Verifica si existe un tópico con el título dado y un ID diferente al proporcionado.
     *
     * @param titulo El título del tópico.
     * @param id El ID del tópico a excluir de la búsqueda.
     * @return true si existe un tópico con el título dado y un ID diferente, false en caso contrario.
     */
    boolean existsByTituloAndIdNot(String titulo, Long id);

    /**
     * Verifica si existe un tópico con el mensaje dado y un ID diferente al proporcionado.
     *
     * @param mensaje El mensaje del tópico.
     * @param id El ID del tópico a excluir de la búsqueda.
     * @return true si existe un tópico con el mensaje dado y un ID diferente, false en caso contrario.
     */
    boolean existsByMensajeAndIdNot(String mensaje, Long id);
}
