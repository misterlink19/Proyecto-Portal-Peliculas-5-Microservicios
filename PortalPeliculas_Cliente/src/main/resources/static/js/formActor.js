function validatePositiveNumber(input) {
    if (input.value < 0) {
        input.value = '';
        alert('Por favor, introduzca un valor positivo.');
    }
}

function allowOnlyNumbers(event) {
    const charCode = event.which ? event.which : event.keyCode;
    if (charCode < 48 || charCode > 57) {
        event.preventDefault();
    }
}

document.getElementById("fechaNacimiento").addEventListener("input", function() {
    validatePositiveNumber(this);
});

document.getElementById("fechaNacimiento").addEventListener("keypress", allowOnlyNumbers);

// Nueva función para setear la fecha de nacimiento
function setDateField(fieldId, dateValue) {
    if (dateValue) {
        var date = new Date(dateValue);
        var formattedDate = date.toISOString().split('T')[0];
        document.getElementById(fieldId).value = formattedDate;
    }
}

// Llamar a la función con el valor de la fecha de nacimiento del actor desde el atributo data
document.addEventListener("DOMContentLoaded", function() {
    const fechaNacimiento = document.getElementById('fechaNacimiento').getAttribute('data-fechaNacimiento');
    setDateField('fechaNacimiento', fechaNacimiento);
});
