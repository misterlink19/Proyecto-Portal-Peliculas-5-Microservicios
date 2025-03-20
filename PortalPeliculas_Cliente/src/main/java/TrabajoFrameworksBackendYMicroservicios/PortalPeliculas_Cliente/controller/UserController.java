package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.controller;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.*;import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.paginator.PageRender;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service.IAuthorityService;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/cusuarios")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthorityService authorityService;

    @GetMapping("/buscar")
    public String buscarUsuarios() {
        return "usuario/searchUsuario";
    }

    @GetMapping("/listado")
    public String listadoUsuarios(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<User> listado = userService.findAllUsers(pageable);
        PageRender<User> pageRender = new PageRender<>("/cusuarios/listado", listado);
        model.addAttribute("titulo", "Listado de todos los usuarios");
        model.addAttribute("listadoUsuarios", listado);
        model.addAttribute("page", pageRender);
        return "usuario/listUsuario";
    }

    @GetMapping("/detalle/{id}")
    public String detalleUsuario(@PathVariable Integer id, Model model) {
        User user = userService.findUserById(id);
        if (user == null) {
            return "redirect:/error";
        }
        model.addAttribute("usuario", user);
        return "usuario/detalleUsuario";
    }

    @GetMapping("/nuevo")
    public String nuevoUsuario(Model model) {
        User nuevoUsuario = new User();
        nuevoUsuario.setEnabled(true);
        List<Authority> allAuthorities = authorityService.findAllAuthorities();
        model.addAttribute("usuario", nuevoUsuario);
        model.addAttribute("allAuthorities", allAuthorities);
        model.addAttribute("titulo", "Crear Nuevo Usuario");
        return "usuario/formUsuario";
    }

    @PostMapping("/guardar")
    public String guardarUsuarioAdmin(Model model, @ModelAttribute("usuario") User user,
                                      @RequestParam(value = "authority", required = false) Integer authorityId,
                                      BindingResult result, RedirectAttributes redirectAttrs) {
        if (RevisaUser(model, user, result)) return "usuario/formUsuario";

        // Asignar autoridad
        if (authorityId != null) {
            Authority authority = authorityService.findAuthorityById(authorityId);
            user.setAuthority(authority);
        }

        userService.saveUser(user);
        redirectAttrs.addFlashAttribute("mensaje", "Usuario guardado exitosamente");
        return "redirect:/cusuarios/listado";
    }


    @PostMapping("/registrar")
    public String registrarUsuario(Model model, @ModelAttribute("usuario") User user,
                                   BindingResult result, RedirectAttributes redirectAttrs) {
        if (RevisaUser(model, user, result)) return "usuario/formUsuario";

        Authority userAuthority = authorityService.findAuthorityById(2);
        user.setAuthority(userAuthority);
        user.setEnabled(true);

        try {
            userService.saveUser(user);
            redirectAttrs.addFlashAttribute("mensaje", "Usuario registrado exitosamente.");
            return "redirect:/home";
        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage()); // Enviar el error a la vista
            return "redirect:/cusuarios/nuevo"; // Redirigir de nuevo al formulario
        }
    }


    private boolean RevisaUser(Model model, @ModelAttribute("usuario") User user, BindingResult result) {
        if (result.hasErrors()) {
            user.setEnabled(true);  // Asegurar que tiene un valor por defecto
            List<Authority> allAuthorities = authorityService.findAllAuthorities();
            model.addAttribute("usuario", user);
            model.addAttribute("allAuthorities", allAuthorities);
            model.addAttribute("titulo", "Formulario de Usuario");
            return true;
        }
        return false;
    }


    @GetMapping("/borrar/{id}")
    public String borrarUsuario(@PathVariable Integer id, RedirectAttributes redirectAttrs, @RequestParam("page") Optional<Integer> page) {
        User user = userService.findUserById(id);
        if (user != null) {
            userService.deleteUser(id);
            redirectAttrs.addFlashAttribute("mensaje", "Usuario eliminado exitosamente");
        } else {
            redirectAttrs.addFlashAttribute("mensaje", "Usuario no encontrado!");
        }
        return "redirect:/usuarios/listado";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(Model model, @PathVariable("id") Integer id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return "redirect:/usuarios/listado";
        }

        List<Authority> allAuthorities = authorityService.findAllAuthorities();
        model.addAttribute("usuario", user);
        model.addAttribute("allAuthorities", allAuthorities);
        model.addAttribute("titulo", "Editar Usuario");
        return "usuario/formUsuario";
    }

    @GetMapping("/buscar/username")
    public String buscarPorUsername(Model model,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam("username") String username) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<User> listado = userService.findUserByUsername(username, pageable);

        if (listado.isEmpty()) {
            return "redirect:/error";
        }

        PageRender<User> pageRender = new PageRender<>("/cusuarios/buscar/username?username=" + username, listado);
        model.addAttribute("titulo", "Buscando usuario con nombre: " + username);
        model.addAttribute("listadoUsuarios", listado);
        model.addAttribute("page", pageRender);
        return "usuario/listUsuario";
    }

    @GetMapping("/buscar/email")
    public String buscarPorEmail(Model model,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam("email") String email) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<User> listado = userService.findUserByEmail(email, pageable);

        if (listado.isEmpty()) {
            return "redirect:/error";
        }

        PageRender<User> pageRender = new PageRender<>("/cusuarios/buscar/email?email=" + email, listado);
        model.addAttribute("titulo", "Buscando usuario con email: " + email);
        model.addAttribute("listadoUsuarios", listado);
        model.addAttribute("page", pageRender);
        return "usuario/listUsuario";
    }

}
