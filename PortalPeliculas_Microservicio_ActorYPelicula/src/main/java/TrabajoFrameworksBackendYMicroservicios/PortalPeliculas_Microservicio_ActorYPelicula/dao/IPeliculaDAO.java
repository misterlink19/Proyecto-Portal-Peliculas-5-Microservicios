package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.dao;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Pelicula;

import java.util.List;

public interface IPeliculaDAO {
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
