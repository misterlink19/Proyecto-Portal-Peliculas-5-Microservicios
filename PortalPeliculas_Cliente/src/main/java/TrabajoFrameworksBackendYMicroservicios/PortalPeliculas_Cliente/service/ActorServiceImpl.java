package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Actor;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;

@Service
public class ActorServiceImpl implements IActorService {

    @Autowired
    RestTemplate restTemplate;

    //private final String url = "http://localhost:8001/peliculas/actores";
    private String url = "http://localhost:9010/api/peliculas/actores";

    @Autowired
    private RestTemplate template;


    public List<Pais> getTodosLosPaises() {
        return Arrays.asList(Pais.values());
    }

    @Override
    public Page<Actor> buscaTodos(Pageable pageable) {
        Actor[] actores = restTemplate.getForObject(url, Actor[].class, pageable);
        return getPaginas(pageable, actores);
    }

    @Override
    public Actor buscaPorId(int id) {
        Actor actor = restTemplate.getForObject(url + "/" + id, Actor.class);
        return actor;
    }

    @Override
    public Page<Actor> buscarPorNombre(String nombre, Pageable pageable) {
        Actor[] actores = restTemplate.getForObject(url + "/nombre/" + nombre, Actor[].class);
        return getPaginas(pageable, actores);
    }

    @Override
    public Page<Actor> buscarPorPais(String pais, Pageable pageable) {
        Actor[] actores = restTemplate.getForObject(url + "/pais/" + pais, Actor[].class);
        return getPaginas(pageable, actores);
    }

    @Override
    public Page<Actor> buscarPorPeliculas(String tituloPelicula, Pageable pageable) {
        Actor[] actores = restTemplate.getForObject(url + "/peliculas/" + tituloPelicula, Actor[].class);
        return getPaginas(pageable, actores);
    }

    private Page<Actor> getPaginas(Pageable pageable, Actor[] actores) {
        List<Actor> actorList = Arrays.asList(actores);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Actor> list;

        if (actorList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, actorList.size());
            list = actorList.subList(startItem, toIndex);
        }

        Page<Actor> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), actorList.size());

        return page;
    }

    @Override
    public void guardarActor(Actor actor) {
        if (actor.getId() != null && actor.getId() > 0) {
            // Actualizar el actor existente
            template.put(url, actor);
        } else {
            // Crear un nuevo actor
            template.postForObject(url, actor, Integer.class);
        }
    }


    @Override
    public void borrarActor(int idActor) {
        template.delete(url + "/" + idActor);
    }

    @Override
    public List<Actor> traeTodos() {
        Actor[] actores = restTemplate.getForObject(url, Actor[].class);
        assert actores != null;
        return Arrays.asList(actores);
    }

}
