package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthorityServiceImpl implements IAuthorityService {

    @Autowired
   private RestTemplate restTemplate;

   // private final String url = "http://localhost:8004/api/auth";

    private final String baseUrl = "http://localhost:9010/api/usuarios";
    private final String authUrl = baseUrl + "/auth";

    @Override
    public List<Authority> findAllAuthorities() {
        Authority[] authorities = restTemplate.getForObject(authUrl, Authority[].class);
        assert authorities != null;
        return Arrays.asList(authorities);
    }


    @Override
    public Authority findAuthorityById(Integer id) {
        return restTemplate.getForObject(authUrl + "/" + id, Authority.class);
    }

    @Override
    public void saveAuthority(Authority authority) {
        if (authority.getId() != null && authority.getId() > 0) {
            // Actualizar una autoridad existente
            restTemplate.put(authUrl + "/" + authority.getId(), authority);
        } else {
            // Crear una nueva autoridad
            restTemplate.postForObject(authUrl, authority, Authority.class);
        }
    }

    @Override
    public void deleteAuthority(Integer id) {
        restTemplate.delete(authUrl + "/" + id);
    }

}
