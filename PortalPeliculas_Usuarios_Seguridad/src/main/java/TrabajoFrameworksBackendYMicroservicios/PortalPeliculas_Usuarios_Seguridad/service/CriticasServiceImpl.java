package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao.ICriticasDAO;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.Criticas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriticasServiceImpl implements ICriticasService {

    @Autowired
    private ICriticasDAO criticasDAO;

    @Override
    public List<Criticas> findAllCriticas() {
        return criticasDAO.findAllCriticas();
    }

    @Override
    public Optional<Criticas> findCriticaById(Integer id) {
        return criticasDAO.findCriticaById(id);
    }

    @Override
    public List<Criticas> findCriticasByUserId(Integer userId) {
        return criticasDAO.findCriticasByUserId(userId);
    }

    @Override
    public List<Criticas> findCriticasByPeliculaId(Integer peliculaId) {
        return criticasDAO.findCriticasByPeliculaId(peliculaId);
    }

    @Override
    public List<Criticas> findCriticasByUsuario(String username) {
        return criticasDAO.findCriticasByUsuario(username);    }


    @Override
    public void saveCritica(Criticas critica) {
        criticasDAO.saveCritica(critica);
    }
    @Override
    public Optional<Criticas> findCriticaByUserAndPelicula(Integer userId, Integer peliculaId) {
        return criticasDAO.findCriticaByUserAndPelicula( userId,  peliculaId);
    }

    @Override
    public void deleteCritica(Integer id) {
        criticasDAO.deleteCritica(id);
    }

    @Override
    public void updateCritica(Criticas critica) {
        criticasDAO.updateCritica(critica);
    }

}
