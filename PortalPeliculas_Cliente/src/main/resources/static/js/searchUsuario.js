document.addEventListener("DOMContentLoaded", function () {
    function selectOption(type, placeholder, buttonText) {
        const searchInput = document.getElementById('searchInput');
        const searchType = document.getElementById('searchType');
        const dropdownButton = document.getElementById('dropdownMenuButton');

        searchInput.placeholder = placeholder;
        searchInput.disabled = false;
        searchInput.value = ""; // Limpiar el valor al cambiar la opción
        searchType.value = type;
        dropdownButton.textContent = buttonText;

        searchInput.name = type;
    }

    function submitSearch() {
        const form = document.getElementById('searchForm');
        const searchInput = document.getElementById('searchInput').value.trim();
        const searchType = document.getElementById('searchType').value;

        if (!searchType || !searchInput) {
            alert('Por favor, seleccione una opción y complete el campo.');
            return;
        }

        let action = `/cusuarios/buscar/${searchType}?${searchType}=${searchInput}`;
        form.action = action;
        form.submit();
    }

    function clearForm() {
        document.getElementById('searchInput').value = '';
        document.getElementById('searchType').value = '';
        document.getElementById('dropdownMenuButton').textContent = 'Seleccione una opción';
        document.getElementById('searchInput').placeholder = 'Seleccione una opción de búsqueda';
        document.getElementById('searchInput').disabled = true;
    }

    document.getElementById('searchInput').addEventListener('keypress', function (event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            submitSearch();
        }
    });

    window.selectOption = selectOption;
    window.submitSearch = submitSearch;
    window.clearForm = clearForm;
});
