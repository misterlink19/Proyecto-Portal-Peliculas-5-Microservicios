package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.dao;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.User;
import java.util.List;

public interface IUserDAO {
    List<User> findAllUsers();
    User findUserById(Integer id);
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    void saveUser(User user);
    void deleteUser(Integer id);
    void updateUser(User user);

    List<User> findUserByUsernameLista(String username);

    List<User> findUserByEmailLista(String email);
}
