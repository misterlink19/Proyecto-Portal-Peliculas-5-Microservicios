package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.dao;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface IActorJPA extends JpaRepository<Actor, Integer> {
    List<Actor> findByNombreContainingIgnoreCase(String nombre);
    List<Actor> findByPais(String pais);
    List<Actor> findByPeliculasTituloContainingIgnoreCase(String titulo);
}
