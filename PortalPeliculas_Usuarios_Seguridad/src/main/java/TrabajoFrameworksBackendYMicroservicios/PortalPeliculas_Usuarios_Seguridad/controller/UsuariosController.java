package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.controller;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.User;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    public List<User> findAll() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Integer id) {
        User user = userService.findUserById(id);
        if (user == null) {
            throw new RuntimeException("Usuario no encontrado."); // Excepción genérica
        }
        return user;
    }

    @GetMapping("/email/{email}")
    public User findByEmail(@PathVariable("email") String email) {
        return userService.findUserByEmail(email);
    }
    @GetMapping("/lista/email/{email}")
    public List<User> findByEmailLista(@PathVariable("email") String email) {
        return userService.findUserByEmailLista(email);
    }

    @GetMapping("/username/{username}")
    public List<User> findByUsername(@PathVariable("username") String username) {
        return userService.findUserByUsernameLista(username);
    }

    @PutMapping()
    public void update(@RequestBody User user) {
        User usuarioActual = userService.findUserById(user.getId());

        if (usuarioActual == null) {
            throw new RuntimeException("Usuario no encontrado.");
        }

        // Si la nueva contraseña está vacía, mantener la anterior
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(usuarioActual.getPassword());
        } else {
            // Encriptar solo si se cambia la contraseña
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
    }
}
