package org.codoacodo.services;

import org.codoacodo.db.ConnectionDB;
import org.codoacodo.models.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieService {
    private ConnectionDB connectionDB;

    public MovieService() {
        this.connectionDB = new ConnectionDB();
    }

    public ArrayList<Movie> getAllMovies() throws SQLException, ClassNotFoundException {
        ArrayList<Movie> movies = new ArrayList<>();
        Connection connection = connectionDB.getConnection();
        String sql = "SELECT * FROM peliculas";
        PreparedStatement PS = connection.prepareStatement(sql);
        ResultSet RS = PS.executeQuery();

        while(RS.next()) {
            Movie movie = new Movie(
                    RS.getInt("id"),
                    RS.getString("titulo"),
                    RS.getString("fecha_lanzamiento"),
                    RS.getString("genero"),
                    RS.getString("duracion"),
                    RS.getString("director"),
                    RS.getString("reparto"),
                    RS.getString("sinopsis"),
                    RS.getString("imagen")
            );

            movies.add(movie);
        }

        RS.close();
        PS.close();

        return movies;
    }

    public Movie getMovieById(int id) throws SQLException, ClassNotFoundException {
        Movie movie = null;
        Connection connection = connectionDB.getConnection();
        String sql = "SELECT * FROM peliculas where id=?";
        PreparedStatement PS = connection.prepareStatement(sql);
        PS.setInt(1, id);
        ResultSet RS = PS.executeQuery();

        while(RS.next()) {
            movie = new Movie(
                    RS.getInt("id"),
                    RS.getString("titulo"),
                    RS.getString("fecha_lanzamiento"),
                    RS.getString("genero"),
                    RS.getString("duracion"),
                    RS.getString("director"),
                    RS.getString("reparto"),
                    RS.getString("sinopsis"),
                    RS.getString("imagen")
            );
        }

        RS.close();
        PS.close();
        connection.close();

        return movie;
    }

    public void addMovie(Movie movie) throws SQLException, ClassNotFoundException {
        Connection connection = connectionDB.getConnection();
        String sql = "INSERT INTO peliculas (titulo, fecha_lanzamiento, genero, duracion, director, reparto, sinopsis, imagen) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement PS = connection.prepareStatement(sql);
        PS.setString(1, movie.getTitle());
        PS.setString(2, movie.getRelease_date());
        PS.setString(3, movie.getGenre());
        PS.setString(4, movie.getDuration());
        PS.setString(5, movie.getDirector());
        PS.setString(6, movie.getDistribution());
        PS.setString(7, movie.getSynopsis());
        PS.setString(8, movie.getImage());
        PS.executeUpdate();

        connection.close();
    }

    public void updateMovie(int id, Movie movie) throws SQLException, ClassNotFoundException {
        Connection connection = connectionDB.getConnection();
        String sql = "UPDATE peliculas SET titulo = ?,  fecha_lanzamiento = ?, genero = ?, duracion = ?, director = ?, " +
                "reparto = ?, sinopsis = ?, imagen = ? WHERE id = ?";
        PreparedStatement PS = connection.prepareStatement(sql);
        PS.setString(1, movie.getTitle());
        PS.setString(2, movie.getRelease_date());
        PS.setString(3, movie.getGenre());
        PS.setString(4, movie.getDuration());
        PS.setString(5, movie.getDirector());
        PS.setString(6, movie.getDistribution());
        PS.setString(7, movie.getSynopsis());
        PS.setString(8, movie.getImage());
        PS.setInt(9, id);
        PS.executeUpdate();

        PS.close();
        connection.close();
    }

    public void deleteMovieById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = connectionDB.getConnection();
        String sql = "DELETE FROM peliculas where id=?";
        PreparedStatement PS = connection.prepareStatement(sql);
        PS.setInt(1, id);
        PS.executeUpdate();
        PS.close();
        connection.close();
    }
}
