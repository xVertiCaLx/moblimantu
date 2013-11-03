/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package printer;

import controller.CinemaController;
import controller.MovieController;
import entity.Cinema;
import entity.Movie;
import entity.Showtime;
import java.util.LinkedList;

/**
 *
 * @author Khach
 */
public class ShowtimePrinter implements Printer {
    private static final ShowtimePrinter INSTANCE = new ShowtimePrinter();
    private ShowtimePrinter(){}
    public static ShowtimePrinter getInstance() {
        return INSTANCE;
    }

    @Override
    public void printList(LinkedList list) {
        for (Object o: list) {
            printInstance(o);
            Showtime showtime = (Showtime)(o);
            Movie movie = MovieController.getMovieById(showtime.getMovieId());
            Cinema cinema = CinemaController.getCinemaById(showtime.getCinemaId());
            System.out.println(showtime.getId() + ". " + showtime.getTimeStringFormat() + " " + movie.getName() + " " 
                                + cinema.getName());                    
        }
    }

    @Override
    public void printInstance(Object o) {
            printInstance(o);
            Showtime showtime = (Showtime)(o);
            Movie movie = MovieController.getMovieById(showtime.getMovieId());
            Cinema cinema = CinemaController.getCinemaById(showtime.getCinemaId());
            System.out.println("ShowtimeID: " + showtime.getId());
            System.out.println("1. Show time: " + showtime.getTimeStringFormat());
            System.out.println("2. Cinema: " + cinema.getName() + " - ID: " + cinema.getName());
            System.out.println("3. Movie: " + movie.getName() + " - ID: " + movie.getId());
    }
    
}
