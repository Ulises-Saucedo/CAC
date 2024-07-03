package org.codoacodo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codoacodo.models.Movie;
import org.codoacodo.services.MovieService;

@WebServlet("/movies/*")
public class MovieServlet extends HttpServlet {
    private MovieService movieService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        movieService = new MovieService();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        try {
            if(pathInfo == null || pathInfo.equals("/")){
                List<Movie> movies = movieService.getAllMovies();
                String json = objectMapper.writeValueAsString(movies);

                res.setContentType("application/json");
                res.getWriter().write(json);
            } else {
                String[] pathParts = pathInfo.split("/");
                int id = Integer.parseInt(pathParts[1]);
                Movie movie = movieService.getMovieById(id);

                if(movie != null){
                    String json = objectMapper.writeValueAsString(movie);

                    res.setContentType("application/json");
                    res.getWriter().write(json);
                } else {
                    res.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            Movie movie = objectMapper.readValue(req.getReader(), Movie.class);
            movieService.addMovie(movie);
            res.setStatus(HttpServletResponse.SC_CREATED);
        } catch (SQLException | ClassNotFoundException e) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String pathInfo=req.getPathInfo();
        try {
            if(pathInfo!=null&&pathInfo.split("/").length>1) {
                int id = Integer.parseInt(pathInfo.split("/")[1]);
                Movie movie = objectMapper.readValue(req.getReader(), Movie.class);
                movieService.updateMovie(id, movie);
                res.setStatus(HttpServletResponse.SC_CREATED);
            }
            else {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
        catch(SQLException|ClassNotFoundException e) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String pathInfo=req.getPathInfo();
        try {
            if(pathInfo!=null&&pathInfo.split("/").length>1) {
                int id = Integer.parseInt(pathInfo.split("/")[1]);
                movieService.deleteMovieById(id);
                res.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
            else {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
        catch(SQLException|ClassNotFoundException e) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
