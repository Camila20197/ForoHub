package foro.hub.Foro_Hub.Controller;

import foro.hub.Foro_Hub.Security.DatosJWTToken;
import foro.hub.Foro_Hub.Security.TokenService;
import foro.hub.Foro_Hub.domain.usuario.DatosAutenticacionUsuario;
import foro.hub.Foro_Hub.domain.usuario.Usuario;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para gestionar la autenticación de usuarios.
 *
 * Este controlador proporciona un endpoint para que los usuarios puedan autenticarse
 * y obtener un token JWT para acceder a recursos protegidos.
 */
@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    /**
     * Autenticación de usuario.
     *
     * Este método recibe las credenciales de usuario (login y contraseña)
     * y realiza la autenticación utilizando el `AuthenticationManager`.
     * Si la autenticación es exitosa, genera un token JWT y lo devuelve al cliente.
     *
     * @param datosAutenticacionUsuario Objeto que contiene las credenciales de usuario.
     * @return Una respuesta HTTP con el código 200 (OK) y el token JWT generado.
     */
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

}
