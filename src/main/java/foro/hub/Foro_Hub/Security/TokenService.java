package foro.hub.Foro_Hub.Security;



import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import foro.hub.Foro_Hub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Servicio encargado de generar y verificar tokens JWT (JSON Web Tokens) para la autenticación de usuarios.
 * Los JWT son un estándar abierto para transmitir información de forma segura entre partes.
 * Esta clase proporciona métodos para crear tokens para usuarios autenticados y extraer la información de
 * inicio de sesión del usuario a partir de un token válido.
 */

@Service
public class TokenService {

    /**
     * Clave secreta utilizada para firmar y verificar los tokens JWT.
     * Esta clave debe mantenerse segura y confidencial.
     */
    @Value("${api.security.secret}")
    private String apiSecret;

    /**
     * Genera un nuevo token JWT para el usuario especificado.
     *
     * @param usuario El usuario para el cual se generará el token.
     * @return El token JWT generado.
     * @throws RuntimeException Si ocurre un error al crear el token.
     */
    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("foro hub")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    /**
     * Obtiene el nombre de usuario (subject) a partir de un token JWT.
     *
     * @param token El token JWT a verificar.
     * @return El nombre de usuario asociado al token.
     * @throws RuntimeException Si el token es inválido o no se puede extraer el nombre de usuario.
     */
    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // validando firma
            verifier = JWT.require(algorithm)
                    .withIssuer("foro hub")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }


    /**
     * Genera una fecha de expiración para el token JWT.
     * Por defecto, la fecha de expiración se establece en 2 horas a partir de la hora actual.
     *
     * @return La fecha de expiración como un objeto Instant.
     */
    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}