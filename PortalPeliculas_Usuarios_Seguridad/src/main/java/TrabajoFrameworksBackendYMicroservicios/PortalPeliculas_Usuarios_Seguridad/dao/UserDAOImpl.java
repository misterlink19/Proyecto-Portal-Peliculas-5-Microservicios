package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements IUserDAO {

    @Autowired
    private IUserJPA userJPA;


    @Override
    public List<User> findAllUsers() {
        return userJPA.findAll();
    }

    @Override
    public User findUserById(Integer id) {
        Optional<User> user = userJPA.findById(id);
        return user.orElse(null);
    }

    @Override
    public User findUserByUsername(String username) {
        return userJPA.findByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userJPA.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        userJPA.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userJPA.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userJPA.save(user);
    }

    @Override
    public List<User> findUserByUsernameLista(String username) {
        return userJPA.findByUsernameContainingIgnoreCase(username);
    }

    @Override
    public List<User> findUserByEmailLista(String email) {
        return userJPA.findByEmailContainingIgnoreCase(email);
    }
}
