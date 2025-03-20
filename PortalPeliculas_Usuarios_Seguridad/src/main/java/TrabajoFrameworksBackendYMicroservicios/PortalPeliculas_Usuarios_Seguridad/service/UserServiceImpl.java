package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao.IUserDAO;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;


    private final  PasswordEncoder passwordEncoder;

    // Inyección por constructor
    public UserServiceImpl(IUserDAO userDAO,PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    @Override
    public User findUserById(Integer id) {
        return userDAO.findUserById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        if (userDAO.findUserByUsername(user.getUsername()) != null) {
            throw new RuntimeException("El nombre de usuario ya está en uso.");
        }

        if (userDAO.findUserByEmail(user.getEmail()) != null) {
            throw new RuntimeException("El email ya está en uso.");
        }

        if (user.getCreatedAt() == null) {
            user.setCreatedAt(Instant.now());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
    }



    @Override
    public void deleteUser(Integer id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        // Si created_at no se establece manualmente, asigna la fecha actual
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(Instant.now());
        }
        userDAO.updateUser(user);
    }

    @Override
    public List<User> findUserByEmailLista(String email) {
        return userDAO.findUserByEmailLista(email);
    }

    @Override
    public List<User> findUserByUsernameLista(String username) {
        return userDAO.findUserByUsernameLista(username);
    }
}
