package controller;

import db.MovieDB;
import entity.Movie;
import entity.Showtime;
import factory.MovieFactory;
import java.util.LinkedList;
import utils.Common;
import utils.Constant;

public class MovieController {
    /*
     * Add a new Movie to the list & database
     */
    public static void addMovie(int movieType, String movieName, int movieStatus, double rating) {
        LinkedList<Movie> list = MovieDB.getMovieList();
        Movie newMovie = MovieFactory.createNewInstance(movieType, movieName, movieStatus, rating);
        list.add(newMovie);
        MovieDB.commit();
    }
    
    /* Edit a specific movie with given information*/
    public static void editMovie(int movieId, Movie newMovie) {
        LinkedList<Movie> list = MovieDB.getMovieList();
        for(Movie m : list) {
            if (m.getId() == movieId) {
                m.setName(newMovie.getName());
                m.setRating(newMovie.getRating());
                m.setStatus(newMovie.getStatus());
                m.setType(newMovie.getType());
            }
        }
        MovieDB.commit();
    }
    /* 
     * Return a set of Movie which has the same title as args 
     */
    public static LinkedList<Movie> getMoviesByStatus(int ...args) {
        LinkedList<Movie> result = new LinkedList<Movie>();
        LinkedList<Movie> list = MovieDB.getMovieList();
        for(Movie m : list) {
            boolean isSelect = false;
            for(int status : args) 
            if (m.getStatus() == status) {
                isSelect = true;
            }
            if (isSelect) result.add(m);
        }
        return result;
    }
    /* 
     * Return a set of Movie which has the same title as title 
     */
    public static LinkedList<Movie> getMoviesByTitle(String title) {
        LinkedList<Movie> result = new LinkedList<Movie>();
        LinkedList<Movie> list = MovieDB.getMovieList();
        for(Movie m : list) {
            if (m.getName().compareTo(title) == 0) {
                result.add(m);
            }
        }
        return result;
    }
    
    /*
     * Return a specific movie with given Id
     */
    public static Movie getMovieById(int Id) {
        LinkedList<Movie> list = MovieDB.getMovieList();
        for(Movie m : list) {
            if (m.getId() == Id) return m;
        }
        return null;
    }
    
    /*
     * Return list of Movies with given Cinema Id
     */
    public static LinkedList<Movie> getMoviesByCinema(int cinemaId) {
        LinkedList<Movie> result = new LinkedList<Movie>();
        LinkedList<Showtime> st = ShowtimeController.getShowtimesByCinema(cinemaId);
        LinkedList<Movie> list = MovieDB.getMovieList();
        for(Movie m: list) {
            for(Showtime s: st) 
                if (m.getId() == s.getMovieId()) {
                    result.add(m);
                    break;
                }
        }
        return result;
    }
    
    /* Get the movie list */
    public static LinkedList<Movie> getMovieList() {
        return MovieDB.getMovieList();
    }
    
    /* Unit Test part */
    public static void main(String[] args) {
        Common.initDB();
        addMovie(Constant.MOVIE_TYPE_3D,"Titanic",Constant.MOVIE_STATUS_NOW_SHOWING,9.0);
    }
}
