/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import entity.Movie;
import java.util.LinkedList;
import utils.Common;

/**
 *
 * @author Vu
 */
public class MovieFactory {
    public static Movie createNewInstance(int movieType, String movieName, String movieStatus, double rating) {
        return new Movie(Common.genMovieId(), movieType, movieName, movieStatus, rating);
    }
    public static Movie clone(Movie movie) {
        Movie newMovie = new Movie(movie.getId(), movie.getType(), movie.getName(), movie.getStatus(), movie.getRating());
        return newMovie;
    }
}
