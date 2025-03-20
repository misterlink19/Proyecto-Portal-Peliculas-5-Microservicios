package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.controller.UsuariosController;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.User;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;



@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTests {
    @Mock
    private IUserService userService;

    @InjectMocks
    private UsuariosController usuariosController;

    @Mock
    private PasswordEncoder passwordEncoder;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usuariosController).build();
    }
    @Test
    void findAllShouldReturnAllUsers() throws Exception {
        User user1 = new User();
        user1.setId(1);
        User user2 = new User();
        user2.setId(2);
        List<User> users = Arrays.asList(user1, user2);

        when(userService.findAllUsers()).thenReturn(users);

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));

        verify(userService, times(1)).findAllUsers();
    }


    @Test
    void findByIdExistingIdShouldReturnUser() throws Exception {
        User user = new User();
        user.setId(1);

        when(userService.findUserById(1)).thenReturn(user);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

        verify(userService, times(1)).findUserById(1);
    }

    @Test
    void findByEmailExistingEmailShouldReturnUser() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");

        when(userService.findUserByEmail("test@example.com")).thenReturn(user);

        mockMvc.perform(get("/usuarios/email/test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("test@example.com")));

        verify(userService, times(1)).findUserByEmail("test@example.com");
    }

    @Test
    void update_ShouldUpdateUser() throws Exception {
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setEmail("user@example.com");
        existingUser.setPassword("oldEncodedPassword"); // Simulación de la contraseña ya encriptada

        User updatedUser = new User();
        updatedUser.setId(1);
        updatedUser.setEmail("user@example.com");
        updatedUser.setPassword("password"); // Nueva contraseña en texto plano

        String encodedPassword = "encodedPassword"; // Simulación de la nueva contraseña encriptada

        // Simular que el usuario existe en la BD
        when(userService.findUserById(1)).thenReturn(existingUser);

        // Simular la codificación de la nueva contraseña
        when(passwordEncoder.encode("password")).thenReturn(encodedPassword);

        mockMvc.perform(put("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"email\":\"user@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk());

        verify(userService, times(1)).updateUser(any(User.class)); // Verifica que se actualiza el usuario
        verify(passwordEncoder, times(1)).encode("password"); // Verifica que la contraseña se encripta
    }


    @Test
    void delete_ShouldDeleteUser() throws Exception {
        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(1);
    }
}
