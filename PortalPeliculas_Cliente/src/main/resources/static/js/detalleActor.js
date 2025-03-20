document.addEventListener("DOMContentLoaded", function() {
    const peliculas = document.querySelectorAll("#peliculas-list li");
    const peliculasArray = Array.from(peliculas).map(pelicula => {
        const peliculaId = pelicula.getAttribute("data-id");
        const peliculaTitulo = pelicula.textContent.trim();
        return `<a href="/cpeliculas/detalle/${peliculaId}">${peliculaTitulo}</a>`;
    });
    const peliculasTexto = peliculasArray.join(", ");

    const peliculasElemento = document.getElementById("peliculas");
    peliculasElemento.innerHTML = peliculasTexto;
});
