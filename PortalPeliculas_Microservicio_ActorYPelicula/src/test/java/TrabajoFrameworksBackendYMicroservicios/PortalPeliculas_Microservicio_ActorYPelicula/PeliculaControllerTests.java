package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.controller.PeliculaController;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Pelicula;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.service.IPeliculaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class PeliculaControllerTests {

    private MockMvc mockMvc;

    @Mock
    private IPeliculaService peliculaService;

    @InjectMocks
    private PeliculaController peliculaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(peliculaController).build();
    }

    @Test
    void testBuscarTodas() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setTitulo("Inception");
        Mockito.when(peliculaService.buscaTodas()).thenReturn(Arrays.asList(pelicula));

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Inception"));

        verify(peliculaService, times(1)).buscaTodas();
        verifyNoMoreInteractions(peliculaService);
    }

    @Test
    void testBuscaPorId() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setTitulo("Inception");
        Mockito.when(peliculaService.buscaPorId(1)).thenReturn(pelicula);

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("Inception"));

        verify(peliculaService, times(1)).buscaPorId(1);
        verifyNoMoreInteractions(peliculaService);
    }

    @Test
    void testBuscaPorTitulo() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setTitulo("Inception");
        Mockito.when(peliculaService.buscaPorTitulo("Inception")).thenReturn(Arrays.asList(pelicula));

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas/titulo/Inception"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Inception"));

        verify(peliculaService, times(1)).buscaPorTitulo("Inception");
        verifyNoMoreInteractions(peliculaService);
    }

    @Test
    void testBuscaPorGenero() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setGenero("Ciencia Ficción");
        Mockito.when(peliculaService.buscaPorGenero("Ciencia Ficción")).thenReturn(Arrays.asList(pelicula));

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas/genero/Ciencia Ficción"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genero").value("Ciencia Ficción"));

        verify(peliculaService, times(1)).buscaPorGenero("Ciencia Ficción");
        verifyNoMoreInteractions(peliculaService);
    }

    @Test
    void testBuscarPorAnnio() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setAnnio(2010);
        Mockito.when(peliculaService.buscarPorAnnio(2010)).thenReturn(Arrays.asList(pelicula));

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas/annio/2010"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].annio").value(2010));

        verify(peliculaService, times(1)).buscarPorAnnio(2010);
        verifyNoMoreInteractions(peliculaService);
    }

    @Test
    void testBuscarPorDireccion() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setDireccion("Christopher Nolan");
        Mockito.when(peliculaService.buscarPorDireccion("Christopher Nolan")).thenReturn(Arrays.asList(pelicula));

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas/direccion/Christopher Nolan"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].direccion").value("Christopher Nolan"));

        verify(peliculaService, times(1)).buscarPorDireccion("Christopher Nolan");
        verifyNoMoreInteractions(peliculaService);
    }

    @Test
    void testBuscarPorPais() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setPais("Estados Unidos");

        Mockito.when(peliculaService.buscarPorPais("Estados Unidos")).thenReturn(Arrays.asList(pelicula));

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas/pais/Estados Unidos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].pais").value("Estados Unidos"));

        verify(peliculaService, times(1)).buscarPorPais("Estados Unidos");
        verifyNoMoreInteractions(peliculaService);
    }

    @Test
    void testGuardarPelicula() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setTitulo("Inception");
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/peliculas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pelicula)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(peliculaService, times(1)).guardarPelicula(any(Pelicula.class));
        verifyNoMoreInteractions(peliculaService);
    }

    @Test
    void testActualizarPelicula() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setTitulo("Inception");
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.put("/peliculas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pelicula)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(peliculaService, times(1)).actualizarPelicula(any(Pelicula.class));
        verifyNoMoreInteractions(peliculaService);
    }

    @Test
    void testBorrarPelicula() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/peliculas/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(peliculaService, times(1)).borrarPelicula(1);
        verifyNoMoreInteractions(peliculaService);
    }
}
