package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.dao.IActorDAO;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Actor;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.service.ActorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


/**
 * Esto asegura  que las interacciones
 * con los DAOs se realizan de manera adecuada
 * y que los métodos de servicio devuelven
 * los resultados correctos.
 * */
public class ActorServiceImplTests {

    @Mock
    private IActorDAO actorDAO;

    @InjectMocks
    private ActorServiceImpl actorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscaTodos() {
        Actor actor = new Actor();
        actor.setId(1);
        List<Actor> actores = Arrays.asList(actor);

        when(actorDAO.buscaTodos()).thenReturn(actores);

        List<Actor> result = actorService.buscaTodos();

        assertEquals(1, result.size());
        verify(actorDAO, times(1)).buscaTodos();
        verifyNoMoreInteractions(actorDAO);
    }

    @Test
    public void testBuscaPorId() {
        Actor actor = new Actor();
        actor.setId(1);

        when(actorDAO.buscaPorId(1)).thenReturn(actor);

        Actor result = actorService.buscaPorId(1);

        assertEquals(1, result.getId());
        verify(actorDAO, times(1)).buscaPorId(1);
        verifyNoMoreInteractions(actorDAO);
    }

    @Test
    public void testBuscarPorNombre() {
        Actor actor = new Actor();
        actor.setId(1);
        actor.setNombre("Leonardo DiCaprio");

        when(actorDAO.buscarPorNombre("Leonardo")).thenReturn(Arrays.asList(actor));

        List<Actor> result = actorService.buscarPorNombre("Leonardo");

        assertEquals("Leonardo DiCaprio", result.get(0).getNombre());
        verify(actorDAO, times(1)).buscarPorNombre("Leonardo");
        verifyNoMoreInteractions(actorDAO);
    }

    @Test
    public void testBuscarPorPais() {
        Actor actor = new Actor();
        actor.setId(1);
        actor.setNombre("Leonardo DiCaprio");

        when(actorDAO.buscarPorPais("Estados Unidos")).thenReturn(Arrays.asList(actor));

        List<Actor> result = actorService.buscarPorPais("Estados Unidos");

        assertEquals("Leonardo DiCaprio", result.get(0).getNombre());
        verify(actorDAO, times(1)).buscarPorPais("Estados Unidos");
        verifyNoMoreInteractions(actorDAO);
    }

    @Test
    public void testBuscarPorPeliculas() {
        Actor actor = new Actor();
        actor.setId(1);
        actor.setNombre("Leonardo DiCaprio");

        when(actorDAO.buscarPorPeliculas("Inception")).thenReturn(Arrays.asList(actor));

        List<Actor> result = actorService.buscarPorPeliculas("Inception");

        assertEquals("Leonardo DiCaprio", result.get(0).getNombre());
        verify(actorDAO, times(1)).buscarPorPeliculas("Inception");
        verifyNoMoreInteractions(actorDAO);
    }


    @Test
    public void testGuardarActor() {
        Actor actor = new Actor();
        actor.setId(1);
        actor.setNombre("Leonardo DiCaprio");

        doNothing().when(actorDAO).guardarActor(any(Actor.class));

        actorService.guardarActor(actor);

        verify(actorDAO, times(1)).guardarActor(actor);
        verifyNoMoreInteractions(actorDAO);
    }


    @Test
    public void testActualizarActor() {
        Actor actor = new Actor();
        actor.setId(1);
        actor.setNombre("Leonardo DiCaprio");

        when(actorDAO.buscaPorId(anyInt())).thenReturn(actor);
        doNothing().when(actorDAO).actualizarActor(any(Actor.class));

        actorService.actualizarActor(actor);

        verify(actorDAO, times(1)).buscaPorId(1);
        verify(actorDAO, times(1)).actualizarActor(actor);
        verifyNoMoreInteractions(actorDAO);
    }

    @Test
    public void testBorrarActor() {
        Actor actor = new Actor();
        actor.setId(1);

        when(actorDAO.buscaPorId(anyInt())).thenReturn(actor);
        doNothing().when(actorDAO).borrarActor(anyInt());

        actorService.borrarActor(1);

        verify(actorDAO, times(1)).buscaPorId(1);
        verify(actorDAO, times(1)).borrarActor(1);
        verifyNoMoreInteractions(actorDAO);
    }


}
