package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.dao.IActorDAO;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.dao.IPeliculaDAO;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Actor;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ActorServiceImpl implements IActorService {

    @Autowired
    IActorDAO actorDAO;

    @Autowired
    private IPeliculaDAO peliculaDAO;

    @Override
    public List<Actor> buscaTodos() {return actorDAO.buscaTodos();}

    @Override
    public Actor buscaPorId(int id) { return actorDAO.buscaPorId(id);}

    @Override
    public List<Actor> buscarPorNombre(String nombre) { return actorDAO.buscarPorNombre(nombre);}

    @Override
    public List<Actor> buscarPorPais(String pais) { return actorDAO.buscarPorPais(pais);}

    @Override
    public List<Actor> buscarPorPeliculas(String tituloPelicula) { return actorDAO.buscarPorPeliculas(tituloPelicula); }

    @Override
    public void guardarActor(Actor actor) {actorDAO.guardarActor(actor);}

    @Override
    public void borrarActor(int idActor) {
        Actor actorExistente = actorDAO.buscaPorId(idActor);
        if (actorExistente != null) {
            // Limpiar la relación de películas
            for (Pelicula pelicula : actorExistente.getPeliculas()) {
                pelicula.getActores().remove(actorExistente); // Eliminar el actor de la película
            }
            actorExistente.getPeliculas().clear(); // Limpiar la lista de películas del actor
            actorDAO.borrarActor(idActor);
        }
    }


    @Override
    public void actualizarActor(Actor actor) {
        Actor actorExistente = actorDAO.buscaPorId(actor.getId());
        if (actorExistente != null) {
            actorExistente.setNombre(actor.getNombre());
            actorExistente.setFechaNacimiento(actor.getFechaNacimiento());
            actorExistente.setPais(actor.getPais());
            // Limpiar la relación de películas actual
            actorExistente.getPeliculas().clear();
            // Agregar la nueva relación de películas
            for (Pelicula pelicula : actor.getPeliculas()) {
                Pelicula peliculaExistente = peliculaDAO.buscaPorId(pelicula.getId());
                if (peliculaExistente != null) {
                    actorExistente.getPeliculas().add(peliculaExistente);
                    peliculaExistente.getActores().add(actorExistente); // Añadir el actor a la película
                }
            }
            actorDAO.actualizarActor(actorExistente);
        }
    }

}
