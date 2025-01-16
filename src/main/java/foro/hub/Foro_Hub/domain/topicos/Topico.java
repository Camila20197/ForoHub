package foro.hub.Foro_Hub.domain.topicos;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad JPA que representa un tópico de discusión.
 *
 * La entidad `Topico` mapea una tabla llamada "topicos" en la base de datos y almacena información
 * relacionada a un tópico de discusión.
 */
@Table(name = "topicos")
@Entity(name = "Topicos")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private EstadoTopico estado;

    private String autor;

    private String curso;

    public  Topico(){}

    /**
     * Constructor que inicializa el tópico a partir de un objeto `DatosTopico`.
     *
     * @param datosTopico Objeto que contiene los datos del tópico.
     */
    public Topico(DatosTopico datosTopico) {
        this.estado = EstadoTopico.ABIERTO;
        this.titulo = datosTopico.titulo();
        this.mensaje = datosTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.autor = datosTopico.autor();
        this.curso = datosTopico.curso();
    }

    // Getters y Setters para los atributos de la clase
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public EstadoTopico getEstado() {
        return estado;
    }

    public void setEstado(EstadoTopico estado) {
        this.estado = estado;
    }

    public String getCurso() {
        return curso;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topico topico = (Topico) o;
        return Objects.equals(id, topico.id) && Objects.equals(titulo, topico.titulo) && Objects.equals(mensaje, topico.mensaje) && Objects.equals(fechaCreacion, topico.fechaCreacion) && estado == topico.estado && Objects.equals(autor, topico.autor) && Objects.equals(curso, topico.curso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, mensaje, fechaCreacion, estado, autor, curso);
    }
}
