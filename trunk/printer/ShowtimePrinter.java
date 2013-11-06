package printer;

import controller.CinemaController;
import controller.MovieController;
import entity.Cinema;
import entity.Movie;
import entity.Showtime;
import java.util.LinkedList;

public class ShowtimePrinter implements Printer {
    private static final ShowtimePrinter INSTANCE = new ShowtimePrinter();
    private ShowtimePrinter(){}
    public static ShowtimePrinter getInstance() {
        return INSTANCE;
    }

    @Override
    public void printList(LinkedList list) {
        for (Object o: list) {
            Showtime showtime = (Showtime)(o);
            Movie movie = MovieController.getMovieById(showtime.getMovieId());
            Cinema cinema = CinemaController.getCinemaById(showtime.getCinemaId());
            System.out.println(showtime.getId() + ". " + showtime.getTimeStringFormat() + " - " + movie.getName() + " - " 
                                + cinema.getName());                    
        }
    }

    @Override
    public void printInstance(Object o) {
        Showtime showtime = (Showtime)(o);
        Movie movie = MovieController.getMovieById(showtime.getMovieId());
        Cinema cinema = CinemaController.getCinemaById(showtime.getCinemaId());
        System.out.println("ShowtimeID:\t" + showtime.getId());
        System.out.println("Show time:\t" + showtime.getTimeStringFormat());
        System.out.println("Cinema:\t\t" + cinema.getName() + " - ID: " + cinema.getId());
        System.out.println("Movie:\t\t" + movie.getName() + " - ID: " + movie.getId());
    }
    
}
