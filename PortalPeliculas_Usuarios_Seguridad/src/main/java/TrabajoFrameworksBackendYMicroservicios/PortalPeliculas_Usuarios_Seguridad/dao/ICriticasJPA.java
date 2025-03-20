package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.Criticas;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICriticasJPA extends JpaRepository<Criticas, Integer> {
    List<Criticas> findByUserId(Integer userId);
    List<Criticas> findByPeliculaId(Integer peliculaId);
    Optional<Criticas> findById(Integer id);

    Optional<Criticas> findByUserAndPeliculaId(User user, Integer peliculaId);

    List<Criticas> findCriticasByUser_UsernameContainingIgnoreCase(String userUsername);
}
