/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;


/**
 *
 * @author Khach
 */
public class Showtime {
    private int id;
    private Date time;
    private int movieId;
    private int cinemaId;
    
    public Showtime() {
    }

    public Showtime(int id, Date time, int movieId, int cinemaId) {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
