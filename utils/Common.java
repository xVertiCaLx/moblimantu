/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import db.BookingDB;
import db.CinemaDB;
import db.CineplexDB;
import db.MovieDB;
import db.SeatLayoutDB;
import db.ShowtimeDB;
import db.StaffDB;

/**
 *
 * @author Vu
 */
public class Common {
    public static void initDB() {
        MovieDB.loadDB(Constant.DATABASE_PATH + Constant.MOVIE_DATABASE);
        CinemaDB.loadDB(Constant.DATABASE_PATH + Constant.CINEMA_DATABASE);
        CineplexDB.loadDB(Constant.DATABASE_PATH + Constant.CINEPLEX_DATABASE);
        ShowtimeDB.loadDB(Constant.DATABASE_PATH + Constant.SHOWTIME_DATABASE);
        BookingDB.loadDB(Constant.DATABASE_PATH + Constant.BOOKING_DATABASE);
        StaffDB.loadDB(Constant.DATABASE_PATH + Constant.STAFF_DATABASE);
        SeatLayoutDB.loadDB(Constant.DATABASE_PATH + Constant.SEAT_LAYOUT_DATABASE);
    }
}
