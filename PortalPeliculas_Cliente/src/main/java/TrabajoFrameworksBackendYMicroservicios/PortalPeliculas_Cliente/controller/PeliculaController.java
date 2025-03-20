package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.controller;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.*;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.paginator.PageRender;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cpeliculas")
public class PeliculaController {

    @Autowired
    IPeliculaService peliculaService;

    @Autowired
    IActorService actorService;

    @Autowired
    ICriticasService criticasService;

    @Autowired
    IUserService userService;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping("/buscar")
    public String mostrarFormularioBusqueda(Model model) {
        List<Pais> todosPaises = peliculaService.getTodosLosPaises();
        model.addAttribute("paises", todosPaises);
        return "pelicula/searchPelicula";
    }


    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
        Resource recurso = null;
        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (recurso == null || !recurso.exists()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }

    @GetMapping("/detalle/{id}")
    public String detallePelicula(Model model, @PathVariable("id") Integer id) {
        Pelicula pelicula = peliculaService.buscaPorId(id);
        List<Criticas> criticas = new ArrayList<>();
        double notaMedia = 0.0;

        try {
            criticas = criticasService.findAllCriticasByPeliculaId(id);
            notaMedia = criticas.stream().mapToInt(Criticas::getNota).average().orElse(0.0);
        } catch (Exception e) {
            model.addAttribute("errorCriticas", "No se pudieron cargar las críticas en este momento.");
        }

        DecimalFormat df = new DecimalFormat("#.#");
        String formattedNotaMedia = df.format(notaMedia);

        model.addAttribute("pelicula", pelicula);
        model.addAttribute("criticas", criticas);
        model.addAttribute("notaMedia", formattedNotaMedia);
        model.addAttribute("titulo", "Detalles de la Película");

        return "pelicula/detallePelicula";
    }



    @GetMapping("/listado")
    public String listadoPeliculas(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado = peliculaService.buscaTodas(pageable);
        PageRender<Pelicula> pageRender = new PageRender<>("/cpeliculas/listado", listado);
        model.addAttribute("titulo", "Listado de todas las películas");
        model.addAttribute("listadoPeliculas", listado);
        model.addAttribute("page", pageRender);
        return "pelicula/listPelicula";
    }

    @GetMapping("/nuevo")
    public String nuevoPelicula(Model model) {
        Pelicula pelicula = new Pelicula();
        List<Actor> todosActores = actorService.traeTodos();
        List<Pais> todosPaises = peliculaService.getTodosLosPaises();
        pelicula.setActoresIds(new HashSet<>()); // Inicializar actoresIds
        model.addAttribute("titulo", "Nueva película");
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("reparto", todosActores);
        model.addAttribute("paises", todosPaises);
        return "pelicula/formPelicula";
    }




    @PostMapping("/guardar")
    public String guardarPelicula(Model model, @Valid @ModelAttribute("pelicula") Pelicula pelicula,
                                  BindingResult result, RedirectAttributes attributes,
                                  @RequestParam("imagenPortadaFile") MultipartFile imagenPortadaFile) {
        if (result.hasErrors()) {
            List<Actor> todosActores = actorService.traeTodos();
            List<Pais> todosPaises = peliculaService.getTodosLosPaises();
            model.addAttribute("titulo", "Nueva película");
            model.addAttribute("reparto", todosActores);
            model.addAttribute("paises", todosPaises);
            return "pelicula/formPelicula";
        }

        if (!imagenPortadaFile.isEmpty()) {
            if (pelicula.getId() != null && pelicula.getId() > 0 && pelicula.getImagenPortada() != null && !pelicula.getImagenPortada().isEmpty()) {
                uploadFileService.delete(pelicula.getImagenPortada());
            }
            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(imagenPortadaFile);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "Error al subir la imagen de portada");
                return "pelicula/formPelicula";
            }
            pelicula.setImagenPortada(uniqueFilename);
        } else if (pelicula.getId() != null && pelicula.getId() > 0) {
            Pelicula peliculaExistente = peliculaService.buscaPorId(pelicula.getId());
            pelicula.setImagenPortada(peliculaExistente.getImagenPortada());
        }

        // Asignar actores a la película
        if (pelicula.getActoresIds() != null) {
            Set<Actor> actores = pelicula.getActoresIds().stream()
                    .map(id -> actorService.buscaPorId(id))
                    .collect(Collectors.toSet());
            pelicula.setActores(actores);
        }

        // Guardar o actualizar la película
        peliculaService.guardarPelicula(pelicula);

        attributes.addFlashAttribute("mensaje", "Película guardada exitosamente");
        return "redirect:/cpeliculas/listado";
    }



    @GetMapping("/editar/{id}")
    public String editarPelicula(Model model, @PathVariable("id") Integer id) {
        Pelicula pelicula = peliculaService.buscaPorId(id);
        List<Actor> todosActores = actorService.traeTodos();
        List<Pais> todosPaises = peliculaService.getTodosLosPaises();
        if (pelicula.getActoresIds() == null) {
            Set<Integer> actoresIds = pelicula.getActores().stream().map(Actor::getId).collect(Collectors.toSet());
            pelicula.setActoresIds(actoresIds);
        }
        model.addAttribute("titulo", "Editar película");
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("reparto", todosActores);
        model.addAttribute("paises", todosPaises);
        return "pelicula/formPelicula";
    }



    @GetMapping("/borrar/{id}")
    public String eliminarPelicula(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes, @RequestParam("page") Optional<Integer> page) {
        Pelicula pelicula = peliculaService.buscaPorId(id);
        if (pelicula != null) {
            List<Criticas> criticas = criticasService.findAllCriticasByPeliculaId(id);
            peliculaService.borrarPelicula(id);

            for (Criticas critica : criticas) {
                criticasService.deleteCritica(critica.getId());
            }
            attributes.addFlashAttribute("mensaje", "Los datos de la película fueron borrados!");
        } else {
            attributes.addFlashAttribute("mensaje", "Película no encontrada!");
        }
        int currentPage = page.orElse(1);
        return "redirect:/cpeliculas/listado?page=" + currentPage;
    }


    @GetMapping("/buscar/titulo")
    public String buscarPorTitulo(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("titulo") String titulo) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado = peliculaService.buscaPorTitulo(titulo, pageable);

        if (listado.isEmpty()) {
            return "redirect:/error";
        }

        PageRender<Pelicula> pageRender = new PageRender<>("/cpeliculas/buscar/titulo?titulo=" + titulo, listado);
        model.addAttribute("titulo", "Buscando peliculas con el titulo de: " + titulo);
        model.addAttribute("listadoPeliculas", listado);
        model.addAttribute("page", pageRender);
        return "pelicula/listPelicula";
    }

    @GetMapping("/buscar/genero")
    public String buscarPorGenero(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("genero") String genero) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado = peliculaService.buscaPorGenero(genero, pageable);

        if (listado.isEmpty()) {
            return "redirect:/error";
        }

        PageRender<Pelicula> pageRender = new PageRender<>("/cpeliculas/buscar/genero?genero=" + genero, listado);
        model.addAttribute("titulo", "Buscando peliculas con el genero: " + genero);
        model.addAttribute("listadoPeliculas", listado);
        model.addAttribute("page", pageRender);
        return "pelicula/listPelicula";
    }

    @GetMapping("/buscar/annio")
    public String buscarPorAnnio(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("annio") Integer annio) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado = peliculaService.buscarPorAnnio(annio, pageable);

        if (listado.isEmpty()) {
            return "redirect:/error";
        }

        PageRender<Pelicula> pageRender = new PageRender<>("/cpeliculas/buscar/annio?annio=" + annio, listado);
        model.addAttribute("titulo", "Buscando peliculas del año" + annio);
        model.addAttribute("listadoPeliculas", listado);
        model.addAttribute("page", pageRender);
        return "pelicula/listPelicula";
    }

    @GetMapping("/buscar/direccion")
    public String buscarPorDireccion(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("direccion") String direccion) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado = peliculaService.buscarPorDireccion(direccion, pageable);

        if (listado.isEmpty()) {
            return "redirect:/error";
        }

        PageRender<Pelicula> pageRender = new PageRender<>("/cpeliculas/buscar/direccion?direccion=" + direccion, listado);
        model.addAttribute("titulo", "Buscando peliculas por Dirección de: "+direccion);
        model.addAttribute("listadoPeliculas", listado);
        model.addAttribute("page", pageRender);
        return "pelicula/listPelicula";
    }

    @GetMapping("/buscar/pais")
    public String buscarPorPais(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("pais") String pais) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado = peliculaService.buscarPorPais(pais, pageable);

        if (listado.isEmpty()) {
            return "redirect:/error";
        }

        PageRender<Pelicula> pageRender = new PageRender<>("/cpeliculas/buscar/pais?pais=" + pais, listado);
        model.addAttribute("titulo", "Buscando peliculas provenientes de: " + pais);
        model.addAttribute("listadoPeliculas", listado);
        model.addAttribute("page", pageRender);
        return "pelicula/listPelicula";
    }

    @GetMapping("/buscar/duracion")
    public String buscarPorDuracion(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("duracion") Integer duracion) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado = peliculaService.buscarPorDuracion(duracion, pageable);

        if (listado.isEmpty()) {
            return "redirect:/error";
        }

        PageRender<Pelicula> pageRender = new PageRender<>("/cpeliculas/buscar/duracion?duracion=" + duracion, listado);
        model.addAttribute("titulo", "Buscando peliculas con duracion de: " + duracion + " Minutos");
        model.addAttribute("listadoPeliculas", listado);
        model.addAttribute("page", pageRender);
        return "pelicula/listPelicula";
    }

    @GetMapping("/buscar/actores")
    public String buscarPorActores(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("nombre") String nombre) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado = peliculaService.buscarPorActores(nombre, pageable);

        if (listado.isEmpty()) {
            return "redirect:/error";
        }

        PageRender<Pelicula> pageRender = new PageRender<>("/cpeliculas/buscar/actores?nombre=" + nombre, listado);
        model.addAttribute("titulo", "Buscando a " + nombre + " en peliculas");
        model.addAttribute("listadoPeliculas", listado);
        model.addAttribute("page", pageRender);
        return "pelicula/listPelicula";
    }
}
