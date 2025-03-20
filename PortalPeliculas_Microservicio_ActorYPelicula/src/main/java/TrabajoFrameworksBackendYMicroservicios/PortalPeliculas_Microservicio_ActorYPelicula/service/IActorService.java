package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Actor;

import java.util.List;

public interface IActorService {

    List<Actor> buscaTodos();
    Actor buscaPorId(int id);
    List<Actor> buscarPorNombre(String nombre);
    List<Actor> buscarPorPais(String pais);
    List<Actor> buscarPorPeliculas(String tituloPelicula);
    void guardarActor(Actor actor);
    void borrarActor(int idActor);
    void actualizarActor(Actor actor);
}
