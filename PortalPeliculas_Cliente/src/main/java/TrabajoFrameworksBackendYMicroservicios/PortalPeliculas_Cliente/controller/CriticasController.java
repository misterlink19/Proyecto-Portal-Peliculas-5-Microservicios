package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.controller;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Criticas;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Pelicula;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.User;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.paginator.PageRender;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service.ICriticasService;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service.IPeliculaService;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/ccriticas")
public class CriticasController {

    @Autowired
    private ICriticasService criticasService;

    @Autowired
    private IPeliculaService peliculaService;

    @Autowired
    private IUserService userService;

    private static final Logger logger = LoggerFactory.getLogger(CriticasController.class);

    @GetMapping("/buscar")
    public String buscarCriticas() {
        return "criticas/searchCriticas";
    }

    @GetMapping("/listado")
    public String listarCriticas(@RequestParam(defaultValue="0") int page, Model model) {
        Page<Criticas> paginaCriticas = criticasService.findAllCriticas(PageRequest.of(page, 5));
        Map<Integer, String> peliculaTitulos = new HashMap<>();

        for (Criticas critica : paginaCriticas.getContent()) {
            peliculaTitulos.putIfAbsent(critica.getPeliculaId(),
                    Optional.ofNullable(peliculaService.buscaPorId(critica.getPeliculaId()))
                            .map(Pelicula::getTitulo)
                            .orElse("Desconocida"));
        }
        PageRender<Criticas> pageRender = new PageRender<>("/ccriticas/listado", paginaCriticas);

        model.addAttribute("paginaCriticas", paginaCriticas);
        model.addAttribute("peliculaTitulos", peliculaTitulos != null ? peliculaTitulos : new HashMap<>());
        model.addAttribute("titulo", "Listado de Críticas");
        model.addAttribute("page", pageRender);
        return "criticas/listCriticas";
    }




    @GetMapping("/{id}")
    public String findCriticaById(@PathVariable Integer id, Model model) {
        Optional<Criticas> criticaOpt = criticasService.findCriticaById(id);

        if (criticaOpt.isPresent()) {
            Criticas critica = criticaOpt.get();

            // Obtener información de la película
            Pelicula pelicula = peliculaService.buscaPorId(critica.getPeliculaId());
            String tituloPelicula = (pelicula != null) ? pelicula.getTitulo() : "Película Desconocida";

            model.addAttribute("critica", critica);
            model.addAttribute("tituloPelicula", tituloPelicula);
            return "criticas/detalleCritica";
        } else {
            model.addAttribute("error", "Crítica no encontrada");
            return "error";
        }
    }

    @GetMapping("/usuario/{userId}")
    public String findCriticasByUserId(@PathVariable Integer userId, @RequestParam(defaultValue = "0") int page, Model model) {
        Page<Criticas> paginaCriticas = criticasService.findCriticasByUserId(userId, PageRequest.of(page, 5));
        model.addAttribute("paginaCriticas", paginaCriticas);
        return "listCriticas";
    }

    @GetMapping("/buscar/pelicula")
    public String findCriticasByPelicula(Model model,
                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam("pelicula") String tituloPelicula) {
        logger.info("Buscando críticas de la película: {}", tituloPelicula);

        // Buscar la película por título
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> peliculasEncontradas = peliculaService.buscaPorTitulo(tituloPelicula, pageable);

        if (peliculasEncontradas.isEmpty()) {
            logger.warn("No se encontraron películas con el título: {}", tituloPelicula);
            return "redirect:/error";
        }


        // Obtener la película encontrada
        Pelicula pelicula = peliculasEncontradas.getContent().get(0);
        Integer peliculaId = pelicula.getId();

        // Buscar críticas por ID de película
        Page<Criticas> paginaCriticas = criticasService.findCriticasByPeliculaId(peliculaId, pageable);

        if (paginaCriticas.isEmpty()) {
            return "redirect:/error";
        }

        // Cargar títulos de las películas
        Map<Integer, String> peliculaTitulos = new HashMap<>();
        peliculaTitulos.put(peliculaId, pelicula.getTitulo());

        PageRender<Criticas> pageRender = new PageRender<>("/ccriticas/buscar/pelicula?pelicula=" + tituloPelicula, paginaCriticas);

        // Pasar los datos al modelo
        model.addAttribute("paginaCriticas", paginaCriticas);
        model.addAttribute("titulo", "Críticas de la Película: " + pelicula.getTitulo());
        model.addAttribute("peliculaTitulos", peliculaTitulos);
        model.addAttribute("page", pageRender);

        return "criticas/listCriticas";
    }


    @GetMapping("/buscar/usuario")
    public String findCriticasByUsuario(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam("usuario") String usuario) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Criticas> listado = criticasService.findCriticasByUsuario(usuario, pageable);

        if (listado.isEmpty()) {
            model.addAttribute("mensaje", "No hay críticas para este usuario.");
            return "criticas/listCriticas";
        }

        Map<Integer, String> peliculaTitulos = new HashMap<>();
        for (Criticas critica : listado.getContent()) {
            peliculaTitulos.putIfAbsent(critica.getPeliculaId(),
                    Optional.ofNullable(peliculaService.buscaPorId(critica.getPeliculaId()))
                            .map(Pelicula::getTitulo)
                            .orElse("Desconocida"));
        }

        PageRender<Criticas> pageRender = new PageRender<>("/ccriticas/buscar/usuario?usuario=" + usuario, listado);
        model.addAttribute("paginaCriticas", listado);
        model.addAttribute("peliculaTitulos", peliculaTitulos);
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Buscando: " + usuario.toUpperCase());
        model.addAttribute("page", pageRender);

        return "criticas/listCriticas";
    }

    @GetMapping("/pelicula/{peliculaId}/criticas")
    public String buscarCriticasPorPelicula(@PathVariable("peliculaId") Integer peliculaId, Model model, @RequestParam(defaultValue = "0") int page) {
        // Buscar la película por ID
        Pelicula pelicula = peliculaService.buscaPorId(peliculaId);
        if (pelicula == null) {
            return "redirect:/error"; // Redirigir si la película no existe
        }

        // Buscar críticas por ID de la película
        Pageable pageable = PageRequest.of(page, 5);
        Page<Criticas> paginaCriticas = criticasService.findCriticasByPeliculaId(peliculaId, pageable);

        PageRender<Criticas> pageRender = new PageRender<>("/ccriticas/pelicula/" + peliculaId + "/criticas", paginaCriticas);
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("paginaCriticas", paginaCriticas);
        model.addAttribute("tituloPelicula", pelicula.getTitulo());
        model.addAttribute("page", pageRender);
        model.addAttribute("titulo", "Críticas de la Película: " + pelicula.getTitulo());
        return "criticas/listCriticas";
    }


    @GetMapping("/nueva/{peliculaId}")
    public String nuevaCritica(@PathVariable Integer peliculaId,
                               @RequestParam(value = "email", required = false) String email,
                               Model model) {
        if (email == null || email.isEmpty()) {
            return "redirect:/login";
        }

        User user = userService.findUserByEmailSinPage(email);
        if (user == null) {
            return "redirect:/error";
        }

        Pelicula pelicula = peliculaService.buscaPorId(peliculaId);
        if (pelicula == null) {
            return "redirect:/error";
        }
        Criticas critica = new Criticas();
        critica.setPeliculaId(peliculaId);
        critica.setUser(user);
        critica.setFecha(Instant.now());

        model.addAttribute("critica", critica);
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("titulo", "Nueva Crítica");

        return "criticas/formCritica";
    }


    @PostMapping("/guardar")
    public String guardarCritica(@ModelAttribute("critica") Criticas critica, RedirectAttributes redirectAttrs) {
        criticasService.saveCritica(critica);
        redirectAttrs.addFlashAttribute("mensaje", "Crítica guardada exitosamente");
        return "redirect:/cpeliculas/detalle/" + critica.getPeliculaId();
    }

    @GetMapping("/borrar/{id}")
    public String borrarCritica(@PathVariable Integer id, RedirectAttributes redirectAttrs,
                                @RequestParam("page") Optional<Integer> page,
                                @RequestParam(value = "returnUrl", required = false) String returnUrl) {
        Optional<Criticas> criticaOptional = criticasService.findCriticaById(id);

        if (criticaOptional.isPresent()) {
            criticasService.deleteCritica(id);
            redirectAttrs.addAttribute("mensaje", "Crítica eliminada exitosamente.");
        } else {
            redirectAttrs.addAttribute("error", "Crítica no encontrada.");
        }

        if (returnUrl != null && !returnUrl.isEmpty()) {
            return "redirect:" + returnUrl;
        }
        return "redirect:/ccriticas/listado?page=" + page.orElse(1) ;
    }
}