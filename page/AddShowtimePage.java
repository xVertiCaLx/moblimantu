/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import controller.CinemaController;
import controller.MovieController;
import controller.ShowtimeController;
import java.util.LinkedList;
import java.util.Scanner;
import printer.CinemaPrinter;
import printer.MoviePrinter;
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
        LinkedList list;
        int choice = 0;
        do {
            System.out.println("Add a showtime ...");
            /*
             * get Movie Id
             */
            list = MovieController.getMovieList();
            MoviePrinter.getInstance().printList(list);
            System.out.print("Enter movie Id to add showtime, enter 0 to cancel: ");
            int movieId = Integer.parseInt(sc.nextLine());
            if (movieId == 0) break;
            if (MovieController.getMovieById(movieId) == null) continue;
            /*
             * get cinema Id
             */
            list = CinemaController.getCinemaList();
            CinemaPrinter.getInstance().printList(list);
            System.out.print("Enter cinema Id to add showtime, enter 0 to cancel: ");            
            int cinemaId = Integer.parseInt(sc.nextLine());
            if (cinemaId == 0) break;
            if (CinemaController.getCinemaById(cinemaId) == null) continue;
            
            System.out.print("Enter time to show (with format YYYY-MM-DD hh:mm:ss): ");
            String time = sc.nextLine();
            ShowtimeController.addShowtime(time, movieId, cinemaId);
            System.out.println("Showtime added ...");
            System.out.println("1. Add another showtime");
            System.out.println("2. Go back to Staff Function page ...");
            System.out.println("Please choose your option: ");
            choice = Integer.parseInt(sc.nextLine());
        } while (choice != 2);        
    }
}
