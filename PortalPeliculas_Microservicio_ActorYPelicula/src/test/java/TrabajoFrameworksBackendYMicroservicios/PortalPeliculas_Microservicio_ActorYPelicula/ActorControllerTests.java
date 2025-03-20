package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.controller.ActorController;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Actor;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.service.IActorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ActorControllerTests {

    private MockMvc mockMvc;

    @Mock
    private IActorService actorService;

    @InjectMocks
    private ActorController actorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(actorController).build();
    }

    @Test
    public void testBuscarTodos() throws Exception {
        Actor actor = new Actor();
        actor.setId(1);
        List<Actor> actores = Arrays.asList(actor);

        when(actorService.buscaTodos()).thenReturn(actores);

        mockMvc.perform(MockMvcRequestBuilders.get("/actores")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));

        verify(actorService, times(1)).buscaTodos();
        verifyNoMoreInteractions(actorService);
    }

    @Test
    public void testBuscaPorId() throws Exception {
        Actor actor = new Actor();
        actor.setId(1);

        when(actorService.buscaPorId(1)).thenReturn(actor);

        mockMvc.perform(MockMvcRequestBuilders.get("/actores/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

        verify(actorService, times(1)).buscaPorId(1);
        verifyNoMoreInteractions(actorService);
    }

    @Test
    public void testBuscarPorNombre() throws Exception {
        Actor actor = new Actor();
        actor.setId(1);
        actor.setNombre("Leonardo DiCaprio");

        when(actorService.buscarPorNombre("Leonardo")).thenReturn(Arrays.asList(actor));

        mockMvc.perform(MockMvcRequestBuilders.get("/actores/nombre/{nombre}", "Leonardo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Leonardo DiCaprio"));

        verify(actorService, times(1)).buscarPorNombre("Leonardo");
        verifyNoMoreInteractions(actorService);
    }

    @Test
    public void testBuscarPorPais() throws Exception {
        Actor actor = new Actor();
        actor.setId(1);
        actor.setNombre("Leonardo DiCaprio");

        when(actorService.buscarPorPais("Estados Unidos")).thenReturn(Arrays.asList(actor));

        mockMvc.perform(MockMvcRequestBuilders.get("/actores/pais/{pais}", "Estados Unidos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Leonardo DiCaprio"));

        verify(actorService, times(1)).buscarPorPais("Estados Unidos");
        verifyNoMoreInteractions(actorService);
    }
    @Test
    public void testBuscarPorPeliculas() throws Exception {
        Actor actor = new Actor();
        actor.setId(1);
        actor.setNombre("Leonardo DiCaprio");

        when(actorService.buscarPorPeliculas("Inception")).thenReturn(Arrays.asList(actor));

        mockMvc.perform(MockMvcRequestBuilders.get("/actores/peliculas/{titulo}", "Inception")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Leonardo DiCaprio"));

        verify(actorService, times(1)).buscarPorPeliculas("Inception");
        verifyNoMoreInteractions(actorService);
    }


    @Test
    public void testGuardarActor() throws Exception {
        Actor actor = new Actor();
        actor.setId(1);
        actor.setNombre("Leonardo DiCaprio");

        Mockito.doNothing().when(actorService).guardarActor(any(Actor.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/actores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nombre\": \"Leonardo DiCaprio\", \"fechaNacimiento\": \"1974-11-11\", \"pais\": \"Estados Unidos\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(actorService, times(1)).guardarActor(any(Actor.class));
    }

    @Test
    public void testActualizarActor() throws Exception {
        Actor actor = new Actor();
        actor.setId(1);
        actor.setNombre("Leonardo DiCaprio");

        Mockito.doNothing().when(actorService).actualizarActor(any(Actor.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/actores", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"nombre\": \"Leonardo DiCaprio\", \"fechaNacimiento\": \"1974-11-11\", \"pais\":\"Estados Unidos\" } "))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(actorService, times(1)).actualizarActor(any(Actor.class));
    }

    @Test
    public void testBorrarActor() throws Exception {
        Mockito.doNothing().when(actorService).borrarActor(anyInt());

        mockMvc.perform(MockMvcRequestBuilders.delete("/actores/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(actorService, times(1)).borrarActor(anyInt());
    }
}
