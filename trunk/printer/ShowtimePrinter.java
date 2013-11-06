package printer;

import controller.CinemaController;
import controller.MovieController;
import entity.Cinema;
import entity.Movie;
import entity.Showtime;
import java.util.LinkedList;

public class ShowtimePrinter implements Printer {
    private static final ShowtimePrinter INSTANCE = new ShowtimePrinter();
    public static final int SHOWTIME_ID = 1;
    public static final int SHOWTIME_TIME = 2;    
    public static final int CINEMA= 3;        
    public static final int MOVIE = 4;            
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
        System.out.println(SHOWTIME_ID + ". ShowtimeID:\t" + showtime.getId());
        System.out.println(SHOWTIME_TIME + ". Show time:\t" + showtime.getTimeStringFormat());
        System.out.println(CINEMA + ". Cinema:\t\t" + cinema.getName() + " - ID: " + cinema.getId());
        System.out.println(MOVIE + ". Movie:\t\t" + movie.getName() + " - ID: " + movie.getId());
    }
    
}
