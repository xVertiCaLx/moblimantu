/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import controller.BookingController;
import controller.CinemaController;
import controller.MovieController;
import controller.ShowtimeController;
import entity.Cinema;
import entity.Movie;
import entity.Showtime;
import factory.ShowtimeFactory;
import java.util.LinkedList;
import java.util.Scanner;
import printer.CinemaPrinter;
import printer.MoviePrinter;
import printer.ShowtimePrinter;
import utils.Common;
import utils.Constant;
import utils.References;

/**
 *
 * @author Khach
 */
public class EditShowtimePage {
    private static final EditShowtimePage INSTANCE = new EditShowtimePage();
    private Scanner sc;
    private EditShowtimePage(){}
    public static EditShowtimePage getInstance() {
        return INSTANCE;
    }
    
    private Showtime getShowtimeToEdit() {

        /*
         * get cinema
         */
        
        LinkedList<Cinema> cinemaList = CinemaController.getCinemaList();
        CinemaPrinter.getInstance().printList(cinemaList);
        
        System.out.print("Enter Cinema (1 - " + cinemaList.size() + ") to edit showtime, enter 0 to cancel: ");
        int index = Integer.parseInt(sc.nextLine());
        if (!(1 <= index && index <= cinemaList.size())) return null;
        Cinema cinema = cinemaList.get(index - 1);
        /*
         * get showtime list base on cinemaID
         */
        LinkedList<Showtime> showtimeList = ShowtimeController.getShowtimesByCinema(cinema.getId());
        do {
            ShowtimePrinter.getInstance().printList(showtimeList);
            System.out.print("Enter showtime (1 - " + showtimeList.size() + ") to edit showtime, enter 0 to cancel: ");            
            index = Integer.parseInt(sc.nextLine());
            if (!(1 <= index && index <= showtimeList.size())) break;

            Showtime showtime = showtimeList.get(index - 1);

            LinkedList bookings = BookingController.getBookingByShowtimeId(showtime.getId());
            if (bookings.size() != 0) {
                System.out.println("ERROR: This showtime has some booking already. Can not be edited. Try edit another showtime");
                continue;
            } else return showtime;
        }   while (true);
        return null;
    }
        
    public void launch() {
        sc = References.getInputStream();
        int choice = 0;
        do {
            System.out.println("===Edit/Update a showtime===");            
            Showtime showtime = getShowtimeToEdit();
            if (showtime == null) break;
            ShowtimePrinter.getInstance().printInstance(showtime);
            Showtime newShowtime = null;
            System.out.print("Enter field (" + ShowtimePrinter.SHOWTIME_TIME + " - " + ShowtimePrinter.MOVIE
                            + ") to edit, enter -1 to delete showtime, enter 0 to cancel: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case-1: ShowtimeController.deleteShowtime(showtime.getId()); 
                        break;
                case ShowtimePrinter.SHOWTIME_TIME: 
                        newShowtime = editTime(showtime);
                        break;
                case ShowtimePrinter.CINEMA: 
                        newShowtime = editCinema(showtime);
                        break;
                case ShowtimePrinter.MOVIE:
                        newShowtime = editMovie(showtime);
            }
            if (ShowtimePrinter.SHOWTIME_TIME <= choice && choice <= ShowtimePrinter.MOVIE) {
                ShowtimeController.editShowtime(showtime.getId(), newShowtime);
                System.out.println("Showtime editted");
            }
        } while (choice != 0);        
    }
    
    private Showtime editTime(Showtime showtime) {
        System.out.print("New time (format YYYY-MM-DD hh:mm:ss): ");
        String newTime = sc.nextLine();
        return ShowtimeFactory.createNewInstance(newTime, showtime.getMovieId(), showtime.getCinemaId());        
    }
    
    private Showtime editCinema(Showtime showtime) {
        LinkedList<Cinema> cinemaList = CinemaController.getCinemaList();
        CinemaPrinter.getInstance().printList(cinemaList);
        System.out.print("Choose new cinema (1 - " + cinemaList.size() + ") for this showtime: ");
        int index = Integer.parseInt(sc.nextLine());
        Cinema cinema = cinemaList.get(index - 1);
        return ShowtimeFactory.createNewInstance(showtime.getTime(), showtime.getMovieId(), cinema.getId());        
    }
    
    private Showtime editMovie(Showtime showtime) {
        LinkedList<Movie> movieList = MovieController.getMoviesByStatus(Constant.MOVIE_STATUS_NOW_SHOWING);
        MoviePrinter.getInstance().printList(movieList);
        System.out.print("Choose new movie (1 - " + movieList.size() + ") for this showtime: ");
        int index = Integer.parseInt(sc.nextLine());
        Movie movie = movieList.get(index - 1);
        return ShowtimeFactory.createNewInstance(showtime.getTime(), movie.getId(), showtime.getCinemaId());
    }
    
    //Unit test 
    public static void main(String[] args) {
        Common.initDB();
        int showtimeId = 1;
        Showtime st = ShowtimeController.getShowtimeById(showtimeId);
        ShowtimePrinter.getInstance().printInstance(st);
        
        //edit showtime
        getInstance().launch();
        
        st = ShowtimeController.getShowtimeById(showtimeId);
        ShowtimePrinter.getInstance().printInstance(st);
        
    }
}
