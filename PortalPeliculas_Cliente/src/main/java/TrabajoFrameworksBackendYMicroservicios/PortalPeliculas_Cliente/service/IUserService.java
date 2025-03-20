package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    Page<User> findAllUsers(Pageable pageable);

    User findUserById(Integer id);

    Page<User> findUserByUsername(String username, Pageable pageable);

    Page<User> findUserByEmail(String email, Pageable pageable);

    User findUserByEmailSinPage(String email);

    User findUserByEmailAndPassword(String email, String password);

    void saveUser(User user);

    void deleteUser(Integer id);



}
