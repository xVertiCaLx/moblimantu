package db;
import entity.Showtime;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
/* Loading database for Cineplex */
public class ShowtimeDB {
    
    public static LinkedList<Showtime> list;
    
    /* Load the Showtime database into list */
    public static void loadDB(String filename) {
        try {
            list = new LinkedList<Showtime>();
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                
                StringTokenizer s = new StringTokenizer(sc.nextLine(),"|");
                /*
                 * Showtime Input format
                 * id|time|movie_id|cinema_id
                 */
                int showtimeId = Integer.parseInt(s.nextToken());
                Date showtimeTime = null;
                try {
                    showtimeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s.nextToken());
                } catch (ParseException ex) {
                    Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                int showtimeMovieId = Integer.parseInt(s.nextToken());
                int showtimeCinemaId = Integer.parseInt(s.nextToken());
                list.add(new Showtime(showtimeId, showtimeTime, showtimeMovieId, showtimeCinemaId));
            }
        } catch (IOException e) {
            System.out.println("IOException at Showtime " + e.getMessage());
        }
    }

    /* Return a specific showtime with given id */
    public static Showtime getShowtimeById(int id) {
        for(Showtime s : list) {
            if (s.getId() == id) return s;
        }
        return null;
    }
    
    /* Return list of Showtimes with given movie_id*/
    public static LinkedList<Showtime> getShowtimesByMovie(int movieId) {
        LinkedList<Showtime> result = new LinkedList<Showtime>();
        for(Showtime s : list) {
            if (s.getMovieId() == movieId) result.add(s);
        }
        return result;
    }
    
    /* Return list of Showtimes with given cinema_id */
    public static LinkedList<Showtime> getShowtimesByCinema(int cinemaId) {
        LinkedList<Showtime> result = new LinkedList<Showtime>();
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
        for(Showtime s : list) {
            if (s.getCinemaId() == cinemaId && s.getMovieId() == movieId) {
                result.add(s);
            }
        }
        return result;
        
    }
}