package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.controller;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Pelicula;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.service.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PeliculaController {

    @Autowired
    IPeliculaService peliculaService;

    @GetMapping("peliculas")
    public List<Pelicula> buscarTodas(){ return peliculaService.buscaTodas();}

    @GetMapping("/peliculas/{id}")
    public Pelicula buscaPorId(@PathVariable int id) {
        return peliculaService.buscaPorId(id);
    }

    @GetMapping("/peliculas/titulo/{titulo}")
    public List<Pelicula> buscaPorTitulo(@PathVariable String titulo) {
        return peliculaService.buscaPorTitulo(titulo);
    }

    @GetMapping("/peliculas/genero/{genero}")
    public List<Pelicula> buscaPorGenero(@PathVariable String genero) {
        return peliculaService.buscaPorGenero(genero);
    }

    @GetMapping("/peliculas/annio/{annio}")
    public List<Pelicula> getPeliculasByAnnio(@PathVariable int annio) {
        return peliculaService.buscarPorAnnio(annio);
    }

    @GetMapping("/peliculas/direccion/{direccion}")
    public List<Pelicula> getPeliculasByDireccion(@PathVariable String direccion) {return peliculaService.buscarPorDireccion(direccion);}

    @GetMapping("/peliculas/pais/{pais}")
    public List<Pelicula> buscarPorPais(@PathVariable String pais) {
        return peliculaService.buscarPorPais(pais);
    }

    @GetMapping("peliculas/duracion/{duracion}")
    public List<Pelicula> buscarPorDuracion(@PathVariable String duracion) {return peliculaService.buscarPorDuracion(Integer.parseInt(duracion));}

    @GetMapping("/peliculas/actores/{nombre}")
    public List<Pelicula> buscarPorActores(@PathVariable String nombre) { return peliculaService.buscarPorActores(nombre);}

    @PostMapping("/peliculas")
    public void guardarPelicula(@RequestBody Pelicula pelicula) {
        peliculaService.guardarPelicula(pelicula);
    }

    @PutMapping("/peliculas")
    public void actualizarPelicula(@RequestBody Pelicula pelicula) {
        peliculaService.actualizarPelicula(pelicula);
    }

    @DeleteMapping("/peliculas/{id}")
    public void borrarPelicula(@PathVariable("id") Integer id) {
        peliculaService.borrarPelicula(id);
    }
}
