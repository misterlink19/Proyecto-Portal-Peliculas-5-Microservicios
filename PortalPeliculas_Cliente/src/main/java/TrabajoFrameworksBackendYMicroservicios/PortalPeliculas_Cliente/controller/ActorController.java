package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.controller;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Actor;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Pais;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Pelicula;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.paginator.PageRender;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service.IActorService;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

@Controller
@RequestMapping("/cactor")
public class ActorController {

    @Autowired
    IActorService actorService;

    @Autowired
    IPeliculaService peliculaService;

    @GetMapping("/buscar")
    public String mostrarFormularioBusqueda(Model model) {
        model.addAttribute("paises", Pais.values());
        return "actor/searchActor";
    }

    @GetMapping("/listado")
    public String listadoActores(Model model, @RequestParam(name="page", defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Actor> listado = actorService.buscaTodos(pageable);
        PageRender<Actor> pageRender = new PageRender<>("/cactor/listado", listado);
        model.addAttribute("titulo", "Listado de todos los actores");
        model.addAttribute("listadoActores", listado);
        model.addAttribute("page", pageRender);
        return "actor/listActor";
    }

    @GetMapping("/detalle/{id}")
    public String detalleActor(@PathVariable("id") Integer id, Model model, @ModelAttribute("mensaje") String mensaje) {
        Actor actor = actorService.buscaPorId(id);
        if (actor == null) {
            return "redirect:/error";
        }
        model.addAttribute("actor", actor);
        model.addAttribute("titulo", "Detalles del Actor");
        model.addAttribute("mensaje", mensaje);
        return "actor/detalleActor";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Actor actor = new Actor();
        model.addAttribute("actor", actor);
        model.addAttribute("paises", Pais.values());
        model.addAttribute("titulo", "Crear Nuevo Actor");
        return "actor/formActor";
    }

    @PostMapping("/guardar")
    public String guardarActor(@ModelAttribute("actor") Actor actor, RedirectAttributes redirectAttrs) {
        Set<Pelicula> peliculas = actor.getPeliculasIds().stream()
                .map(peliculaService::buscaPorId)
                .collect(Collectors.toSet());
        actor.setPeliculas(peliculas); // Asignar las películas al actor
        actorService.guardarActor(actor); // Guardar el actor con las películas asignadas
        redirectAttrs.addFlashAttribute("mensaje", "Actor guardado exitosamente");
        return "redirect:/cactor/listado";
    }

    @GetMapping("/editar/{id}")
    public String editarActor(Model model, @PathVariable("id") Integer id) {
        Actor actor = actorService.buscaPorId(id);
        List<Pelicula> todasPeliculas = peliculaService.traeTodas();
        Set<Integer> peliculasIds = actor.getPeliculasIds();
        model.addAttribute("titulo", "Editar Actor");
        model.addAttribute("actor", actor);
        model.addAttribute("todasPeliculas", todasPeliculas);
        model.addAttribute("peliculasIds", peliculasIds);
        return "actor/formActor";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarActor(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes, @RequestParam("page") Optional<Integer> page) {
        Actor actor = actorService.buscaPorId(id);
        if (actor != null) {
            actorService.borrarActor(id);
            attributes.addFlashAttribute("mensaje", "El actor fue borrado!");
        } else {
            attributes.addFlashAttribute("mensaje", "Actor no encontrado!");
        }
        int currentPage = page.orElse(1);
        return "redirect:/cactor/listado?page=" + currentPage;
    }

    @GetMapping("/buscar/nombre")
    public String buscarPorNombre(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("nombre") String nombre) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Actor> listado = actorService.buscarPorNombre(nombre, pageable);

        if (listado.isEmpty()) {
            return "redirect:/error";
        }

        PageRender<Actor> pageRender = new PageRender<>("/cactor/buscar/nombre?nombre=" + nombre, listado);
        model.addAttribute("titulo", "Buscando: " + nombre.toUpperCase());
        model.addAttribute("listadoActores", listado);
        model.addAttribute("page", pageRender);
        return "actor/listActor";
    }

    @GetMapping("/buscar/pais")
    public String buscarPorPais(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("pais") String pais) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Actor> listado = actorService.buscarPorPais(pais, pageable);

        if (listado.isEmpty()) {
            return "redirect:/error";
        }

        PageRender<Actor> pageRender = new PageRender<>("/cactor/buscar/pais?pais=" + pais, listado);
        model.addAttribute("titulo", "Actores del País: "+ pais.toUpperCase());
        model.addAttribute("listadoActores", listado);
        model.addAttribute("page", pageRender);
        return "actor/listActor";
    }

    @GetMapping("/buscar/pelicula")
    public String buscarPorPelicula(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("titulo") String titulo) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Actor> listado = actorService.buscarPorPeliculas(titulo, pageable);

        if (listado.isEmpty()) {
            return "redirect:/error";
        }

        PageRender<Actor> pageRender = new PageRender<>("/cactor/buscar/pelicula?titulo=" + titulo, listado);
        model.addAttribute("titulo", "Actores de la pelicula: " + titulo.toUpperCase());
        model.addAttribute("listadoActores", listado);
        model.addAttribute("page", pageRender);
        return "actor/listActor";
    }
}
