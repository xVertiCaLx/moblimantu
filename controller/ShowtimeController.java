/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.ShowtimeDB;
import entity.Showtime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Constant;

/**
 *
 * @author Vu
 */
public class ShowtimeController {
       /* Add a Showtime  to the database & database */
    public static void addShowtime(Date showtimeTime, int showtimeMovieId, int showtimeCinemaId){
        LinkedList<Showtime> list = ShowtimeDB.getShowtimeList();
        Showtime s = new Showtime(list.size()+1, showtimeTime, showtimeMovieId, showtimeCinemaId);
        list.add(s);
        ShowtimeDB.commit();
    }
    
    /* Add a Showtime to the database & database */
    public static void addShowtime(String showtimeTimeStringFormat, int showtimeMovieId, int showtimeCinemaId){
        try {
            LinkedList<Showtime> list = ShowtimeDB.getShowtimeList();
            Date showtimeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(showtimeTimeStringFormat);                
            Showtime s = new Showtime(list.size()+1, showtimeTime, showtimeMovieId, showtimeCinemaId);
            list.add(s);
            ShowtimeDB.commit();
        } catch (ParseException ex) {
            System.out.println("Parse Exception at addShowtime" + ex.getMessage());
            Logger.getLogger(ShowtimeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* Edit a specific Showtime */
    public static void editShowtime(int showtimeId, Showtime newShowtime) {
        LinkedList<Showtime> list = ShowtimeDB.getShowtimeList();
        for(Showtime st : list) {
            if (st.getId() == showtimeId) {
                st.setCinemaId(newShowtime.getCinemaId());
                st.setMovieId(newShowtime.getMovieId());
                st.setTime(newShowtime.getTime());
            }
        }
        ShowtimeDB.commit();
    }

    /* Return a specific showtime with given id */
    public static Showtime getShowtimeById(int id) {
        LinkedList<Showtime> list = ShowtimeDB.getShowtimeList();
        for(Showtime s : list) {
            if (s.getId() == id) return s;
        }
        return null;
    }
    
    /* Return list of Showtimes with given movie_id*/
    public static LinkedList<Showtime> getShowtimesByMovie(int movieId) {
        LinkedList<Showtime> result = new LinkedList<Showtime>();
        LinkedList<Showtime> list = ShowtimeDB.getShowtimeList();
        for(Showtime s : list) {
            if (s.getMovieId() == movieId) result.add(s);
        }
        return result;
    }
    
    /* Return list of Showtimes with given cinema_id */
    public static LinkedList<Showtime> getShowtimesByCinema(int cinemaId) {
        LinkedList<Showtime> result = new LinkedList<Showtime>();
        LinkedList<Showtime> list = ShowtimeDB.getShowtimeList();
        for(Showtime s : list) {
            if (s.getCinemaId() == cinemaId) {
                result.add(s);
            }
        }
        return result;
    }
 
    /* Return list of Showtimes with given cinemaId and movie Id */
    public static LinkedList<Showtime> getShowtimesByCinemaAndMovie(int movieId, int cinemaId) {
        LinkedList<Showtime> result = new LinkedList<Showtime>();
        LinkedList<Showtime> list = ShowtimeDB.getShowtimeList();
        for(Showtime s : list) {
            if (s.getCinemaId() == cinemaId && s.getMovieId() == movieId) {
                result.add(s);
            }
        }
        return result;
    }
    /* Unit Test part */
    public static void main(String[] args) {
        ShowtimeDB.loadDB(Constant.DATABASE_PATH + Constant.SHOWTIME_DATABASE);
        Date x = new Date();
        addShowtime("2013-12-28 12:13:12",2,3);
    }
}
