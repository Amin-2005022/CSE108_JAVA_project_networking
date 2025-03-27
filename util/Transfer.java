package util;

import movie.mainproject.Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transfer implements Serializable {
    private String senderName,receiverName;
    private Movie movie;
    private List<Movie> list=new ArrayList<>();

    public List<Movie> getList() {
        return list;
    }

    public void setList(List<Movie> list) {
        this.list = list;
    }

    public Transfer(String senderName, String receiverName, Movie movie) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.movie = movie;
    }

    public Transfer(String receiverName) {
        this.receiverName = receiverName;
        this.senderName=null;
    }


    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}
