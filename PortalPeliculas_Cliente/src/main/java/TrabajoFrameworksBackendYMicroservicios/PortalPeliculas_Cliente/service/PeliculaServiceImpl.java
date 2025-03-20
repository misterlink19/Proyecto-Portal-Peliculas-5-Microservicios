package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.service;

import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Pais;
import TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class PeliculaServiceImpl implements IPeliculaService{

    @Autowired
    private RestTemplate restTemplate;

    //private final String url = "http://localhost:8001/peliculas";

    private final String url = "http://localhost:9010/api/peliculas/peliculas";

    public List<Pais> getTodosLosPaises() {
        return Arrays.asList(Pais.values());
    }

    @Override
    public List<Pelicula> traeTodas() {
        Pelicula[] peliculasArray = restTemplate.getForObject(url, Pelicula[].class);
        return Arrays.asList(peliculasArray != null ? peliculasArray : new Pelicula[0]);
    }


    @Override
    public Page<Pelicula> buscaTodas(Pageable pageable) {
        Pelicula[] peliculas = restTemplate.getForObject(url, Pelicula[].class);
        return getPagina(pageable, peliculas);
    }

    @Override
    public Pelicula buscaPorId(int id) {
        return restTemplate.getForObject(url + "/" + id, Pelicula.class);
    }

    @Override
    public Page<Pelicula> buscaPorTitulo(String titulo, Pageable pageable) {
        Pelicula[] peliculas = restTemplate.getForObject(url + "/titulo/" + titulo, Pelicula[].class);
        return getPagina(pageable, peliculas);
    }

    @Override
    public Page<Pelicula> buscaPorGenero(String genero, Pageable pageable) {
        Pelicula[] peliculas = restTemplate.getForObject(url + "/genero/" + genero, Pelicula[].class);
        return getPagina(pageable, peliculas);
    }

    @Override
    public Page<Pelicula> buscarPorAnnio(int annio, Pageable pageable) {
        Pelicula[] peliculas = restTemplate.getForObject(url + "/annio/" + annio, Pelicula[].class);
        return getPagina(pageable, peliculas);
    }

    @Override
    public Page<Pelicula> buscarPorDireccion(String direccion, Pageable pageable) {
        Pelicula[] peliculas = restTemplate.getForObject(url + "/direccion/" + direccion, Pelicula[].class);
        return getPagina(pageable, peliculas);
    }

    @Override
    public Page<Pelicula> buscarPorPais(String pais, Pageable pageable) {
        Pelicula[] peliculas = restTemplate.getForObject(url + "/pais/" + pais, Pelicula[].class);
        return getPagina(pageable, peliculas);
    }

    private Page<Pelicula> getPagina(Pageable pageable, Pelicula[] peliculas) {
        List<Pelicula> peliculaList = Arrays.asList(peliculas);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Pelicula> list;

        if (peliculaList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, peliculaList.size());
            list = peliculaList.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of( currentPage,pageSize), peliculaList.size());
    }

    @Override
    public Page<Pelicula> buscarPorDuracion(int duracion, Pageable pageable) {
        Pelicula[] peliculas = restTemplate.getForObject(url + "/duracion/" + duracion, Pelicula[].class);
        return getPagina(pageable, peliculas);
    }

    @Override
    public Page<Pelicula> buscarPorActores(String nombre, Pageable pageable) {
        Pelicula[] peliculas = restTemplate.getForObject(url + "/actores/" + nombre, Pelicula[].class);
        return getPagina(pageable, peliculas);
    }

    @Override
    public void guardarPelicula(Pelicula pelicula) {
        if (pelicula.getId() != null && pelicula.getId() > 0) {
            restTemplate.put(url, pelicula);
        } else {
            restTemplate.postForObject(url, pelicula, Integer.class);
        }
    }


    @Override
    public void borrarPelicula(int idPelicula) {
        restTemplate.delete(url + "/" + idPelicula);
    }
}
