/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import db.MovieDB;
import entity.Movie;
import java.util.LinkedList;

/**
 *
 * @author Vu
 */
public class MovieFactory {
    public static Movie createNewMovie(String movieType, String movieName, String movieStatus, double rating) {
        LinkedList<Movie> list = MovieDB.getMovieList();
        return new Movie(list.size()+1, movieType, movieName, movieStatus, rating);
    }
    public static Movie clone(Movie movie) {
        Movie newMovie = new Movie(movie.getId(), movie.getType(), movie.getName(), movie.getStatus(), movie.getRating());
        return newMovie;
    }
}
