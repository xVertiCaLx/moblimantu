package entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import utils.Constant;

public class Showtime {
    private int id;
    private Date time;
    private int movieId;
    private int cinemaId;
    private int seatLayoutId;
    
    public Showtime() {
    }

    public Showtime(int id, Date time, int movieId, int cinemaId, int seatLayoutId) {
        this.id = id;
        this.time = time;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.seatLayoutId = seatLayoutId;
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
    
    public String getTimeStringFormat() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormatter.format(time);
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
    
    public int getSeatLayoutId() {
        return seatLayoutId;
    }
    public void setSeatLayoutId(int seatLayoutId) {
        this.seatLayoutId = seatLayoutId;
    }
}
