const API = "https://api.themoviedb.org/3/";
const options = {
    method: "GET",
    headers: {
        accept: "application/json",
        Authorization:
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhYTJjYTAwZDYxZWIzOTEyYjZlNzc4MDA4YWQ3ZmNjOCIsInN1YiI6IjYyODJmNmYwMTQ5NTY1MDA2NmI1NjlhYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4MJSPDJhhpbHHJyNYBtH_uCZh4o0e3xGhZpcBIDy-Y8",
    },
};

document.addEventListener("DOMContentLoaded", async () => {
    const moviesContainer = document.querySelector(".movies-container");
    const moviesTrendingSlider = document.getElementById("movies-plus");
    let page = 1;

    const data = await getTrendingMovies(page);
    const topRated = await getTopRatedMovies();

    const formatedData = data.map(
        (movie) => `
            <a href="./src/pages/detalle.html">
                <img src="https://image.tmdb.org/t/p/w500/${movie.poster_path}" class="rounded w-100" />
            </a>
        `
    );

    const sliderData = topRated.map(
        (movie) => `
        <img src="https://image.tmdb.org/t/p/w500/${movie.backdrop_path}" class="rounded" />
    `
    );

    moviesContainer.innerHTML = formatedData.join("");

    moviesTrendingSlider.innerHTML = sliderData.join("");
});

async function getTrendingMovies(page = 1) {
    const data = await fetch(`${API}/movie/popular?page=${page}`, options);

    const { results } = await data.json();

    const movies = results.map((movie) => ({
        poster_path: movie.poster_path,
        backdrop_path: movie.backdrop_path,
        title: movie.title,
    }));

    return movies;
}

async function getTopRatedMovies(page = 1) {
    const data = await fetch(`${API}/movie/top_rated?page=${page}`, options);

    const { results } = await data.json();

    const movies = results.map((movie) => ({
        poster_path: movie.poster_path,
        backdrop_path: movie.backdrop_path,
        title: movie.title,
    }));

    return movies;
}
