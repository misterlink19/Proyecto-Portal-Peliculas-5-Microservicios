package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Actor;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Pais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IActorService {

    Page<Actor> buscaTodos(Pageable pageable);
    Actor buscaPorId(int id);
    Page<Actor> buscarPorNombre(String nombre, Pageable pageable);
    Page<Actor> buscarPorPais(String pais, Pageable pageable);
    Page<Actor> buscarPorPeliculas(String tituloPelicula, Pageable pageable);
    void guardarActor(Actor actor);
    void borrarActor(int idActor);

    List<Actor> traeTodos();

    List<Pais> getTodosLosPaises();
}
