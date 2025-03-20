package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.dao;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorDAOImpl implements IActorDAO {

    @Autowired
    IActorJPA actorJPA;

    @Override
    public List<Actor> buscaTodos() {
        return actorJPA.findAll();
    }

    @Override
    public Actor buscaPorId(int id) {
        Optional<Actor> actor = actorJPA.findById(id);
        return actor.orElse(null);
    }

    @Override
    public List<Actor> buscarPorNombre(String nombre) { return actorJPA.findByNombreContainingIgnoreCase(nombre);}

    @Override
    public List<Actor> buscarPorPais(String pais) { return actorJPA.findByPais(pais);}

    @Override
    public List<Actor> buscarPorPeliculas(String tituloPelicula) {
        return actorJPA.findByPeliculasTituloContainingIgnoreCase(tituloPelicula);
    }

    @Override
    public void guardarActor(Actor actor) {
        actorJPA.save(actor);
    }

    @Override
    public void borrarActor(int idActor) {
        actorJPA.deleteById(idActor);
    }

    @Override
    public void actualizarActor(Actor actor) {
        actorJPA.save(actor);
    }
}
