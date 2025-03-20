package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao.IAuthorityDAO;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements IAuthorityService {

    @Autowired
    private IAuthorityDAO authorityDAO;

    @Override
    public List<Authority> findAllAuthorities() {
        return authorityDAO.findAllAuthorities();
    }

    @Override
    public Authority findAuthorityById(Integer id) {
        return authorityDAO.findAuthorityById(id);
    }

    @Override
    public void saveAuthority(Authority authority) {
authorityDAO.saveAuthority(authority);
    }

    @Override
    public void deleteAuthority(Integer id) {
authorityDAO.deleteAuthority(id);
    }
}
