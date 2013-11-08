package printer;

import controller.CinemaController;
import controller.MovieController;
import entity.Cinema;
import entity.Movie;
import entity.Showtime;
import java.util.LinkedList;

public class ShowtimePrinter implements Printer {
    private static final ShowtimePrinter INSTANCE = new ShowtimePrinter();
    public static final int SHOWTIME_TIME = 1;    
    public static final int CINEMA= 2;        
    public static final int MOVIE = 3;            
    private ShowtimePrinter(){}
    public static ShowtimePrinter getInstance() {
        return INSTANCE;
    }

    @Override
    public void printList(LinkedList list) {
        for (int index = 0; index < list.size(); index++) {
            Showtime showtime = (Showtime)(list.get(index));
            Movie movie = MovieController.getMovieById(showtime.getMovieId());
            Cinema cinema = CinemaController.getCinemaById(showtime.getCinemaId());
            System.out.println((index + 1) + ". " + showtime.getTimeStringFormat() + " - " + movie.getName() + " - " 
                                + cinema.getName());                    
        }
    }

    @Override
    public void printInstance(Object o) {
        Showtime showtime = (Showtime)(o);
        Movie movie = MovieController.getMovieById(showtime.getMovieId());
        Cinema cinema = CinemaController.getCinemaById(showtime.getCinemaId());
        System.out.println("ShowtimeID:\t\t" + showtime.getId());
        System.out.println(SHOWTIME_TIME + ". Show time:\t\t" + showtime.getTimeStringFormat());
        System.out.println(CINEMA + ". Cinema:\t\t" + cinema.getName() + " - ID: " + cinema.getId());
        System.out.println(MOVIE + ". Movie:\t\t" + movie.getName() + " - ID: " + movie.getId());
    }
    
}
