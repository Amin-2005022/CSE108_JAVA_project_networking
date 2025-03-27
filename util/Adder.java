package util;

import movie.mainproject.Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Adder implements Serializable {
    String name;
    List<Movie> movielist= new ArrayList<>();
    Movie movie;

    public List<Movie> getMovielist() {
        return movielist;
    }

    public void setMovielist(List<Movie> movielist) {
        this.movielist = movielist;
    }

    public Adder(String name, List<Movie> movielist) {
        this.name = name;
        this.movielist = movielist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Adder(String name, Movie movie) {
        this.name = name;
        this.movie = movie;
    }
}