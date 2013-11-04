/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package printer;

import entity.Movie;
import java.util.LinkedList;

/**
 *
 * @author Khach
 */
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
        for (Object movie: list) {
            System.out.println(((Movie)movie).getId() + ". " + ((Movie)movie).getName());
        }
    }
    @Override
    public void  printInstance(Object movie) {
        System.out.println("MovieId: " + ((Movie)movie).getId());
        System.out.println(MOVIE_TITLE + ". Title: " + ((Movie)movie).getName());
        System.out.println(MOVIE_TYPE + ". Type: " + ((Movie)movie).getType());
        System.out.println(MOVIE_STATUS + ". Status: " + ((Movie)movie).getStatus());
        System.out.println(MOVIE_RATING + ". Rating: " + ((Movie)movie).getRating());
    } 
}
