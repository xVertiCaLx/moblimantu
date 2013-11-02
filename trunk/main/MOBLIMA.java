/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import db.BookingDB;
import db.MovieDB;
import db.CinemaDB;
import db.CineplexDB;
import db.ShowtimeDB;
import entity.Booking;
import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import entity.Showtime;
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
        MovieDB.loadDB(Constant.database_path + Constant.movie_database);
        for(Movie m : MovieDB.list) {
            System.out.println(m.getId());
            System.out.println(m.getName());
            System.out.println(m.getStatus());
            System.out.println(m.getType());
            System.out.println(m.getRating());
        }
        
        System.out.println("UNIT TEST FOR CINEMA DB");
        CinemaDB.loadDB(Constant.database_path + Constant.cinema_database);
        for(Cinema c : CinemaDB.list) {
            System.out.println(c.getId());
            System.out.println(c.getCinemaClass());
            System.out.println(c.getName());
            System.out.println(c.getCineplexId());
            System.out.println(c.getCinemaCode());
        }
        
        System.out.println("UNIT TEST FOR CINEPLEX DB");
        CineplexDB.loadDB(Constant.database_path + Constant.cineplex_database);
        for(Cineplex c : CineplexDB.list) {
            System.out.println(c.getId());
            System.out.println(c.getName());
        }
        System.out.println("UNIT TEST FOR SHOWTIME DB");
        ShowtimeDB.loadDB(Constant.database_path + Constant.showtime_database);
        for(Showtime s : ShowtimeDB.list) {
            System.out.println(s.getId());
            System.out.println(s.getTime());
            System.out.println(s.getMovieId());
            System.out.println(s.getCinemaId());
        }
        System.out.println("UNIT TEST FOR BOOKING DB");
        BookingDB.loadDB(Constant.database_path + Constant.booking_database);
        for(Booking b : BookingDB.list) {
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
    }
    public static void initDB() {
        MovieDB.loadDB(Constant.database_path + Constant.movie_database);
        CinemaDB.loadDB(Constant.database_path + Constant.cinema_database);
        CineplexDB.loadDB(Constant.database_path + Constant.cineplex_database);
        ShowtimeDB.loadDB(Constant.database_path + Constant.showtime_database);
        BookingDB.loadDB(Constant.database_path + Constant.booking_database);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        //unitTestDB();
        initDB();
        //Scanner scanner = new Scanner(System.in);
       // References.setInputStream(scanner);
       // MainPage.getInstance().launch();
    }
}
