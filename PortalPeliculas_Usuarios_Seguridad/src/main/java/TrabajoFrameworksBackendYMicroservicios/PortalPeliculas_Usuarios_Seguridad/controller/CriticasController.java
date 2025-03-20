package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.controller;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.Criticas;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.model.User;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service.ICriticasService;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Usuarios_Seguridad.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/criticas")
public class CriticasController {

    @Autowired
    private ICriticasService criticasService;

    @GetMapping
    public List<Criticas> findAll() {
        return criticasService.findAllCriticas();
    }

    @GetMapping("/{id}")
    public Criticas findById(@PathVariable("id") Integer id) {
        return criticasService.findCriticaById(id)
                .orElseThrow(() -> new RuntimeException("Crítica no encontrada."));
    }


    @GetMapping("/usuario/id/{userId}")
    public List<Criticas> findByUserId(@PathVariable("userId") Integer userId) {
        return criticasService.findCriticasByUserId(userId);
    }

    @GetMapping("/pelicula/{peliculaId}")
    public List<Criticas> findByPeliculaId(@PathVariable("peliculaId") Integer peliculaId) {
        return criticasService.findCriticasByPeliculaId(peliculaId);
    }

    @GetMapping("/usuario/{userId}/pelicula/{peliculaId}")
    public Optional<Criticas> findByUserAndPelicula(@PathVariable("userId") Integer userId,
                                                    @PathVariable("peliculaId") Integer peliculaId) {
        return criticasService.findCriticaByUserAndPelicula(userId, peliculaId);
    }

    @GetMapping("/usuario/username/{username}")
    public List<Criticas> findByUsuario(@PathVariable("username") String username) {
        return criticasService.findCriticasByUsuario(username);
    }

    @PostMapping
    public void create(@RequestBody Criticas critica) {
        criticasService.saveCritica(critica);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody Criticas critica) {
        criticasService.findCriticaById(id)
                .orElseThrow(() -> new RuntimeException("Crítica no encontrada."));
        critica.setId(id);
        criticasService.updateCritica(critica);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        criticasService.deleteCritica(id);
    }
}
