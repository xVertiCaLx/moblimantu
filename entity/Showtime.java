/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author Khach
 */
public class Showtime {
    private int id;
    private Timestamp time;
    private int movieId;
    private int cinemaId;
    
    public Showtime() {
    }

    public Showtime(int id, Timestamp time, int movieId, int cinemaId) {
        this.id = id;
        this.time = time;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }
    
}
