package org.codoacodo.models;

public class Movie {
    private int id;
    private String title;
    private String release_date;
    private String genre;
    private String duration;
    private String director;
    private String distribution;
    private String synopsis;
    private String image;

    public Movie() {}

    public Movie(int id, String title, String release_date, String genre, String duration, String director, String distribution, String synopsis, String image) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.genre = genre;
        this.duration = duration;
        this.director = director;
        this.distribution = distribution;
        this.synopsis = synopsis;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
