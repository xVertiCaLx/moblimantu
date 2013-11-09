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
import java.util.Calendar;
import java.util.GregorianCalendar;
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
        System.out.println("|             Month: " + DateHelper.MONTH[month] + "     Year: " + year + "\t\t\t|" );
        System.out.println(" =======================================================");        
    }
    private void printTitle(int year) {
        System.out.println(" =======================================================");
        System.out.println("|                 REVENUE REPORT\t\t\t|");
        System.out.println("|                   Year: " + year + "\t\t\t\t|" );
        System.out.println(" =======================================================");        
    }
    private int getMonth() {
        System.out.print("Enter the month (0-Jan,1-Feb,...11-Dec): "); 
        return Integer.parseInt(sc.nextLine());        
    }
    private int getYear() {
        System.out.print("Enter the year (YYYY): "); 
        return Integer.parseInt(sc.nextLine());        
    }
    
    private void reportByMovie() {
        int month, year;
        month = getMonth();
        year = getYear();
        printTitle(month, year);
        System.out.println();
        /* get movie list */
        LinkedList<Movie> movieList = MovieController.getMovieList();
        /* get cineplex list */
        LinkedList<Cineplex> cineplexList = CineplexController.getCineplexList();
        double totalRevenue = 0;
        for(Movie m : movieList) {
            System.out.println("Movie " + m.getName() + " statistics:");
            double totalIncome = 0;
            for(Cineplex cpl : cineplexList) {
                double cineplexIncome = 0;
                LinkedList<Cinema> cinemaList = CinemaController.getCinemasByCineplexId(cpl.getId());
                for(Cinema c : cinemaList) {
                    cineplexIncome += BookingController.getIncomeByMovieAndCinemaAndYearAndMonth(m.getId(), c.getId(), year, month);
                }
                if (cineplexIncome > 0) {
                    System.out.println("\tCineplex: " + cpl.getName() + "\t\tINCOME = " + cineplexIncome);
                    totalIncome += cineplexIncome;
                }
            }
            System.out.println("\tTOTAL INCOME = " + totalIncome);
            System.out.println();
            totalRevenue += totalIncome;
        }
        System.out.println("TOTAL REVENUE = " + totalRevenue);
        System.out.println();
    }
    
    private void reportByCineplex() {
        int month, year;
        month = getMonth();
        year = getYear();
        printTitle(month, year);
        System.out.println();
        /* get movie list */
        LinkedList<Movie> movieList = MovieController.getMovieList();
        /* get cineplex list */
        LinkedList<Cineplex> cineplexList = CineplexController.getCineplexList();
        
        double totalRevenue = 0;
        for(Cineplex cpl : cineplexList) {
            System.out.println("Cineplex " + cpl.getName() + " statistics:");
            double totalIncome = 0;
            for(Movie mv : movieList) {
                double movieIncome = 0;
                LinkedList<Cinema> cinemaList = CinemaController.getCinemasByCineplexId(cpl.getId());
                for(Cinema c : cinemaList) {
                    movieIncome += BookingController.getIncomeByMovieAndCinemaAndYearAndMonth(mv.getId(), c.getId(), year, month);
                }
                if (movieIncome > 0) {
                    System.out.println("\tMovie: " + mv.getName() + "\t\tINCOME = " + movieIncome);
                    totalIncome += movieIncome;
                }
            }
            System.out.println("\tTOTAL INCOME = " + totalIncome);
            System.out.println();
        }
        System.out.println("TOTAL REVENUE = " + totalRevenue);
        System.out.println();
    }
    private void reportByDay() {
        int month, year;
        month = getMonth();
        year = getYear();
        printTitle(month, year);
        
        Calendar mycal = new GregorianCalendar(1999, Calendar.FEBRUARY, 1);
        // Get the number of days in that month
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); 
        /* get movie list */
        LinkedList<Movie> movieList = MovieController.getMovieList();
        /* get cineplex list */
        LinkedList<Cineplex> cineplexList = CineplexController.getCineplexList();
        
        double totalRevenue = 0;
        for(int day = 1; day <= daysInMonth; day++) {
            double totalIncome = 0;
            System.out.println("Date: " + year + "-" + DateHelper.MONTH[month] + "-" + day);
            for(Cineplex cpl : cineplexList) {
                double totalIncomeCineplex = 0;
                for(Movie mv : movieList) {
                    double movieIncome = 0;
                    LinkedList<Cinema> cinemaList = CinemaController.getCinemasByCineplexId(cpl.getId());
                    for(Cinema c : cinemaList) {
                        movieIncome += BookingController.getIncomeByMovieAndCinemaAndYearAndMonthAndDay(mv.getId(), c.getId(), year, month, day);
                    }
                    if (movieIncome > 0) {
                        if (totalIncomeCineplex == 0)
                        System.out.println("\tCineplex " + cpl.getName() + " statistics:");
                        System.out.println("\t\tMovie: " + mv.getName() + "\t\tINCOME = " + movieIncome);
                        totalIncomeCineplex += movieIncome;
                    }
                }
                if (totalIncomeCineplex > 0) {
                    System.out.println("\t\tCINEPLEX INCOME = " + totalIncomeCineplex);
                    totalIncome += totalIncomeCineplex;
                    System.out.println();
                }
            }
            System.out.println("\tTOTAL INCOME = " + totalIncome);
            totalRevenue += totalIncome;
        }
        System.out.println("TOTAL REVENUE = " + totalRevenue);
        System.out.println();
    }
    private void reportByMonth() {
        int year;
        year = getYear();
        printTitle(year);
        /* get movie list */
        LinkedList<Movie> movieList = MovieController.getMovieList();
        /* get cineplex list */
        LinkedList<Cineplex> cineplexList = CineplexController.getCineplexList();
        
        double totalRevenue = 0;
        for(int month = 0; month <= 11; month++) {
            double totalIncome = 0;
            System.out.println("Month: " + year + "-" + DateHelper.MONTH[month]);
            for(Cineplex cpl : cineplexList) {
                double totalIncomeCineplex = 0;
                for(Movie mv : movieList) {
                    double movieIncome = 0;
                    LinkedList<Cinema> cinemaList = CinemaController.getCinemasByCineplexId(cpl.getId());
                    for(Cinema c : cinemaList) {
                        movieIncome += BookingController.getIncomeByMovieAndCinemaAndYearAndMonth(mv.getId(), c.getId(), year, month);
                    }
                    if (movieIncome > 0) {
                        if (totalIncomeCineplex == 0)
                        System.out.println("\tCineplex " + cpl.getName() + " statistics:");
                        System.out.println("\t\tMovie: " + mv.getName() + "\t\tINCOME = " + movieIncome);
                        totalIncomeCineplex += movieIncome;
                    }
                }
                if (totalIncomeCineplex > 0) {
                    System.out.println("\t\tCINEPLEX INCOME = " + totalIncomeCineplex);
                    totalIncome += totalIncomeCineplex;
                    System.out.println();
                }
            }
            System.out.println("\tTOTAL INCOME = " + totalIncome);
            totalRevenue += totalIncome;
        }
        
        System.out.println();
        System.out.println("TOTAL REVENUE = " + totalRevenue);
        System.out.println();
        
    }
    
    public static void main(String[] args) {
        Common.initDB();
        getInstance().launch();
    }
}
