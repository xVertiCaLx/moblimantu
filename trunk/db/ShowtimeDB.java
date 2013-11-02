package db;
import entity.Showtime;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
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
                Timestamp showtimeTimestamp = Timestamp.valueOf(s.nextToken());
                int showtimeMovieId = Integer.parseInt(s.nextToken());
                int showtimeCinemaId = Integer.parseInt(s.nextToken());
                list.add(new Showtime(showtimeId, showtimeTimestamp, showtimeMovieId, showtimeCinemaId));
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
}