package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;

import java.util.Map;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.UserResponse;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    private final String AUTH_URL = "http://localhost:9010/api/usuarios/auth/login";

    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes redirectAttrs) {

        try {
            // Credenciales para la solicitud de autenticación
            Map<String, String> credentials = Map.of("email", email, "password", password);
            ResponseEntity<UserResponse> authResponse = restTemplate.postForEntity(AUTH_URL, credentials, UserResponse.class);

            // Validar la respuesta
            if (authResponse.getStatusCode() == HttpStatus.OK && authResponse.getBody() != null) {
                UserResponse userResponse = authResponse.getBody();


                // Comprobar si el usuario está activo
                if (userResponse.getUser().getEnabled() == null || !(userResponse.getUser().getEnabled())) {
                    redirectAttrs.addFlashAttribute("error", "Su cuenta está inactiva. Un administrador debe activarla.");
                    return "redirect:/login"; // Regresar a la página de login con el mensaje de error
                }
                // Guardar datos en la sesión
                session.setAttribute("token", userResponse.getToken());
                session.setAttribute("role", userResponse.getUser().getAuthority().getAuthority());
                session.setAttribute("username", userResponse.getUser().getUsername());
                session.setAttribute("email", userResponse.getUser().getEmail());
                session.setAttribute("userId", userResponse.getUser().getId());
                System.out.println("Usuario autenticado - ID: " + session.getAttribute("userId"));
                // Redirigir al home sin errores
                return "redirect:/home";
            } else {
                // Mensaje de error si las credenciales son incorrectas
                redirectAttrs.addFlashAttribute("error", "Credenciales incorrectas.");
                return "redirect:login"; // Regresar a la página de login
            }
        } catch (Exception e) {
            // Manejar errores generales
            redirectAttrs.addFlashAttribute("error", "Contraseña o correo incorrectas.");
            return "redirect:login"; // Regresar a la página de login
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

