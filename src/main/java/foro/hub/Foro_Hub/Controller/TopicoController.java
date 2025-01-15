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

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private final TopicoRepository repositorio;

    public TopicoController(TopicoRepository repositorio) {
        this.repositorio = repositorio;
    }

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

//    @GetMapping("/listado")
//    public List<DatosListadoTopicos> TopicoController() {
//        return repositorio.findAll().stream().map(DatosListadoTopicos::new).toList();
//    }

    @GetMapping
    public Page<DatosListadoTopicos> listarTopicos(@PageableDefault(size = 10) Pageable pageable) {
        return repositorio.findAll(pageable).map(DatosListadoTopicos::new);
    }

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

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> actualizarTopico(@PathVariable Long id, @Valid @RequestBody DatosActualizarTopico datos) {

        Optional<Topico> topicoOptional = repositorio.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }


        Topico topico = topicoOptional.get();
        if (datos.titulo() != null) {
            topico.setTitulo(datos.titulo());
        }
        if (datos.mensaje() != null) {
            topico.setMensaje(datos.mensaje());
        }
        if (datos.estado() != null) {
            topico.setEstado(datos.estado());
        }


        return ResponseEntity.ok("Tópico actualizado con éxito");
    }

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

