package foro.hub.Foro_Hub.domain.usuario;

/**
 * Clase de registro que representa los datos de autenticación de un usuario.
 * Se utiliza para recibir los datos de login y contraseña desde el cliente.
 */
public record DatosAutenticacionUsuario(

        String login,

        String clave) {
}
