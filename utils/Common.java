package utils;

import helper.DateHelper;
import db.BookingDB;
import db.CinemaDB;
import db.CineplexDB;
import db.MovieDB;
import db.SeatLayoutDB;
import db.ShowtimeDB;
import db.StaffDB;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Common {
    public static int maxMovieId;
    public static int maxCinemaId;
    public static int maxCineplexId;
    public static int maxShowtimeId;
    public static int maxBookingId;
    public static int maxSeatLayoutId;
    
    public static void initDB() {
        MovieDB.loadDB(Constant.DATABASE_PATH + Constant.MOVIE_DATABASE);
        CinemaDB.loadDB(Constant.DATABASE_PATH + Constant.CINEMA_DATABASE);
        CineplexDB.loadDB(Constant.DATABASE_PATH + Constant.CINEPLEX_DATABASE);
        ShowtimeDB.loadDB(Constant.DATABASE_PATH + Constant.SHOWTIME_DATABASE);
        BookingDB.loadDB(Constant.DATABASE_PATH + Constant.BOOKING_DATABASE);
        StaffDB.loadDB(Constant.DATABASE_PATH + Constant.STAFF_DATABASE);
        DateHelper.initPublicHolidays();
        try {
            Scanner sc = new Scanner(new File(Constant.DATABASE_PATH + Constant.ID_DATABASE));
            maxMovieId = sc.nextInt();
            maxCinemaId = sc.nextInt();
            maxCineplexId = sc.nextInt();
            maxShowtimeId = sc.nextInt();
            maxBookingId = sc.nextInt();
            maxSeatLayoutId = sc.nextInt();
        } catch(IOException e) {
            System.out.println("IOException at initDB " + e.getMessage());
        }
    }
    public static void commit() {
        try {
            //update id database
            PrintWriter pw = new PrintWriter(new File(Constant.DATABASE_PATH + Constant.ID_DATABASE));
            pw.write(new Integer(maxMovieId).toString()); pw.write("\r\n");
            pw.write(new Integer(maxCinemaId).toString()); pw.write("\r\n");
            pw.write(new Integer(maxCineplexId).toString()); pw.write("\r\n");
            pw.write(new Integer(maxShowtimeId).toString()); pw.write("\r\n");
            pw.write(new Integer(maxBookingId).toString()); pw.write("\r\n");
            pw.write(new Integer(maxSeatLayoutId).toString()); pw.write("\r\n");
            
            //Write out the documentation
            pw.write("maximum id of all entities:"); pw.write("\r\n");
            pw.write("movie"); pw.write("\r\n");
            pw.write("cinema"); pw.write("\r\n");
            pw.write("cineplex"); pw.write("\r\n");
            pw.write("showtime"); pw.write("\r\n");
            pw.write("booking id"); pw.write("\r\n");
            pw.write("seatlayout"); pw.write("\r\n");
            pw.close();
        } catch(IOException e) {
            System.out.println("IOException at Common.commit()" + e.getMessage());
        }
    }
    //create unique id for movie
    public static int genMovieId() {
        maxMovieId++;
        commit();
        return maxMovieId;
    }
    //create unique id for cinema
    public static int genCinemaId() {
        maxCinemaId++;
        commit();
        return maxCinemaId;
    }
    //create unique id for cineplex
    public static int genCineplexId() {
        maxCineplexId++;
        commit();
        return maxCineplexId;
    }
    //create unique id for showtime
    public static int genShowtimeId() {
        maxShowtimeId++;
        commit();
        return maxShowtimeId;
    }
    //create unique id for booking
    public static int genBookingId() {
        maxBookingId++;
        commit();
        return maxBookingId;
    }
    //create unique id for seat layout
    public static int genSeatLayoutId() {
        maxSeatLayoutId++;
        commit();
        return maxSeatLayoutId;
    }
}

