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
import entity.Showtime;
import java.util.LinkedList;
import java.util.Scanner;
import utils.Common;
import utils.References;

/**
 *
 * @author Vu
 */
public class ShowtimeByMovieSubPage {
    private static final ShowtimeByMovieSubPage INSTANCE = new ShowtimeByMovieSubPage();
    
    private ShowtimeByMovieSubPage(){}
    public static ShowtimeByMovieSubPage getInstance() {
        return INSTANCE;
    }
    
    public void launch(int movieId) {
        Scanner sc = References.getInputStream();
        Movie movie = MovieController.getMovieById(movieId);
        LinkedList<Cinema> cinemaList = CinemaController.getCinemasByMovie(movieId);
        System.out.println("\t--------SHOWING TIME-------");
        System.out.println("\t\tMovie: " + movie.getName());
        System.out.println();
        if (cinemaList.size() == 0) {
            System.out.println("Sorry. There is no show time for this movie");
            System.out.println();
        }
        for(Cinema c : cinemaList) {
            System.out.println("Cinema: " + c.getName());
            LinkedList<Showtime> showtimeList = ShowtimeController.getShowtimesByMovieAndCinema(movieId, c.getId());
            for(Showtime st : showtimeList) {
                System.out.println(st.getId() + ". Showing time: " + st.getTimeStringFormat());
            }
            System.out.println();
        }
        if (cinemaList.size() > 0) {
            System.out.print("Choose a showtime to proceed on booking (0 to go back): ");
            int showtimeId = Integer.parseInt(sc.nextLine());
        }
    }
    //unit testing
    public static void main(String args[]) {
        Common.initDB();
        getInstance().launch(1);
    }
}
