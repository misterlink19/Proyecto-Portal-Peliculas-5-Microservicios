package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Authority;

import java.util.List;

public interface IAuthorityService {
    List<Authority> findAllAuthorities();

    Authority findAuthorityById(Integer id);

    void saveAuthority(Authority authority);

    void deleteAuthority(Integer id);
}
