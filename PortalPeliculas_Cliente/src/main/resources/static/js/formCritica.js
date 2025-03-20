document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");
    const valoracionInput = document.getElementById("valoracion");
    const notaInput = document.getElementById("nota");

    form.addEventListener("submit", (event) => {
        if (valoracionInput.value.trim() === "") {
            alert("La valoración no puede estar vacía.");
            event.preventDefault();
            return;
        }

        if (notaInput.value < 1 || notaInput.value > 10) {
            alert("La nota debe estar entre 1 y 10.");
            event.preventDefault();
            return;
        }
    });
});
