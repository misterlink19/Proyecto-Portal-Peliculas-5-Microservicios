document.addEventListener("DOMContentLoaded", function () {
    // Manejar el evento de "Borrar Usuario"
    document.querySelectorAll(".btn-danger").forEach(button => {
        button.addEventListener("click", function () {
            const userId = this.getAttribute("th:onclick").match(/\d+/)[0];
            confirmDeletion(userId);
        });
    });

    // Manejar el botón "Guardar Cambios"
    const saveChangesButton = document.getElementById("saveChangesButton");
    if (saveChangesButton) {
        saveChangesButton.addEventListener("click", function () {
            alert("Guardar cambios no implementado aún");
        });
    }
});

// Confirmar eliminación
function confirmDeletion(userId) {
    if (confirm("¿Estás seguro de que quieres borrar este usuario?")) {
        const currentPage = parseInt(new URLSearchParams(window.location.search).get('page')) || 1;
        fetch(`/cusuarios/borrar/${userId}?page=${currentPage}`, { method: "DELETE" })
            .then(response => {
                if (response.redirected) {
                    window.location.href = response.url;
                } else {
                    window.location.reload();
                }
            })
            .catch(error => {
                console.error("Error al borrar usuario:", error);
                alert("Error al borrar el usuario. Por favor, inténtalo de nuevo.");
            });
    }
}
