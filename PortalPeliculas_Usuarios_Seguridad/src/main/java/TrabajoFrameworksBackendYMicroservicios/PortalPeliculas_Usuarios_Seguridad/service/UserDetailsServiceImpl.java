package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.User;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements IUserDetailsService {
    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            user = userService.findUserByEmail(username);
            if (user == null) {
                throw new UsernameNotFoundException("Usuario no encontrado con el nombre de usuario: " + username);
            }
        }
        // Obtener la autoridad del usuario
        Authority authority = user.getAuthority();
        if (authority == null) {
            throw new UsernameNotFoundException("El usuario no tiene una autoridad asignada");
        }

        // Crear la lista de GrantedAuthority
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(authority.getAuthority())
        );

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
