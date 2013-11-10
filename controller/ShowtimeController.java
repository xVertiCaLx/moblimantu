package controller;

import db.ShowtimeDB;
import entity.Showtime;
import factory.ShowtimeFactory;
import java.util.Date;
import java.util.LinkedList;
import utils.Common;

public class ShowtimeController {
       /* Add a Showtime  to the database & database */
    public static void addShowtime(Date showtimeTime, int showtimeMovieId, int showtimeCinemaId){
        LinkedList<Showtime> list = ShowtimeDB.getShowtimeList();
        Showtime s = ShowtimeFactory.createNewInstance(showtimeTime, showtimeMovieId, showtimeCinemaId);
        list.add(s);
        ShowtimeDB.commit();
    }
    
    /* Add a Showtime to the database & database */
    public static void addShowtime(String showtimeTimeStringFormat, int showtimeMovieId, int showtimeCinemaId) {
        LinkedList<Showtime> list = ShowtimeDB.getShowtimeList();
        Showtime s = ShowtimeFactory.createNewInstance(showtimeTimeStringFormat, showtimeMovieId, showtimeCinemaId);
        list.add(s);
        ShowtimeDB.commit();
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
    public static LinkedList<Showtime> getShowtimesByMovieAndCinema(int movieId, int cinemaId) {
        LinkedList<Showtime> result = new LinkedList<Showtime>();
        LinkedList<Showtime> list = ShowtimeDB.getShowtimeList();
        for(Showtime s : list) {
            if (s.getCinemaId() == cinemaId && s.getMovieId() == movieId) {
                result.add(s);
            }
        }
        return result;
    }
    
    /* Get the Showtime list */
    public static LinkedList<Showtime> getShowtimeList() {
        return ShowtimeDB.getShowtimeList();
    }
    
    public static void deleteShowtime(int showtimeId) {
        LinkedList<Showtime> showtimeList = ShowtimeDB.getShowtimeList();
        for(Showtime st : showtimeList) 
        if (st.getId() == showtimeId) {
            showtimeList.remove(st);
        }
        commit();
    }
    /* Unit Test part */
    public static void main(String[] args) {
        Common.initDB();
        LinkedList<Showtime> showtimeList = getShowtimeList();
        for(Showtime st : showtimeList) {
            System.out.println(st.getId() + " " + st.getMovieId() + " " + st.getCinemaId());
        }
        showtimeList = getShowtimesByMovieAndCinema(1,1);
        for(Showtime st : showtimeList) {
            System.out.println(st.getId() + " " + st.getMovieId() + " " + st.getCinemaId());
        }
        
    }
}
