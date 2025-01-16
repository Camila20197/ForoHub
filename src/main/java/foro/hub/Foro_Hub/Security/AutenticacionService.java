package foro.hub.Foro_Hub.Security;


import foro.hub.Foro_Hub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de cargar los detalles de usuario para la autenticación de Spring Security.
 *
 * Implementa la interfaz UserDetailsService y proporciona el método loadUserByUsername()
 * para buscar y cargar los detalles del usuario en base a su nombre de usuario.
 */
@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Carga los detalles del usuario a partir de su nombre de usuario.
     *
     * @param username El nombre de usuario del usuario a buscar.
     * @return Un objeto UserDetails que representa al usuario encontrado.
     * @throws UsernameNotFoundException Si no se encuentra ningún usuario con el nombre de usuario especificado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }
}
