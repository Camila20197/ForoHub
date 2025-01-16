package foro.hub.Foro_Hub.domain.usuario;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Entidad JPA que representa a un usuario del sistema.
 *
 * La entidad `Usuario` mapea una tabla llamada "usuarios" en la base de datos
 * y almacena información básica de autenticación del usuario.
 * Implementa la interfaz `UserDetails` de Spring Security para proporcionar
 * credenciales de autenticación.
 */

@Table(name = "usuarios")
@Entity(name = "Usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private  String clave;

    public Usuario() {
    }

    public Usuario(String login, String clave) {
        this.login = login;
        this.clave = clave;
    }

    /**
     * Devuelve una colección vacía de autoridades (roles) ya que la implementación
     * actual no maneja roles de usuario.
     *
     * Se puede modificar este método para devolver las autoridades
     * correspondientes a cada usuario si el sistema requiere control de acceso basado en roles.
     *
     * @return Una colección vacía de GrantedAuthority.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * Devuelve la clave del usuario.
     *
     * @return La clave del usuario.
     */
    @Override
    public String getPassword() {
        return clave;
    }

    /**
     * Devuelve el login del usuario.
     *
     * @return El login del usuario.
     */
    @Override
    public String getUsername() {
        return login;
    }

    // Getters y Setters para los atributos de la clase
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
