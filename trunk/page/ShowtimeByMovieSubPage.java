/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import controller.BookingController;
import controller.CinemaController;
import controller.MovieController;
import controller.SeatLayoutController;
import controller.ShowtimeController;
import entity.Cinema;
import entity.Movie;
import entity.SeatLayout;
import entity.Showtime;
import helper.DateHelper;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
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
                System.out.println(st.getId() + ". Showing time: " + DateHelper.getTimeStringFormat(st.getTime()));
            }
            System.out.println();
        }
        if (cinemaList.size() > 0) {
            System.out.print("Choose a showtime to proceed on booking (0 to go back): ");
            int showtimeId = Integer.parseInt(sc.nextLine());
            if (showtimeId == 0) return;
            System.out.println();
            if (showtimeId == 0) return;
            launchSeatSelection(showtimeId);
        }
    }
    
    private void launchSeatSelection(int showtimeId) {
        Scanner sc = References.getInputStream();
        Showtime st = ShowtimeController.getShowtimeById(showtimeId);
        Cinema c = CinemaController.getCinemaById(st.getCinemaId());
        Movie m = MovieController.getMovieById(st.getMovieId());
        SeatLayout sl = SeatLayoutController.getSeatLayoutById(st.getSeatLayoutId());
        if (sl.isFullyBooked()) {
            System.out.println("ERROR: Sorry, there is no more seat available for this showtime.");
            System.out.println();
            return;
        }
        System.out.println("\tBOOKING YOUR SEATS");
        System.out.println("================================");
        System.out.println("Movie: " + m.getName());
        System.out.println("Cinema: " + c.getName());
        System.out.println("Time: " + DateHelper.getTimeStringFormat(st.getTime()));
        System.out.println("Seat Layout: ");
        sl.display();
        String customerName, customerEmail, customerHP;
        int customerAge;
        System.out.print("Name: \t\t");         customerName = sc.nextLine();
        System.out.print("Email: \t\t");        customerEmail = sc.nextLine();
        System.out.print("Handphone No: \t");   customerHP = sc.nextLine();
        System.out.print("Age: \t\t");            customerAge = Integer.parseInt(sc.nextLine());
        while (true) {
            System.out.print("Seats (e.g: '4,5'): \t");
            StringTokenizer s = new StringTokenizer(sc.nextLine(),",");
            LinkedList<Integer> seats = new LinkedList<Integer>();
            while (s.hasMoreTokens()) {
                seats.add(new Integer(s.nextToken()));
            }
            if (checkAvailableSeat(seats, st) == true) {
                System.out.println();
                BookingController.makeBooking(showtimeId, customerName, customerHP, customerEmail, customerAge, seats);
                break;
            } else {
                System.out.println("Selected seats are not available");
                System.out.println();
            }
        }
        
    }
    
    public static boolean checkAvailableSeat(LinkedList<Integer> bookedSeatNumbers, Showtime st) {
        SeatLayout sl = SeatLayoutController.getSeatLayoutById(st.getSeatLayoutId());
        for(Integer seat : bookedSeatNumbers) {
            int row = sl.getRow(seat.intValue());
            int col = sl.getCol(seat.intValue());
            if (sl.isSeatAvailable(row, col) == false) {
                return false;
            }
        }
        return true;
    }
    
    //unit testing
    public static void main(String args[]) {
        Common.initDB();
        getInstance().launch(1);
    }
}
