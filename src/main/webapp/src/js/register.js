document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("formPelis");
    const nombre = document.getElementById("username");
    const email = document.getElementById("email");
    const password = document.getElementById("password");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        if (
            nombre.value.length == 0 ||
            password.value.length == 0 ||
            email.value.length == 0
        ) {
            if (nombre.value.length == 0) {
                usernameError = document.getElementById("error-username");

                usernameError.textContent =
                    "Por favor, introduce un nombre de usuario.";
            }

            if (password.value.length == 0) {
                passwordError = document.getElementById("error-password");

                passwordError.textContent =
                    "Por favor, introduce una contraseña.";
            }

            if (email.value.length == 0) {
                emailError = document.getElementById("error-email");

                emailError.textContent =
                    "Por favor, introduce un email válido.";
            }

            return;
        }

        alert("Registro exitoso");
    });
});
