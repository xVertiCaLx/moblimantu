package main;

import controller.StaffController;
import db.BookingDB;
import db.MovieDB;
import db.CinemaDB;
import db.CineplexDB;
import db.StaffDB;
import entity.Booking;
import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import entity.Staff;
import java.util.Scanner;
import page.MainPage;
import utils.Common;
import utils.Constant;
import utils.References;

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
    public static void main(String[] args) {
        // TODO code application logic here
        int choice = 1;
        if (choice == 0)
            unitTestDB();
        else {
            Common.initDB();
            MainPage.getInstance().launch();
        }
    }
}
