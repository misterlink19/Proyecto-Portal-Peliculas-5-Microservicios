package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public String home(Model model, HttpSession session) {
        // Recuperar información de la sesión
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        // Valores predeterminados si no hay sesión activa
        if (username == null) {
            username = "Invitado";
            role = "PUBLIC"; // Rol ficticio para usuarios no autenticados
        }

        model.addAttribute("username", username);
        model.addAttribute("role", role);
        model.addAttribute("titulo", "Bienvenido al Portal de Películas");
        return "home";
    }
}