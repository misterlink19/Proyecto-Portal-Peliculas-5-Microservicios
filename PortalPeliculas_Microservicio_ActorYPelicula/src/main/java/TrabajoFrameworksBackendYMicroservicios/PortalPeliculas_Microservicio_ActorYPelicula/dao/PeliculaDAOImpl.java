package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.dao;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PeliculaDAOImpl implements IPeliculaDAO{

    @Autowired
    IPeliculaJPA peliculaJPA;

    @Override
    public List<Pelicula> buscaTodas() { return peliculaJPA.findAll();}

    @Override
    public Pelicula buscaPorId(int id) {
        Optional<Pelicula> pelicula = peliculaJPA.findById(id);
        return pelicula.orElse(null);
    }

    @Override
    public List<Pelicula> buscaPorTitulo(String titulo) { return peliculaJPA.findByTituloContainingIgnoreCase(titulo);}

    @Override
    public List<Pelicula> buscaPorGenero(String genero) {return peliculaJPA.findByGenero(genero);}

    @Override
    public List<Pelicula> buscarPorAnnio(int annio) { return peliculaJPA.findByAnnio(annio);}

    @Override
    public List<Pelicula> buscarPorDireccion(String direccion) {return peliculaJPA.findByDireccionContainingIgnoreCase(direccion);}

    @Override
    public List<Pelicula> buscarPorPais(String pais) {
        return peliculaJPA.findByPais(pais);
    }

    @Override
    public List<Pelicula> buscarPorDuracion(int duracion) {
        return peliculaJPA.findByDuracion(duracion);
    }

    @Override
    public List<Pelicula> buscarPorActores(String nombre) {
        return peliculaJPA.findByActoresNombreContainingIgnoreCase(nombre);
    }

    @Override
    public void guardarPelicula(Pelicula pelicula) { peliculaJPA.save(pelicula);}

    @Override
    public void actualizarPelicula(Pelicula pelicula) { peliculaJPA.save(pelicula);}

    @Override
    public void borrarPelicula(int idPelicula) { peliculaJPA.deleteById(idPelicula);}
}
