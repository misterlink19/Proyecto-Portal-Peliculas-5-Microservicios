package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.Criticas;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.User;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public class CriticasDAOImpl implements ICriticasDAO {

    @Autowired
    private ICriticasJPA criticasJPA;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public List<Criticas> findAllCriticas() {
        return criticasJPA.findAll();
    }

    @Override
    public Optional<Criticas> findCriticaById(Integer id) {
        return criticasJPA.findById(id);
    }

    @Override
    public List<Criticas> findCriticasByUserId(Integer userId) {
        return criticasJPA.findByUserId(userId);
    }

    @Override
    public List<Criticas> findCriticasByPeliculaId(Integer peliculaId) {
        return criticasJPA.findByPeliculaId(peliculaId);
    }

    @Override
    public List<Criticas> findCriticasByUsuario(String username) {
        return criticasJPA.findCriticasByUser_UsernameContainingIgnoreCase(username);
    }

    @Override
    public Optional<Criticas> findCriticaByUserAndPelicula( Integer userId, Integer peliculaId) {
        User user = userServiceImpl.findUserById(userId);
        if (user == null) {
            return Optional.empty(); // Usuario no encontrado, retornar vacío
        }
        return criticasJPA.findByUserAndPeliculaId(user, peliculaId);
    }

    @Override
    public void saveCritica(Criticas critica) {
        if (critica.getFecha() == null) {
            critica.setFecha(Instant.now());
        }
        criticasJPA.save(critica);
    }

    @Override
    public void deleteCritica(Integer id) {
        criticasJPA.deleteById(id);
    }

    @Override
    public void updateCritica(Criticas critica) {
        if (critica.getFecha() == null) {
            critica.setFecha(Instant.now());
        }
        criticasJPA.save(critica);
    }

}
