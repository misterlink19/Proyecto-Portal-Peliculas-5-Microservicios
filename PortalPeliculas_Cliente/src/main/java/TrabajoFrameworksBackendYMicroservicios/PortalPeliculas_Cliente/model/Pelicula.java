package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Cliente.model;

import jakarta.persistence.Transient;

import java.util.LinkedHashSet;
import java.util.Set;


public class Pelicula {

    private Integer id;
    private String titulo;
    private Integer annio;
    private String direccion;
    private String genero;
    private String sinopsis;
    private Integer duracion;
    private String imagenPortada;
    private Pais pais;

    private Set<Actor> actores = new LinkedHashSet<>();

    @Transient
    private Set<Integer> actoresIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnnio() {
        return annio;
    }

    public void setAnnio(Integer annio) {
        this.annio = annio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getImagenPortada() { return imagenPortada;}

    public void setImagenPortada(String imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

    public Pais getPais() { return pais; }

    public void setPais(Pais pais) { this.pais = pais; }

    public String getNombrePais() { return pais.getNombre(); }

    public Set<Actor> getActores() {
        return actores;
    }

    public void setActores(Set<Actor> actores) {
        this.actores = actores;
    }

    public Set<Integer> getActoresIds() {
        return actoresIds;
    }

    public void setActoresIds(Set<Integer> actoresIds) {
        this.actoresIds = actoresIds;
    }
}
