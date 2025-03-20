package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao.IAuthorityDAO;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.Authority;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service.AuthorityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorityServiceTests {
    @Mock
    private IAuthorityDAO authorityDAO;

    @InjectMocks
    private AuthorityServiceImpl authorityService;

    @Test
    void testFindAllAuthorities() {
        Authority authority1 = new Authority();
        authority1.setId(1);
        authority1.setAuthority("ROLE_USER");

        Authority authority2 = new Authority();
        authority2.setId(2);
        authority2.setAuthority("ROLE_ADMIN");

        when(authorityDAO.findAllAuthorities()).thenReturn(Arrays.asList(authority1, authority2));

        List<Authority> authorities = authorityService.findAllAuthorities();

        assertEquals(2, authorities.size());
        verify(authorityDAO, times(1)).findAllAuthorities();
    }

    @Test
    void testFindAuthorityById() {
        Authority authority = new Authority();
        authority.setId(1);
        authority.setAuthority("ROLE_USER");

        when(authorityDAO.findAuthorityById(1)).thenReturn(authority);

        Authority foundAuthority = authorityService.findAuthorityById(1);

        assertNotNull(foundAuthority);
        assertEquals("ROLE_USER", foundAuthority.getAuthority());
        verify(authorityDAO, times(1)).findAuthorityById(1);
    }

    @Test
    void testSaveAuthority() {
        Authority authority = new Authority();
        authority.setId(1);
        authority.setAuthority("ROLE_USER");

        authorityService.saveAuthority(authority);

        verify(authorityDAO, times(1)).saveAuthority(authority);
    }

    @Test
    void testDeleteAuthority() {
        authorityService.deleteAuthority(1);

        verify(authorityDAO, times(1)).deleteAuthority(1);
    }
}
