package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.Authority;
import java.util.List;

public interface IAuthorityDAO {
    List<Authority> findAllAuthorities();
    Authority findAuthorityById(Integer id);
    void saveAuthority(Authority authority);
    void deleteAuthority(Integer id);
}
