package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Criticas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CriticasServiceImpl implements ICriticasService {

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUrl = "http://localhost:9010/api/usuarios";
    private final String criticasUrl = baseUrl + "/criticas";
    private static final Logger logger = LoggerFactory.getLogger(CriticasServiceImpl.class);
    @Override
    public Page<Criticas> findAllCriticas(Pageable pageable) {
        Criticas[] criticasArray = restTemplate.getForObject(criticasUrl, Criticas[].class);
        return getPagina(pageable, criticasArray);
    }

    @Override
    public Optional<Criticas> findCriticaById(Integer id) {
        Criticas critica = restTemplate.getForObject(criticasUrl + "/" + id, Criticas.class);
        return Optional.ofNullable(critica);
    }

    @Override
    public Page<Criticas> findCriticasByUserId(Integer userId, Pageable pageable) {
        Criticas[] criticasArray = restTemplate.getForObject(criticasUrl + "/usuario/id/" + userId, Criticas[].class);
        return getPagina(pageable, criticasArray);
    }

    @Override
    public Page<Criticas> findCriticasByPeliculaId(Integer peliculaId, Pageable pageable) {
        Criticas[] criticasArray = restTemplate.getForObject(criticasUrl + "/pelicula/" + peliculaId, Criticas[].class);
        return getPagina(pageable, criticasArray);
    }

    @Override
    public List<Criticas> findAllCriticasByPeliculaId(Integer peliculaId) {
        Criticas[] criticasArray = restTemplate.getForObject(criticasUrl + "/pelicula/" + peliculaId, Criticas[].class);
        return Arrays.asList(criticasArray);
    }

    @Override
    public Page<Criticas> findCriticasByUsuario(String username, Pageable pageable) {
        logger.info("Iniciando búsqueda de críticas por usuario en el servicio: {}", username);
        Criticas[] criticasArray = restTemplate.getForObject(criticasUrl + "/usuario/username/" + username, Criticas[].class);
        logger.info("Búsqueda de críticas por usuario completada en el servicio: {}", username);
        return getPagina(pageable, criticasArray);
    }

    @Override
    public void saveCritica(Criticas critica) {
        if (critica.getId() != null && critica.getId() > 0) {
            // Actualizar critica existente
            restTemplate.put(criticasUrl + "/" + critica.getId(), critica);
        } else {
            // Crear una nueva crítica
           restTemplate.postForObject(criticasUrl, critica, Integer.class);

        }
    }

    @Override
    public void deleteCritica(Integer id) {
        restTemplate.delete(criticasUrl + "/" + id);
    }

    private Page<Criticas> getPagina(Pageable pageable, Criticas[] criticas) {
        List<Criticas> criticasList = Arrays.asList(criticas);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Criticas> list;

        if (criticasList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, criticasList.size());
            list = criticasList.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), criticasList.size());
    }
}
