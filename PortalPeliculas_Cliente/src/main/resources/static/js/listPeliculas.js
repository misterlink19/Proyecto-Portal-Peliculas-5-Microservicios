function confirmDeletion(peliculaId) {
    if (confirm("¿Estás seguro de que quieres borrar esta película?")) {
        const currentPage = parseInt(new URLSearchParams(window.location.search).get('page')) || 1;
        const rows = document.querySelectorAll("tbody tr").length;

        fetch(`/cpeliculas/borrar/${peliculaId}?page=${currentPage}`)
            .then(response => {
                if (response.redirected) {
                    if (rows === 1 && currentPage > 1) {
                        window.location.href = `/cpeliculas/listado?page=${currentPage - 1}`;
                    } else {
                        window.location.href = response.url;
                    }
                } else {
                    window.location.reload();
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Error al borrar la película. Por favor, inténtalo de nuevo.");
            });
    }
}

document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll(".btn-danger").forEach(button => {
        button.addEventListener("click", function() {
            const peliculaId = this.getAttribute("th:onclick").match(/\d+/)[0];
            confirmDeletion(peliculaId);
        });
    });
});
