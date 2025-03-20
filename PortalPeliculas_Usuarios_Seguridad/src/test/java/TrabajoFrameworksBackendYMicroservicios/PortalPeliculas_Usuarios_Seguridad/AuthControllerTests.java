package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.config.JwtUtil;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.controller.AuthController;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.Authority;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.User;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service.IAuthorityService;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTests {
    @Mock
    private IUserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private IAuthorityService authorityService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }
    @Test
    void testLogin() throws Exception {
        User user = new User();
        user.setUsername("user1");
        user.setEmail("user1@example.com");
        user.setPassword("password");

        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        user.setAuthority(authority);

        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER")
        );

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                "password",
                authorities
        );

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(userService.findUserByEmail("user1@example.com")).thenReturn(user);
        when(jwtUtil.generateToken(anyString(), anyString(), anyString())).thenReturn("testToken");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user1@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("testToken"))
                .andExpect(jsonPath("$.user.username").value("user1"))
                .andExpect(jsonPath("$.user.email").value("user1@example.com"))
                .andExpect(jsonPath("$.user.authority.authority").value("ROLE_USER"));

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userService, times(1)).findUserByEmail("user1@example.com");
        verify(jwtUtil, times(1)).generateToken(anyString(), anyString(), anyString());
    }

    @Test
    void testRegister() throws Exception {
        User user = new User();
        user.setEmail("user1@example.com");
        user.setPassword("password");

        when(userService.findUserByEmail("user1@example.com")).thenReturn(null);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user1@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk());

        verify(userService, times(1)).saveUser(any(User.class));
    }

    @Test
    void testRegisterUserAlreadyExists() throws Exception {
        User user = new User();
        user.setEmail("user1@example.com");

        when(userService.findUserByEmail("user1@example.com")).thenReturn(user);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user1@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isBadRequest());

        verify(userService, times(1)).findUserByEmail("user1@example.com");
    }

    @Test
    void testLoginInvalidCredentials() throws Exception {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new org.springframework.security.core.AuthenticationException("Credenciales incorrectas") {});

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user1@example.com\",\"password\":\"wrongPassword\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Credenciales incorrectas"));

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void testRegisterWithResponseValidation() throws Exception {
        User user = new User();
        user.setEmail("user1@example.com");
        user.setPassword("password");

        when(userService.findUserByEmail("user1@example.com")).thenReturn(null);
        doNothing().when(userService).saveUser(any(User.class));

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user1@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk());

        verify(userService, times(1)).saveUser(any(User.class));
    }

}
