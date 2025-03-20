package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Actor;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Authority;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUrl = "http://localhost:9010/api/usuarios";
    private final String usuariosUrl = baseUrl + "/usuarios";

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        User[] users = restTemplate.getForObject(usuariosUrl, User[].class);
        return getPaginas(pageable, users);
    }

    @Override
    public User findUserById(Integer id) {
        User user = restTemplate.getForObject(usuariosUrl + "/" + id, User.class);
        return user;
    }

    @Override
    public Page<User> findUserByUsername(String username, Pageable pageable) {
        User[] users = restTemplate.getForObject(usuariosUrl + "/username/" + username, User[].class);
        return getPaginas(pageable, users);
    }

    @Override
    public Page<User> findUserByEmail(String email, Pageable pageable) {
        User[] users = restTemplate.getForObject(usuariosUrl + "/lista/email/" + email, User[].class);
        return getPaginas(pageable, users);
    }

    @Override
    public User findUserByEmailSinPage(String email) {
        User user = restTemplate.getForObject(usuariosUrl + "/email/" + email, User.class);
        return user;
    }


    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        User user = restTemplate.postForObject(
                baseUrl + "/auth/login",
                credentials,
                User.class
        );
        return user;
    }

    @Override
    public void saveUser(User user) {
        try {
            if (user.getId() != null && user.getId() > 0) {
                // Actualizar el usuario existente
                restTemplate.put(usuariosUrl, user);
            } else {
                // Crear nuevo usuario
                ResponseEntity<User> response = restTemplate.postForEntity(baseUrl + "/auth/register", user, User.class);
                if (response.getStatusCode() == HttpStatus.OK) {
                    User createdUser = response.getBody();
                    if (createdUser != null) {
                        user.setId(createdUser.getId());
                    } else {
                        throw new RuntimeException("Error al crear usuario: El cuerpo de la respuesta es nulo");
                    }
                } else {
                    throw new RuntimeException("Error al crear usuario: " + response.getStatusCode());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar usuario: " + e.getMessage());
        }
    }


    @Override
    public void deleteUser(Integer id) {
        restTemplate.delete(usuariosUrl + "/" + id);
    }

    private Page<User> getPaginas(Pageable pageable, User[] users) {
        List<User> userList = Arrays.asList(users);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<User> list;

        if (userList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, userList.size());
            list = userList.subList(startItem, toIndex);
        }

        Page<User> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), userList.size());

        return page;
    }
}
