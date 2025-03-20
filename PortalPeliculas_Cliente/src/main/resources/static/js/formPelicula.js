function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            document.getElementById("portadaPreview").setAttribute("src", e.target.result);
            document.getElementById("portadaPreview").style.display = 'block';
        };
        reader.readAsDataURL(input.files[0]);
    }
}

function validatePositiveNumber(input) {
    if (input.value < 0) {
        input.value = "";
        alert("Por favor, introduzca un valor positivo.");
    }
}

function allowOnlyNumbers(event) {
    const key = event.which ? event.which : event.keyCode;
    if (key < 48 || key > 57) {
        event.preventDefault();
    }
}

document.getElementById("imagenPortada").addEventListener("change", function (event) {
    event.preventDefault();
    readURL(this);
});

document.getElementById("annio").addEventListener("input", function () {
    validatePositiveNumber(this);
});

document.getElementById("duracion").addEventListener("input", function () {
    validatePositiveNumber(this);
});

document.getElementById("annio").addEventListener("keypress", allowOnlyNumbers);
document.getElementById("duracion").addEventListener("keypress", allowOnlyNumbers);
