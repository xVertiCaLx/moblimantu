/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CinemaDB;
import entity.Cinema;
import entity.Showtime;
import factory.CinemaFactory;
import java.util.LinkedList;

/**
 *
 * @author Vu
 */
public class CinemaController {
    
    /* Add a Cinema to the database & database */
    public static void addCinema(String cinemaClass, String cinemaName, int cineplexId, String cinemaCode, int templateLayoutId){
        LinkedList<Cinema> list = CinemaDB.getCinemaList();
        Cinema c = CinemaFactory.createNewInstance(cinemaClass, cinemaName, cineplexId, cinemaCode, templateLayoutId);
        list.add(c);
        CinemaDB.commit();
    }
    
    /* Return a specific Cinema with given Id */
    public static Cinema getCinemaById(int Id) {
        LinkedList<Cinema> list = CinemaDB.getCinemaList();
        for(Cinema c : list) {
            if (c.getId() == Id) return c;
        }
        return null;
    }
    
    /* Return list of cinemas with given cineplex Id */
    public static LinkedList<Cinema> getCinemasByCineplexId(int Id) {
        LinkedList<Cinema> result = new LinkedList<Cinema>();
        LinkedList<Cinema> list = CinemaDB.getCinemaList();
        for(Cinema c : list) {
            if (c.getCineplexId() == Id) result.add(c);
        }
        return result;
    }
    
    /* Return list of cinemas with given movie_id */
    public static LinkedList<Cinema> getCinemasByMovie(int movieId) {
        LinkedList<Showtime> st = ShowtimeController.getShowtimesByMovie(movieId);
        LinkedList<Cinema> result = new LinkedList<Cinema>();
        LinkedList<Cinema> list = CinemaDB.getCinemaList();
        for(Cinema c : list) {
            boolean isValid = false;
            for(Showtime s : st) {
                if (c.getId() == s.getCinemaId()) {
                    isValid = true;
                    break;
                }
            }
            result.add(c);
        }
        return result;
    }
    
    /* Return the Cinema list */
    public static LinkedList<Cinema> getCinemaList() {
        return CinemaDB.getCinemaList();
    }
}
