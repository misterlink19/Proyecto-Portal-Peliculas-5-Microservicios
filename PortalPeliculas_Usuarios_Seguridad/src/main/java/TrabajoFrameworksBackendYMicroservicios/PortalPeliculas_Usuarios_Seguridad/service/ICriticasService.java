package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.Criticas;

import java.util.List;
import java.util.Optional;

public interface ICriticasService {
    List<Criticas> findAllCriticas();
    Optional<Criticas> findCriticaById(Integer id);
    List<Criticas> findCriticasByUserId(Integer userId);
    List<Criticas> findCriticasByPeliculaId(Integer peliculaId);
    List<Criticas> findCriticasByUsuario(String username);

    void saveCritica(Criticas critica);
    void deleteCritica(Integer id);
    void updateCritica(Criticas critica);

    Optional<Criticas> findCriticaByUserAndPelicula(Integer userId, Integer peliculaId);
}
