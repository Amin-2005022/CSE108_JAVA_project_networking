package util;

import movie.mainproject.Movie;

import java.io.Serializable;
import java.util.List;

public class LoginDTO implements Serializable {
    public String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String comand) {
        this.command = comand;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private String userName;
    private String password;
    private boolean status;

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    private List<Movie> movieList;
}
