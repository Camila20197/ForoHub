package foro.hub.Foro_Hub.Controller;

import foro.hub.Foro_Hub.domain.topicos.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Esta clase representa un usuario del sistema.
 * Almacena información básica del usuario, como su nombre de usuario, correo electrónico y contraseña.
 * También incluye métodos para verificar la contraseña y actualizar la información del usuario.
 */

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private final TopicoRepository repositorio;

    public TopicoController(TopicoRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Crea un nuevo tópico.
     *
     * @param datos Los datos del nuevo tópico.
     * @param uriComponentsBuilder Utilería para construir URIs.
     * @return Una respuesta HTTP con el código 201 (Created) y la ubicación del recurso creado,
     *         o una respuesta HTTP con código 400 (Bad Request) si ya existe un tópico con el mismo título y mensaje.
     */
    @PostMapping
    @Transactional
    public ResponseEntity<String> crearTopico(@Valid @RequestBody DatosTopico datos, UriComponentsBuilder uriComponentsBuilder) {

        Topico topico = new Topico(datos);

        Optional<Topico> topicoExistente = repositorio.findByTituloAndMensaje(topico.getTitulo(), topico.getMensaje());
        if (topicoExistente.isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con el mismo título y mensaje.");
        }
        repositorio.save(topico);

        URI location = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        return ResponseEntity.created(location).body("Tópico creado con éxito.");
    }

    /**
     * Obtiene una lista paginada de tópicos.
     *
     * @param pageable Objeto Pageable para controlar la paginación.
     * @return Una página de objetos DatosListadoTopicos que representan los tópicos.
     */
    @GetMapping
    public Page<DatosListadoTopicos> listarTopicos(@PageableDefault(size = 10) Pageable pageable) {
        return repositorio.findAll(pageable).map(DatosListadoTopicos::new);
    }

    /**
     * Obtiene el detalle de un tópico específico.
     *
     * @param id El identificador del tópico.
     * @return Una respuesta HTTP con el código 200 (OK) y los detalles del tópico,
     *         o una respuesta HTTP con código 404 (Not Found) si el tópico no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopicos> obtenerDetalleTopico(@PathVariable Long id) {

        Optional<Topico> topicoOptional = repositorio.findById(id);


        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }


        Topico topico = topicoOptional.get();
        DatosListadoTopicos detalle = new DatosListadoTopicos(topico);

        return ResponseEntity.ok(detalle);
    }

    /**
     * Actualiza un tópico existente.
     *
     * @param id El identificador del tópico a actualizar.
     * @param datos Los nuevos datos del tópico.
     * @return Una respuesta HTTP con el código 200 (OK) si la actualización fue exitosa,
     *         o una respuesta HTTP con código 404 (Not Found) si el tópico no existe,
     *         o una respuesta HTTP con código 400 (Bad Request) si los datos de actualización no son válidos.
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> actualizarTopico(@PathVariable Long id, @Valid @RequestBody DatosActualizarTopico datos) {

        Optional<Topico> topicoOptional = repositorio.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retornar 404 si no se encuentra el tópico
        }

        Topico topico = topicoOptional.get();

        if (datos.titulo() != null && !datos.titulo().equals(topico.getTitulo())) {
            boolean tituloRepetido = repositorio.existsByTituloAndIdNot(datos.titulo(), id);
            if (tituloRepetido) {
                return ResponseEntity.badRequest().body("El título ya está en uso por otro tópico.");
            }
            topico.setTitulo(datos.titulo());
        }

        if (datos.mensaje() != null && !datos.mensaje().equals(topico.getMensaje())) {
            boolean mensajeRepetido = repositorio.existsByMensajeAndIdNot(datos.mensaje(), id);
            if (mensajeRepetido) {
                return ResponseEntity.badRequest().body("El mensaje ya está en uso por otro tópico.");
            }
            topico.setMensaje(datos.mensaje());
        }

        if (datos.estado() != null && !datos.estado().equals(topico.getEstado())) {
            topico.setEstado(datos.estado());
        }

        return ResponseEntity.ok("Tópico actualizado con éxito");
    }


    /**
     * Elimina un tópico.
     *
     * @param id El identificador del tópico a eliminar.
     * @return Una respuesta HTTP con el código 200 (OK) si el tópico fue eliminado con éxito,
     *         o una respuesta HTTP con código 404 (Not Found) si el tópico no existe.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarTopico(@PathVariable Long id) {

        Optional<Topico> topicoOptional = repositorio.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repositorio.deleteById(id);

        return ResponseEntity.ok("Tópico eliminado con éxito");
    }

}

