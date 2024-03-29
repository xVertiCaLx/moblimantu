package factory;

import entity.Movie;
import java.util.LinkedList;
import utils.Common;

public class MovieFactory {
    public static Movie createNewInstance(int movieType, String movieName, int movieStatus, double rating) {
        return new Movie(Common.genMovieId(), movieType, movieName, movieStatus, rating);
    }
    public static Movie clone(Movie movie) {
        Movie newMovie = new Movie(movie.getId(), movie.getType(), movie.getName(), movie.getStatus(), movie.getRating());
        return newMovie;
    }
}
