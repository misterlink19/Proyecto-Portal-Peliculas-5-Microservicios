package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.User;
import java.util.List;

public interface IUserService {
    List<User> findAllUsers();

    User findUserById(Integer id);

    User findUserByUsername(String username);

    User findUserByEmail(String email);


    void saveUser(User user);

    void deleteUser(Integer id);

    void updateUser(User user);

   List<User> findUserByEmailLista(String email);

    List<User> findUserByUsernameLista(String username);
}
