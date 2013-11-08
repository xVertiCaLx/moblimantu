/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import controller.BookingController;
import controller.MovieController;
import controller.ShowtimeController;
import entity.Booking;
import entity.Movie;
import entity.Showtime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import utils.Common;
import utils.References;

/**
 *
 * @author Khach
 */
public class PrintSaleRevenueReportPage {
    private static final PrintSaleRevenueReportPage INSTANCE = new PrintSaleRevenueReportPage();
    private Scanner sc;
    private PrintSaleRevenueReportPage(){}
    public static PrintSaleRevenueReportPage getInstance() {
        return INSTANCE;
    }
    public void launch() {
        sc = References.getInputStream();
        int choice = 0;
        do {
            System.out.println("===Printe Sale Revenue Report page===");
            System.out.println("1. Sale Revenue Report by movie");
            System.out.println("2. Sale Revenue Report by cineplex");            
            System.out.println("3. Sale Revenue Report by day");
            System.out.println("4. Sale Revenue Report by month");
            System.out.println("5. Go back to Staff Function page");
            System.out.print("Please choose your option: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: reportByMovie();
                        break;
                case 2: reportByCineplex();
                        break;
                case 3: reportByDay();
                        break;
                case 4: reportByMonth();
                        break;
            }            
        } while (choice != 5);
    }
    
    private LinkedList<Booking> getBookingInDefaultDateRange() {
        return BookingController.getBookingInCurrentMonth();
    }
    private void reportByMovie() {
        System.out.println("Revenue report by Movie");
        LinkedList<Booking> bookingList = getBookingInDefaultDateRange();
        LinkedList<Movie> movieList = new LinkedList();
        HashMap<Integer, Double> totalPrice = new HashMap();
        double sum = 0;
        for (Booking booking: bookingList) {
            Showtime st = ShowtimeController.getShowtimeById(booking.getShowtimeId());
            sum += booking.getPrice();
            Movie mv = MovieController.getMovieById(st.getMovieId());
            if (!totalPrice.containsKey(mv.getId())) {
                totalPrice.put(mv.getId(), totalPrice.get(mv.getId()) + booking.getPrice());
            }
        }
        int index = 0;
        for (int movieId: totalPrice.keySet()) {
            index++;
            Movie mv = MovieController.getMovieById(movieId);
            System.out.println(index + ". " + mv.getName() + " - ID: " + mv.getId());
            System.out.println("Price taking in current month: SGD $" + totalPrice.get(movieId));
        }
        System.out.println("Total taking in current month: " + sum);
    }
    
    private void reportByCineplex() {
        System.out.println("Revenue report by Cineplex");
        LinkedList<Booking> bookingList = getBookingInDefaultDateRange();
        for (Booking booking: bookingList) {
            
        }
    }
    private void reportByDay() {
        System.out.println("Revenue report by Day");
    }
    private void reportByMonth() {
        System.out.println("Revenue report by Month");
    }
    
    public static void main(String[] args) {
        Common.initDB();
        getInstance().launch();
    }
}
