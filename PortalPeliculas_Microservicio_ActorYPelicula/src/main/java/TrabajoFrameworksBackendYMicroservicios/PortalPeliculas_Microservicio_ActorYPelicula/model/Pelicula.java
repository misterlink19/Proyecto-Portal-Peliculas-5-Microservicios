package TrabajoFrameworksBackendYMicroservicios.PortalPeliculas_Microservicio_ActorYPelicula.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "peliculas")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPelicula", nullable = false)
    private Integer id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "annio", nullable = false)
    private Integer annio;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "genero", nullable = false, length = 100)
    private String genero;

    @Column(name = "sinopsis", nullable = false , length = 1000)
    private String sinopsis;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Column(name = "imagen_Portada")
    private String imagenPortada;

    @Column(name = "pais", nullable = false)
    private String pais;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "reparto",
            joinColumns = {
                    @JoinColumn(name = "idPelicula", referencedColumnName = "idPelicula")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "idActor", referencedColumnName = "idActor")
            }
    )
    @JsonIgnoreProperties("peliculas")// Aquí ignoramos la propiedad "peliculas" en la entidad Actor
    private Set<Actor> actores = new LinkedHashSet<>();

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

    public String getImagenPortada() {
        return imagenPortada;
    }

    public void setImagenPortada(String imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

    public String getPais() { return pais;}

    public void setPais(String pais) {this.pais = pais;}
    public Set<Actor> getActores() {
        return actores;
    }

    public void setActores(Set<Actor> actores) {
        this.actores = actores;
    }

}