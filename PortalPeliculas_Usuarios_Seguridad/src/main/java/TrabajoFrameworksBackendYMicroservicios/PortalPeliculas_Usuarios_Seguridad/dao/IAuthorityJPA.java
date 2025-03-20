package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorityJPA extends JpaRepository<Authority, Integer>
{
}
