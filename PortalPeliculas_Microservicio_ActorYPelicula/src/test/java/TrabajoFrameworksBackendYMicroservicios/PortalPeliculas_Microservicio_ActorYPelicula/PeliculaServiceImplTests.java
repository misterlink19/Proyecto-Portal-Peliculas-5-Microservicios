package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.dao.IPeliculaDAO;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model.Pelicula;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.service.PeliculaServiceImpl;
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
 * Esto asegura que las interacciones
 * con los DAOs se realizan de manera adecuada
 * y que los métodos de servicio devuelven
 * los resultados correctos.
 * */
public class PeliculaServiceImplTests {

    @Mock
    private IPeliculaDAO peliculaDAO;

    @InjectMocks
    private PeliculaServiceImpl peliculaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscaTodas() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        List<Pelicula> peliculas = Arrays.asList(pelicula);

        when(peliculaDAO.buscaTodas()).thenReturn(peliculas);

        List<Pelicula> result = peliculaService.buscaTodas();

        assertEquals(1, result.size());
        verify(peliculaDAO, times(1)).buscaTodas();
        verifyNoMoreInteractions(peliculaDAO);
    }

    @Test
    public void testBuscaPorId() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);

        when(peliculaDAO.buscaPorId(1)).thenReturn(pelicula);

        Pelicula result = peliculaService.buscaPorId(1);

        assertEquals(1, result.getId());
        verify(peliculaDAO, times(1)).buscaPorId(1);
        verifyNoMoreInteractions(peliculaDAO);
    }

    @Test
    public void testBuscaPorTitulo() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setTitulo("Inception");

        when(peliculaDAO.buscaPorTitulo("Inception")).thenReturn(Arrays.asList(pelicula));

        List<Pelicula> result = peliculaService.buscaPorTitulo("Inception");

        assertEquals("Inception", result.get(0).getTitulo());
        verify(peliculaDAO, times(1)).buscaPorTitulo("Inception");
        verifyNoMoreInteractions(peliculaDAO);
    }

    @Test
    public void testBuscaPorGenero() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setGenero("Ciencia Ficción");

        when(peliculaDAO.buscaPorGenero("Ciencia Ficción")).thenReturn(Arrays.asList(pelicula));

        List<Pelicula> result = peliculaService.buscaPorGenero("Ciencia Ficción");

        assertEquals("Ciencia Ficción", result.get(0).getGenero());
        verify(peliculaDAO, times(1)).buscaPorGenero("Ciencia Ficción");
        verifyNoMoreInteractions(peliculaDAO);
    }

    @Test
    public void testBuscarPorAnnio() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setAnnio(2010);

        when(peliculaDAO.buscarPorAnnio(2010)).thenReturn(Arrays.asList(pelicula));

        List<Pelicula> result = peliculaService.buscarPorAnnio(2010);

        assertEquals(2010, result.get(0).getAnnio());
        verify(peliculaDAO, times(1)).buscarPorAnnio(2010);
        verifyNoMoreInteractions(peliculaDAO);
    }

    @Test
    public void testBuscarPorDireccion() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setDireccion("Christopher Nolan");

        when(peliculaDAO.buscarPorDireccion("Christopher Nolan")).thenReturn(Arrays.asList(pelicula));

        List<Pelicula> result = peliculaService.buscarPorDireccion("Christopher Nolan");

        assertEquals("Christopher Nolan", result.get(0).getDireccion());
        verify(peliculaDAO, times(1)).buscarPorDireccion("Christopher Nolan");
        verifyNoMoreInteractions(peliculaDAO);
    }

    @Test
    public void testBuscarPorPais() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setPais("Estados Unidos");


        when(peliculaDAO.buscarPorPais("Estados Unidos")).thenReturn(Arrays.asList(pelicula));

        List<Pelicula> result = peliculaService.buscarPorPais("Estados Unidos");

        assertEquals("Estados Unidos", result.get(0).getPais());
        verify(peliculaDAO, times(1)).buscarPorPais("Estados Unidos");
        verifyNoMoreInteractions(peliculaDAO);
    }

    @Test
    public void testBuscarPorDuracion() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setDuracion(148);

        when(peliculaDAO.buscarPorDuracion(148)).thenReturn(Arrays.asList(pelicula));

        List<Pelicula> result = peliculaService.buscarPorDuracion(148);

        assertEquals(148, result.get(0).getDuracion());
        verify(peliculaDAO, times(1)).buscarPorDuracion(148);
        verifyNoMoreInteractions(peliculaDAO);
    }

    @Test
    public void testBuscarPorActores() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setTitulo("Inception");

        when(peliculaDAO.buscarPorActores("Leonardo DiCaprio")).thenReturn(Arrays.asList(pelicula));

        List<Pelicula> result = peliculaService.buscarPorActores("Leonardo DiCaprio");

        assertEquals("Inception", result.get(0).getTitulo());
        verify(peliculaDAO, times(1)).buscarPorActores("Leonardo DiCaprio");
        verifyNoMoreInteractions(peliculaDAO);
    }

    @Test
    public void testGuardarPelicula() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setTitulo("Inception");

        doNothing().when(peliculaDAO).guardarPelicula(any(Pelicula.class));

        peliculaService.guardarPelicula(pelicula);

        verify(peliculaDAO, times(1)).guardarPelicula(pelicula);
        verifyNoMoreInteractions(peliculaDAO);
    }

    @Test
    public void testActualizarPelicula() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1);
        pelicula.setTitulo("Inception");

        doNothing().when(peliculaDAO).actualizarPelicula(any(Pelicula.class));

        peliculaService.actualizarPelicula(pelicula);

        verify(peliculaDAO, times(1)).actualizarPelicula(pelicula);
        verifyNoMoreInteractions(peliculaDAO);
    }

    @Test
    public void testBorrarPelicula() {
        doNothing().when(peliculaDAO).borrarPelicula(anyInt());

        peliculaService.borrarPelicula(1);

        verify(peliculaDAO, times(1)).borrarPelicula(1);
        verifyNoMoreInteractions(peliculaDAO);
    }
}
