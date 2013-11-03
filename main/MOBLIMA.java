/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.StaffController;
import db.BookingDB;
import db.MovieDB;
import db.CinemaDB;
import db.CineplexDB;
import db.ShowtimeDB;
import db.StaffDB;
import entity.Booking;
import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import entity.Showtime;
import entity.Staff;
import utils.Constant;
/**
 *
 * @author Vu
 */
public class MOBLIMA {

    /**
     * @param args the command line arguments
     */
    public static void unitTestDB() {
        System.out.println("UNIT TEST FOR MOVIEDB");
        MovieDB.loadDB(Constant.DATABASE_PATH + Constant.MOVIE_DATABASE);
        for(Movie m : MovieDB.getMovieList()) {
            System.out.println(m.getId());
            System.out.println(m.getName());
            System.out.println(m.getStatus());
            System.out.println(m.getType());
            System.out.println(m.getRating());
        }
        
        System.out.println("UNIT TEST FOR CINEMA DB");
        CinemaDB.loadDB(Constant.DATABASE_PATH + Constant.CINEMA_DATABASE);
        for(Cinema c : CinemaDB.getCinemaList()) {
            System.out.println(c.getId());
            System.out.println(c.getCinemaClass());
            System.out.println(c.getName());
            System.out.println(c.getCineplexId());
            System.out.println(c.getCinemaCode());
        }
        
        System.out.println("UNIT TEST FOR CINEPLEX DB");
        CineplexDB.loadDB(Constant.DATABASE_PATH + Constant.CINEPLEX_DATABASE);
        for(Cineplex c : CineplexDB.getCineplexList()) {
            System.out.println(c.getId());
            System.out.println(c.getName());
        }
        System.out.println("UNIT TEST FOR SHOWTIME DB");
        ShowtimeDB.loadDB(Constant.DATABASE_PATH + Constant.SHOWTIME_DATABASE);
        for(Showtime s : ShowtimeDB.getShowtimeList()) {
            System.out.println(s.getId());
            System.out.println(s.getTime());
            System.out.println(s.getMovieId());
            System.out.println(s.getCinemaId());
        }
        System.out.println("UNIT TEST FOR BOOKING DB");
        BookingDB.loadDB(Constant.DATABASE_PATH + Constant.BOOKING_DATABASE);
        for(Booking b : BookingDB.getBookingList()) {
            System.out.println(b.getId());
            System.out.println(b.getTransactionId());
            System.out.println(b.getShowtimeId());
            System.out.println(b.getCustomerName());
            System.out.println(b.getCustomerHP());
            System.out.println(b.getCustomerEmail());
            System.out.println(b.getCustomerAge());
            System.out.println(b.getTime());
            System.out.println(b.getSeatNumbers());
            System.out.println(b.getPrice());
        }
        System.out.println("UNIT TEST FOR STAFF DB");
        StaffDB.loadDB(Constant.DATABASE_PATH + Constant.STAFF_DATABASE);
        for(Staff s : StaffDB.getStaffList()) {
            System.out.println(s.getUsername());
            System.out.println(s.getPassword());
            System.out.println(StaffController.authenticate(s.getUsername(),"password"));
        }
    }
    public static void initDB() {
        MovieDB.loadDB(Constant.DATABASE_PATH + Constant.MOVIE_DATABASE);
        CinemaDB.loadDB(Constant.DATABASE_PATH + Constant.CINEMA_DATABASE);
        CineplexDB.loadDB(Constant.DATABASE_PATH + Constant.CINEPLEX_DATABASE);
        ShowtimeDB.loadDB(Constant.DATABASE_PATH + Constant.SHOWTIME_DATABASE);
        BookingDB.loadDB(Constant.DATABASE_PATH + Constant.BOOKING_DATABASE);
        StaffDB.loadDB(Constant.DATABASE_PATH + Constant.STAFF_DATABASE);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        unitTestDB();
        //initDB();
    //    Scanner scanner = new Scanner(System.in);
//        References.setInputStream(scanner);
  //      MainPage.getInstance().launch();
    }
}
