package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Criticas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICriticasService {
    Page<Criticas> findAllCriticas( Pageable pageable);
    Optional<Criticas> findCriticaById(Integer id);
    Page<Criticas> findCriticasByUserId(Integer userId, Pageable pageable);
    Page<Criticas> findCriticasByPeliculaId(Integer peliculaId, Pageable pageable);
    List<Criticas> findAllCriticasByPeliculaId(Integer peliculaId);
    Page<Criticas> findCriticasByUsuario(String username, Pageable pageable);

    void saveCritica(Criticas critica);
    void deleteCritica(Integer id);
}
