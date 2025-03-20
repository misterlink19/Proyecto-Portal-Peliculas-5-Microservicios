package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Actor {

    private Integer id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private Pais pais;
    private Set<Pelicula> peliculas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Pais getPais() { return pais;}

    public void setPais(Pais pais) { this.pais = pais;}

    public String getNombrePais() { return pais.getNombre(); }

    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public Set<Integer> getPeliculasIds() {
        return peliculas.stream().map(Pelicula::getId).collect(Collectors.toSet());
    }

    public void setPeliculasIds(Set<Integer> peliculasIds) {
        this.peliculas = peliculasIds.stream().map(id -> {
            Pelicula pelicula = new Pelicula();
            pelicula.setId(id);
            return pelicula;
        }).collect(Collectors.toSet());
    }
}
