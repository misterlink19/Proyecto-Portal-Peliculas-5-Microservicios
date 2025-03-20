package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Pelicula;

import java.util.List;

public interface IPeliculaService {

    List<Pelicula> buscaTodas();
    Pelicula buscaPorId(int id);
    List<Pelicula> buscaPorTitulo(String titulo);
    List<Pelicula> buscaPorGenero(String genero);
    List<Pelicula> buscarPorAnnio(int annio);
    List<Pelicula> buscarPorDireccion(String direccion);
    List<Pelicula> buscarPorPais(String pais);
    List<Pelicula> buscarPorDuracion(int duracion);
    List<Pelicula> buscarPorActores(String nombre);

    void guardarPelicula(Pelicula pelicula);
    void actualizarPelicula(Pelicula pelicula);
    void borrarPelicula(int idPelicula);

}
