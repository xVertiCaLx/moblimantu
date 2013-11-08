/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import controller.CinemaController;
import controller.MovieController;
import controller.ShowtimeController;
import entity.Cinema;
import entity.Movie;
import java.util.LinkedList;
import java.util.Scanner;
import printer.CinemaPrinter;
import printer.MoviePrinter;
import utils.Common;
import utils.Constant;
import utils.References;

/**
 *
 * @author Khach
 */
public class AddShowtimePage {
    private static final AddShowtimePage INSTANCE = new AddShowtimePage();
    
    private AddShowtimePage(){}
    public static AddShowtimePage getInstance() {
        return INSTANCE;
    }
    public void launch() {
        Scanner sc = References.getInputStream();
        int choice = 0;
        do {
            System.out.println("===Adding Showtime===");
            /*
             * get Movie Id
             */
            LinkedList<Movie> movieList = MovieController.getMoviesByStatus(Constant.MOVIE_STATUS_NOW_SHOWING);
            MoviePrinter.getInstance().printList(movieList);
            System.out.print("Enter movie (1-" + movieList.size() + ") to add showtime, enter 0 to cancel: ");
            int mvIndex = Integer.parseInt(sc.nextLine());
            Movie movie;
            if (1 <= mvIndex && mvIndex <= movieList.size()) {
                movie = movieList.get(mvIndex - 1);
            } else break;
            
            /*
             * get cinema Id
             */
            LinkedList<Cinema> cinemaList = CinemaController.getCinemaList();
            CinemaPrinter.getInstance().printList(cinemaList);
            System.out.print("Enter cinema (1-" + cinemaList.size() + ") to add showtime, enter 0 to cancel: ");            
            int cnIndex = Integer.parseInt(sc.nextLine());
            Cinema cinema;
            if (1 <= cnIndex && cnIndex <= cinemaList.size()) {
                cinema = cinemaList.get(cnIndex - 1);
            } else break;
            
            System.out.print("Enter time to show (with format YYYY-MM-DD hh:mm:ss): ");
            String time = sc.nextLine();
            ShowtimeController.addShowtime(time, movie.getId(), cinema.getId());
            System.out.println("Showtime added ...");
            System.out.println();
            System.out.println("1. Add another showtime");
            System.out.println("2. Go back to Staff Function page ...");
            System.out.println("Please choose your option: ");
            choice = Integer.parseInt(sc.nextLine());
        } while (choice != 2);        
    }
    
    public static void main(String[] args) {
        Common.initDB();
        getInstance().launch();
    }
}
