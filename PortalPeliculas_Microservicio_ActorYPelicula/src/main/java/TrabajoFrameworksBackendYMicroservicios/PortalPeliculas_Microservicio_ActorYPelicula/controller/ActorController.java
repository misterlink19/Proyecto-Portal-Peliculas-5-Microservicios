package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.controller;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Actor;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActorController {

    @Autowired
    IActorService actorService;

    @GetMapping("actores")
    public List<Actor> buscarTodos(){return actorService.buscaTodos();}

    @GetMapping("/actores/{id}")
    public Actor buscaPorId(@PathVariable int id) {
        return actorService.buscaPorId(id);
    }

    @GetMapping("/actores/nombre/{nombre}")
    public List<Actor> buscarPorNombre(@PathVariable String nombre) {
        return actorService.buscarPorNombre(nombre);
    }

    @GetMapping("/actores/pais/{pais}")
    public List<Actor> buscarPorPais(@PathVariable String pais) {
        return actorService.buscarPorPais(pais);
    }

    @GetMapping("/actores/peliculas/{titulo}")
    public List<Actor> buscarPorPelicula(@PathVariable String titulo) {return actorService.buscarPorPeliculas(titulo);}

    @PostMapping("/actores")
    public void guardarActor(@RequestBody Actor actor) {
        actorService.guardarActor(actor);
    }

    @PutMapping("/actores")
    public void actualizarActor(@RequestBody Actor actor) {
        actorService.actualizarActor(actor);
    }

    @DeleteMapping("/actores/{id}")
    public void borrarActor(@PathVariable("id") Integer id) {
        actorService.borrarActor(id);
    }

}
