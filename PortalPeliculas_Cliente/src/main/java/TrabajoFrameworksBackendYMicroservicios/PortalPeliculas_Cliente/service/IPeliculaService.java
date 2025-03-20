package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Pais;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPeliculaService {

    Page<Pelicula> buscaTodas(Pageable pageable);
    Pelicula buscaPorId(int id);
    Page<Pelicula> buscaPorTitulo(String titulo, Pageable pageable);
    Page<Pelicula> buscaPorGenero(String genero, Pageable pageable);
    Page<Pelicula> buscarPorAnnio(int annio, Pageable pageable);
    Page<Pelicula> buscarPorDireccion(String direccion, Pageable pageable);
    Page<Pelicula> buscarPorPais(String pais,Pageable pageable);
    Page<Pelicula> buscarPorDuracion(int duracion, Pageable pageable);
    Page<Pelicula> buscarPorActores(String nombre, Pageable pageable);
    void guardarPelicula(Pelicula pelicula);
    void borrarPelicula(int idPelicula);

    List<Pais> getTodosLosPaises();

    List<Pelicula> traeTodas();
}
