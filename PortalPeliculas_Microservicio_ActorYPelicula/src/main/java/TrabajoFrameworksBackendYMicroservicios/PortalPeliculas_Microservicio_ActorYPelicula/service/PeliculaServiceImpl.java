package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.dao.IPeliculaDAO;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaServiceImpl implements IPeliculaService {

    @Autowired
    IPeliculaDAO peliculaDAO;

    @Override
    public List<Pelicula> buscaTodas() { return peliculaDAO.buscaTodas(); }

    @Override
    public Pelicula buscaPorId(int id) { return peliculaDAO.buscaPorId(id);}

    @Override
    public List<Pelicula> buscaPorTitulo(String titulo) { return peliculaDAO.buscaPorTitulo(titulo);}

    @Override
    public List<Pelicula> buscaPorGenero(String genero) { return peliculaDAO.buscaPorGenero(genero);}

    @Override
    public List<Pelicula> buscarPorAnnio(int annio) { return peliculaDAO.buscarPorAnnio(annio);}

    @Override
    public List<Pelicula> buscarPorDireccion(String direccion) { return peliculaDAO.buscarPorDireccion(direccion);}

    @Override
    public List<Pelicula> buscarPorPais(String pais) {return peliculaDAO.buscarPorPais(pais);}

    @Override
    public List<Pelicula> buscarPorDuracion(int duracion) { return peliculaDAO.buscarPorDuracion(duracion); }

    @Override
    public List<Pelicula> buscarPorActores(String nombre) { return peliculaDAO.buscarPorActores(nombre); }

    @Override
    public void guardarPelicula(Pelicula pelicula) {
        peliculaDAO.guardarPelicula(pelicula);
    }

    @Override
    public void actualizarPelicula(Pelicula pelicula) { peliculaDAO.actualizarPelicula(pelicula); }

    @Override
    public void borrarPelicula(int idPelicula) { peliculaDAO.borrarPelicula(idPelicula); }
}
