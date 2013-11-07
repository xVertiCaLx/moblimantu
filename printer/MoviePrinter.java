package printer;

import entity.Movie;
import java.util.LinkedList;

public class MoviePrinter implements Printer {
    private static final MoviePrinter INSTANCE = new MoviePrinter();
    
    public static final int MOVIE_TITLE = 1;
    public static final int MOVIE_TYPE = 2;
    public static final int MOVIE_STATUS = 3;
    public static final int MOVIE_RATING = 4;
    
    private MoviePrinter(){}
    public static MoviePrinter getInstance() {
        return INSTANCE;
    }
    
    @Override
    public void printList(LinkedList list) {
        for (int index = 0; index < list.size(); index ++) {
            Movie movie = (Movie)(list.get(index));
            System.out.println((index + 1) + ". " + movie.getName());
        }
    }
    @Override
    public void  printInstance(Object o) {
        Movie movie = (Movie)(o);
        System.out.println("MovieId: " + movie.getId());
        System.out.println(MOVIE_TITLE + ". Title: " + movie.getName());
        System.out.println(MOVIE_TYPE + ". Type: " + movie.getType());
        System.out.println(MOVIE_STATUS + ". Status: " + movie.getStatus());
        System.out.println(MOVIE_RATING + ". Rating: " + movie.getRating());
    } 
}
