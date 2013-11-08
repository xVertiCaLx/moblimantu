/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import controller.BookingController;
import controller.CinemaController;
import controller.CineplexController;
import controller.MovieController;
import entity.Booking;
import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import helper.DateHelper;
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
    private void printTitle(int month, int year) {
        System.out.println(" =======================================================");
        System.out.println("|                 REVENUE REPORT\t\t\t|");
        System.out.println("|             Month: " + DateHelper.month[month] + "     Year: " + year + "\t\t\t|" );
        System.out.println(" =======================================================");        
    }
    private void reportByMovie() {
        int month, year;
        System.out.print("Enter the month (0-Jan,1-Feb,...11-Dec): "); month = Integer.parseInt(sc.nextLine());
        System.out.print("Enter the year (YYYY): "); year = Integer.parseInt(sc.nextLine());
        printTitle(month, year);
        System.out.println();
        /* get movie list */
        LinkedList<Movie> movieList = MovieController.getMovieList();
        /* get cineplex list */
        LinkedList<Cineplex> cineplexList = CineplexController.getCineplexList();
        for(Movie m : movieList) {
            System.out.println("Movie " + m.getName() + " statistics:");
            double totalIncome = 0;
            for(Cineplex cpl : cineplexList) {
                double cineplexIncome = 0;
                LinkedList<Cinema> cinemaList = CinemaController.getCinemasByCineplexId(cpl.getId());
                for(Cinema c : cinemaList) {
                    cineplexIncome += BookingController.getIncomeByMovieAndCinema(m.getId(), c.getId());
                }
                if (cineplexIncome > 0) {
                    System.out.println("\tCineplex: " + cpl.getName() + "\t\tINCOME = " + cineplexIncome);
                    totalIncome += cineplexIncome;
                }
            }
            System.out.println("\tTOTAL INCOME = " + totalIncome);
            System.out.println();
        }
        /*
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
        */
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
