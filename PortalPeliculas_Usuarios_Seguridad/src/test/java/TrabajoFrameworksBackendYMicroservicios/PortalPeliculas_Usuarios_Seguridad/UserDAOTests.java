package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao.IUserJPA;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao.UserDAOImpl;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.User;
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
public class UserDAOTests {
    @Mock
    private IUserJPA userJPA;

    @InjectMocks
    private UserDAOImpl userDAO;

    @Test
    void testFindAllUsers() {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("user1");

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("user2");

        when(userJPA.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userDAO.findAllUsers();

        assertEquals(2, users.size());
        verify(userJPA, times(1)).findAll();
    }

    @Test
    void testFindUserById() {
        User user = new User();
        user.setId(1);
        user.setUsername("user1");

        when(userJPA.findById(1)).thenReturn(Optional.of(user));

        User foundUser = userDAO.findUserById(1);

        assertNotNull(foundUser);
        assertEquals("user1", foundUser.getUsername());
        verify(userJPA, times(1)).findById(1);
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("user1");

        userDAO.saveUser(user);

        verify(userJPA, times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        userDAO.deleteUser(1);

        verify(userJPA, times(1)).deleteById(1);
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("user1");

        userDAO.updateUser(user);

        verify(userJPA, times(1)).save(user);
    }
}
