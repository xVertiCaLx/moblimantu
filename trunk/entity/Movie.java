package entity;

import utils.Constant;

public class Movie {
    private int id;
    private int type;
    private String name;
    private int status;
    private double rating;
    
    public Movie() {
    }
    public Movie(Movie movie) {
        this(movie.getId(), movie.getType(), movie.getName(), movie.getStatus(), movie.getRating());
    }
    public Movie(int id, int type, String name, int status, double rating) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.status = status;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isNowShowing() {
        return status == Constant.MOVIE_STATUS_NOW_SHOWING;
    }
    
}