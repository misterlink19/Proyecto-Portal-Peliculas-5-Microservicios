package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorityDAOImpl implements IAuthorityDAO {

    @Autowired
    private IAuthorityJPA authorityJPA;

    @Override
    public List<Authority> findAllAuthorities() {
        return authorityJPA.findAll();
    }

    @Override
    public Authority findAuthorityById(Integer id) {
        Optional<Authority> authority = authorityJPA.findById(id);
        return authority.orElse(null);
    }

    @Override
    public void saveAuthority(Authority authority) {
        authorityJPA.save(authority);
    }

    @Override
    public void deleteAuthority(Integer id) {
        authorityJPA.deleteById(id);
    }
}
