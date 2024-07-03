const tb = document.getElementById("table-body");
const form = document.getElementById("formAdmin");
const title = document.getElementById("title");
const release_date = document.getElementById("release_date");
const genre = document.getElementById("genre");
const duration = document.getElementById("duration");
const director = document.getElementById("director");
const distribution = document.getElementById("distribution");
const synopsis = document.getElementById("synopsis");
const image = document.getElementById("image");
let movies = [];

document.addEventListener("DOMContentLoaded", function () {
    localStorage.removeItem("movie_id");

    form.addEventListener("submit", () => {
        const movie = {
            title: title.value,
            release_date: release_date.value,
            genre: genre.value,
            duration: duration.value,
            director: director.value,
            distribution: distribution.value,
            synopsis: synopsis.value,
            image: image.files[0] ? image.files[0].name : "default",
        };

        saveMovie(movie);
    });

    loadMovies();
});

function loadMovies() {
    fetch("http://localhost:8080/CAC/movies")
        .then((response) => response.json())
        .then((data) => {
            data.forEach((movie) => {
                tb.innerHTML += `
                    <tr>
                        <td>${movie.id}</td>
                        <td>${movie.title}</td>
                        <td>${movie.release_date}</td>
                        <td>${movie.genre}</td>
                        <td>${movie.duration}</td>
                        <td>${movie.director}</td>
                        <td>${movie.distribution}</td>
                        <td>${movie.synopsis}</td>
                        <td>${movie.image}</td>
                        <td>
                            <button class="btn btn-primary" onclick="updateForm(${movie.id})">
                                Editar
                            </button>
                        </td>
                        <td>
                            <button class="btn btn-danger" onclick="removeMovie(${movie.id})">Eliminar</button>
                        </td>
                    </tr>
                `;

                movies.push(movie);
            });
        });
}

function saveMovie(movie) {
    const id = localStorage.getItem("movie_id");

    if (!id) {
        fetch("http://localhost:8080/CAC/movies", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(movie),
        }).then((response) => {
            if (response.ok) {
                location.reload();
            }
        });
    } else {
        fetch(`http://localhost:8080/CAC/movies/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(movie),
        }).then((response) => {
            if (response.ok) {
                location.reload();
            }
        });

        localStorage.removeItem("movie_id");
    }
}

function updateForm(movieId) {
    localStorage.setItem("movie_id", movieId);

    const movie = movies.find((m) => m.id === movieId);
    title.value = movie.title;
    release_date.value = movie.release_date;
    genre.value = movie.genre;
    duration.value = movie.duration;
    director.value = movie.director;
    distribution.value = movie.distribution;
    synopsis.value = movie.synopsis;
}

function removeMovie(movieId) {
    fetch(`http://localhost:8080/CAC/movies/${movieId}`, {
        method: "DELETE",
    });

    location.reload();
}
