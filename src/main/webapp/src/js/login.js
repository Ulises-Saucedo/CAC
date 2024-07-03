document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("formPelis");
    const nombre = document.getElementById("username");
    const password = document.getElementById("password");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        if (nombre.value.length == 0 || password.value.length == 0) {
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

            return;
        }

        alert("Bienvenido");
    });
});
