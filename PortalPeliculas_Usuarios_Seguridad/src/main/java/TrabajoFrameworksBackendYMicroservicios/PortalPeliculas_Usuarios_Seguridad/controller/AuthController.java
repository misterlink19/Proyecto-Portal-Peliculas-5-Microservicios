package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.controller;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.config.JwtUtil;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.Authority;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.User;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service.IAuthorityService;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthorityService authorityService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> credentials) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.get("email"),
                            credentials.get("password")
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = userService.findUserByEmail(credentials.get("email"));
            String token = jwtUtil.generateToken(
                    authentication.getName(),
                    user.getEmail(),
                    authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .findFirst()
                            .orElse("ROLE_USER")
            );
            // Construir respuesta JSON con el token y detalles del usuario
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", Map.of(
                    "username", user.getUsername(),
                    "email", user.getEmail(),
                    "enabled", user.getEnabled(),
                    "authority", Map.of(
                            "authority", user.getAuthority().getAuthority() // 🔹 Extraer solo el nombre del rol
                    )
            ));

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales incorrectas"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) { // Capturamos directamente RuntimeException
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @GetMapping()
    public List<Authority> getAllAuthorities() {
        return authorityService.findAllAuthorities();
    }

    @GetMapping("/{id}")
    public Authority getAuthorityById(@PathVariable Integer id) {
        return authorityService.findAuthorityById(id);
    }
}
