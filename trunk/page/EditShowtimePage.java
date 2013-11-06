/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import controller.BookingController;
import controller.CinemaController;
import controller.ShowtimeController;
import entity.Cinema;
import entity.Showtime;
import factory.ShowtimeFactory;
import java.util.LinkedList;
import java.util.Scanner;
import printer.CinemaPrinter;
import printer.ShowtimePrinter;
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
         * get cinema Id
         */
        
        LinkedList<Cinema> cinemaList = CinemaController.getCinemaList();
        CinemaPrinter.getInstance().printList(cinemaList);
        
        System.out.print("Enter Cinema Id to edit showtime, enter 0 to cancel: ");
        int cinemaId = Integer.parseInt(sc.nextLine());
        if (cinemaId == 0 || CinemaController.getCinemaById(cinemaId) == null) return null;
        /*
         * get showtime list base on cinemaID
         */
        LinkedList<Showtime> showtimeList = ShowtimeController.getShowtimesByCinema(cinemaId);
        do {
            ShowtimePrinter.getInstance().printList(showtimeList);
            System.out.print("Enter showtime Id to edit showtime, enter 0 to cancel: ");            

            int showtimeId = Integer.parseInt(sc.nextLine());
            if (showtimeId == 0) break;

            Showtime showtime = ShowtimeController.getShowtimeById(showtimeId);
            if (showtime == null) continue;

            LinkedList bookings = BookingController.getBookingByShowtimeId(showtimeId);
            if (bookings.size() != 0) {
                System.out.println("This showtime has some booking already. Can not be edited. Try edit another showtime");
                continue;
            }
        }   while (true);
        return null;
    }
        
    public void launch() {
        sc = References.getInputStream();
        int choice = 0;
        do {
            System.out.println("Edit/Update a showtime ...");            
            Showtime showtime = getShowtimeToEdit();
            ShowtimePrinter.getInstance().printInstance(showtime);
            Showtime newShowtime = null;
            System.out.print("Enter field (2 - 4) to edit, enter -1 to delete showtime, enter 0 to cancel: ");
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
            if (2 <= choice && choice <= 4) {
                ShowtimeController.editShowtime(showtime.getId(), newShowtime);
            }
        } while (choice != 0);        
    }
    
    private Showtime editTime(Showtime showtime) {
        System.out.print("New time (format YYYY-MM-DD hh:mm:ss): ");
        String newTime = sc.nextLine();
        return ShowtimeFactory.createNewInstance(newTime, showtime.getCinemaId(), showtime.getMovieId());        
    }
    
    private Showtime editCinema(Showtime showtime) {
        System.out.print("New cinema ID: ");
        int newCinemaId = Integer.parseInt(sc.nextLine());
        return ShowtimeFactory.createNewInstance(showtime.getTime(), showtime.getMovieId(), newCinemaId);        
    }
    
    private Showtime editMovie(Showtime showtime) {
        System.out.print("New movie ID: ");
        int newMovieId = Integer.parseInt(sc.nextLine());
        return ShowtimeFactory.createNewInstance(showtime.getTime(), newMovieId, showtime.getCinemaId());
    }
}
