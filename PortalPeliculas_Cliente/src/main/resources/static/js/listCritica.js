function confirmDeletion(criticaId) {
    if (confirm("¿Estás seguro de que quieres borrar esta crítica?")) {
        const currentPage = new URLSearchParams(window.location.search).get('page') || 1;
        const returnUrl = encodeURIComponent(window.location.pathname + window.location.search); // Capturar URL actual

        // Construir la URL para eliminar la crítica
        const url = `/ccriticas/borrar/${criticaId}?page=${currentPage}&returnUrl=${returnUrl}`;

        fetch(url, { method: "GET" })
            .then(response => {
                if (response.redirected) {
                    window.location.href = response.url;
                } else {
                    document.querySelector(`tr[data-id="${criticaId}"]`).remove();
                    showMessage("Crítica eliminada exitosamente.");
                }
            })
            .catch(error => {
                console.error("Error:", error);
                showMessage("Error al borrar la crítica. Inténtalo de nuevo.", "danger");
            });
    }
}

function showMessage(message, type = "success") {
    const alertDiv = document.createElement("div");
    alertDiv.className = `alert alert-${type} alert-dismissible fade show`;
    alertDiv.role = "alert";
    alertDiv.innerHTML = `<span>${message}</span>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>`;

    document.querySelector(".container.py-4").prepend(alertDiv);

    setTimeout(() => {
        alertDiv.remove();
    }, 5000);
}

document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".btn-danger").forEach(button => {
        button.addEventListener("click", function () {
            const criticaId = this.closest("tr").getAttribute("data-id");
            confirmDeletion(criticaId);
        });
    });
});
