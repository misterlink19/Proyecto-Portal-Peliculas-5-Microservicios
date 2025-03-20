document.addEventListener("DOMContentLoaded", function () {
    const actores = document.querySelectorAll("#actores-list li");
    const actoresArray = Array.from(actores).map(actor => {
        const actorId = actor.getAttribute("data-id");
        const actorNombre = actor.textContent.trim();
        return `<a href="/cactor/detalle/${actorId}">${actorNombre}</a>`;
    });
    const actoresTexto = actoresArray.join(", ");

    const actoresElemento = document.getElementById("actores");
    actoresElemento.innerHTML = actoresTexto;

    const userInfo = document.getElementById("user-info");
    const userEmail = userInfo ? userInfo.getAttribute("data-email") : null;
    const criticasList = document.getElementById("criticas-list");
    const criticaBotonContainer = document.getElementById("critica-boton-container");
    let userCritica = null;

    if (userEmail && criticasList) {
        const criticas = criticasList.querySelectorAll(".list-group-item");

        criticas.forEach(critica => {
            const criticaEmail = critica.getAttribute("data-user-email");

            if (criticaEmail === userEmail) {
                userCritica = critica;
            }
        });

        if (userCritica) {
            if (criticaBotonContainer) {
                criticaBotonContainer.style.display = "none";
            }
        }
    }

    // Ocultar alertas después de 5 segundos
    setTimeout(() => {
        let alerts = document.querySelectorAll(".alert");
        alerts.forEach(alert => {
            alert.classList.remove("show");
            alert.classList.add("fade");
        });
    }, 5000);
});

