document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");
    const emailInput = document.getElementById("email");
    const usernameInput = document.getElementById("username");
    const roleSelect = document.getElementById("role");

    form.addEventListener("submit", (event) => {
        if (!validateEmail(emailInput.value)) {
            alert("Por favor, ingresa un correo electrónico válido.");
            event.preventDefault();
            return;
        }

        if (usernameInput.value.trim() === "") {
            alert("El nombre de usuario no puede estar vacío.");
            event.preventDefault();
            return;
        }

        if (roleSelect.value === "") {
            alert("Por favor, selecciona un rol válido para el usuario.");
            event.preventDefault();
            return;
        }
    });

    function validateEmail(email) {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }
});
