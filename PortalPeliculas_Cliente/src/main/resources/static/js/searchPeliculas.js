document.addEventListener("DOMContentLoaded", function() {
    function selectOption(type, placeholder, buttonText) {
        const searchInput = document.getElementById('searchInput');
        const searchType = document.getElementById('searchType');
        const dropdownButton = document.getElementById('dropdownMenuButton');
        const selectPais = document.getElementById('selectPais');

        searchInput.placeholder = placeholder;
        searchInput.disabled = false;
        searchInput.value = ""; // Limpiar el valor al cambiar la opción
        searchType.value = type;
        dropdownButton.textContent = buttonText;

        if (type === 'pais') {
            searchInput.style.display = 'none';
            selectPais.style.display = 'block';
            selectPais.disabled = false;
            selectPais.name = 'pais';
        } else {
            searchInput.style.display = 'block';
            selectPais.style.display = 'none';
            searchInput.name = (type === 'actores') ? 'nombre' : type;

            // Validación de solo números para 'duracion' y 'annio'
            if (type === 'duracion' || type === 'annio') {
                searchInput.type = 'number';
                searchInput.setAttribute("min", "0"); // Opcional: añadir límite inferior
                searchInput.addEventListener('input', function() {
                    this.value = this.value.replace(/[^0-9]/g, ''); // Remover cualquier carácter que no sea numérico
                });
            } else {
                searchInput.type = 'text';
                searchInput.removeEventListener('input', null);
                searchInput.removeAttribute("min");
            }
        }
    }

    function submitSearch() {
        const form = document.getElementById('searchForm');
        const searchInput = document.getElementById('searchInput').value.trim();
        const selectPais = document.getElementById('selectPais').value.trim();
        const searchType = document.getElementById('searchType').value;

        if (!searchType || (!searchInput && searchType !== 'pais') || (searchType === 'pais' && !selectPais)) {
            alert('Por favor, seleccione una opción y llene el campo para buscar.');
            return;
        }

        let action = `/cpeliculas/buscar/${searchType}`;
        if (searchType === 'pais') {
            form.action = action;
            document.getElementById('searchInput').value = selectPais;
        } else {
            form.action = action;
        }

        form.submit();
    }

    function clearForm() {
        const searchInput = document.getElementById('searchInput');
        const selectPais = document.getElementById('selectPais');

        searchInput.value = '';
        selectPais.value = '';
        document.getElementById('searchType').value = '';
        document.getElementById('dropdownMenuButton').textContent = 'Seleccione una opción';
        searchInput.placeholder = 'Seleccione una opción de búsqueda';
        searchInput.disabled = true;
        selectPais.style.display = 'none';
        searchInput.style.display = 'block';
    }

    document.getElementById('searchInput').addEventListener('keypress', function(event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            submitSearch();
        }
    });

    window.selectOption = selectOption;
    window.submitSearch = submitSearch;
    window.clearForm = clearForm;
});
