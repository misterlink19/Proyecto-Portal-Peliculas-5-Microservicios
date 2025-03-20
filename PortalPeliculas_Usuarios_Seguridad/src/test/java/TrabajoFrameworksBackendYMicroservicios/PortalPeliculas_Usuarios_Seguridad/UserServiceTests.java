package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao.IUserDAO;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.Authority;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.User;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private IUserDAO userDAO;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testFindAllUsers() {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("user1");

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("user2");

        when(userDAO.findAllUsers()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.findAllUsers();

        assertEquals(2, users.size());
        verify(userDAO, times(1)).findAllUsers();
    }


    @Test
    void testFindUserById() {
        User user = new User();
        user.setId(1);
        user.setUsername("user1");

        when(userDAO.findUserById(1)).thenReturn(user);

        User foundUser = userService.findUserById(1);

        assertNotNull(foundUser);
        assertEquals("user1", foundUser.getUsername());
        verify(userDAO, times(1)).findUserById(1);
    }


    @Test
    void testFindUserByUsername() {
        User user = new User();
        user.setId(1);
        user.setUsername("user1");

        when(userDAO.findUserByUsername("user1")).thenReturn(user);

        User foundUser = userService.findUserByUsername("user1");

        assertNotNull(foundUser);
        assertEquals("user1", foundUser.getUsername());
        verify(userDAO, times(1)).findUserByUsername("user1");
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password123");

        when(userDAO.findUserByUsername("user1")).thenReturn(null);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");

        userService.saveUser(user);

        verify(userDAO, times(1)).findUserByUsername("user1");
        verify(passwordEncoder, times(1)).encode("password123");
        verify(userDAO, times(1)).saveUser(user);
    }


    @Test
    void testDeleteUser() {
        userService.deleteUser(1);

        verify(userDAO, times(1)).deleteUser(1);
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("user1");

        userService.updateUser(user);

        verify(userDAO, times(1)).updateUser(user);
    }

}
