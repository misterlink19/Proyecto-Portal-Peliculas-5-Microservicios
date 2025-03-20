package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.dao;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPeliculaJPA extends JpaRepository<Pelicula, Integer> {

    List<Pelicula> findByTituloContainingIgnoreCase(String titulo);
    List<Pelicula> findByAnnio(int annio);
    List<Pelicula> findByGenero(String genero);
    List<Pelicula> findByDireccionContainingIgnoreCase(String direccion);
    List<Pelicula> findByDuracion(int duracion);
    List<Pelicula> findByPais(String pais);
    List<Pelicula> findByActoresNombreContainingIgnoreCase(String nombre);
}
