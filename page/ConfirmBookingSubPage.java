/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import controller.CinemaController;
import controller.CineplexController;
import controller.MovieController;
import controller.ShowtimeController;
import entity.Booking;
import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import entity.Showtime;
import java.util.Scanner;
import printer.BookingPrinter;
import utils.References;

/**
 *
 * @author Vu
 */
/* This page prints the invoice and ask for confirmation */
public class ConfirmBookingSubPage {
    private static final ConfirmBookingSubPage INSTANCE = new ConfirmBookingSubPage();
    
    private ConfirmBookingSubPage(){}
    public static ConfirmBookingSubPage getInstance() {
        return INSTANCE;
    }
    public static void showDisplay(Booking b) {
        BookingPrinter.getInstance().printInstance(b);
    }
    
    public boolean getConfirmation(Booking b) {
        Showtime st = ShowtimeController.getShowtimeById(b.getShowtimeId());
        Cinema c = CinemaController.getCinemaById(st.getCinemaId());
        Cineplex cx = CineplexController.getCineplexById(c.getCineplexId());
        Movie m = MovieController.getMovieById(st.getMovieId());
        
        showDisplay(b);
        
        System.out.print("Confirm your Booking (y/n)? ");
        Scanner sc = References.getInputStream();    
        String choice = sc.nextLine();
        if (choice.compareTo("y") == 0) return true;
        else return false;
    }
}
