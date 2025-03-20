package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao.AuthorityDAOImpl;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao.IAuthorityJPA;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.Authority;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AuthorityrDAOTests {
    @Mock
    private IAuthorityJPA authorityJPA;

    @InjectMocks
    private AuthorityDAOImpl authorityDAO;

    @Test
    void testFindAllAuthorities() {
        Authority authority1 = new Authority();
        authority1.setId(1);
        authority1.setAuthority("ROLE_USER");

        Authority authority2 = new Authority();
        authority2.setId(2);
        authority2.setAuthority("ROLE_ADMIN");

        when(authorityJPA.findAll()).thenReturn(Arrays.asList(authority1, authority2));

        List<Authority> authorities = authorityDAO.findAllAuthorities();

        assertEquals(2, authorities.size());
        verify(authorityJPA, times(1)).findAll();
    }

    @Test
    void testFindAuthorityById() {
        Authority authority = new Authority();
        authority.setId(1);
        authority.setAuthority("ROLE_USER");

        when(authorityJPA.findById(1)).thenReturn(Optional.of(authority));

        Authority foundAuthority = authorityDAO.findAuthorityById(1);

        assertNotNull(foundAuthority);
        assertEquals("ROLE_USER", foundAuthority.getAuthority());
        verify(authorityJPA, times(1)).findById(1);
    }

    @Test
    void testSaveAuthority() {
        Authority authority = new Authority();
        authority.setId(1);
        authority.setAuthority("ROLE_USER");

        authorityDAO.saveAuthority(authority);

        verify(authorityJPA, times(1)).save(authority);
    }

    @Test
    void testDeleteAuthority() {
        authorityDAO.deleteAuthority(1);

        verify(authorityJPA, times(1)).deleteById(1);
    }
}
