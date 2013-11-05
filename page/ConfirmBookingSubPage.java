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
import java.util.LinkedList;
import java.util.Scanner;
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
    
    public boolean getConfirmation(Booking b) {
        Showtime st = ShowtimeController.getShowtimeById(b.getShowtimeId());
        Cinema c = CinemaController.getCinemaById(st.getCinemaId());
        Cineplex cx = CineplexController.getCineplexById(c.getCineplexId());
        Movie m = MovieController.getMovieById(st.getMovieId());
        
        System.out.println("=========================================");
        System.out.println("BOOKING INVOICE ");
        System.out.println("Booking Id: " + b.getId());
        System.out.println("Customer: " + b.getCustomerName());
        System.out.println("Email: " + b.getCustomerEmail());
        System.out.println("Movie name: " + m.getName());
        System.out.println("Cineplex: " + cx.getName());
        System.out.println("Cinema: " + c.getName());
        System.out.println("Time: " + st.getTimeStringFormat());
        System.out.print("Seat: ");
        LinkedList<Integer> seats = b.getSeatNumbers();
        boolean firstSeat = true;
        for(Integer seat : seats) {
            if (firstSeat) System.out.print(seat.toString());
            else System.out.print("," + seat.toString());
            firstSeat = false;
        }
        System.out.println();
        System.out.println("Price: " + b.getPrice() + " SGD");
        System.out.println("=========================================");
        
        System.out.print("Confirm your Booking (y/n)? ");
        Scanner sc = References.getInputStream();    
        String choice = sc.nextLine();
        if (choice.compareTo("y") == 0) return true;
        else return false;
    }
}
