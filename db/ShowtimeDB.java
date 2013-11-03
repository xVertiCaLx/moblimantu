package db;
import static db.CineplexDB.commit;
import entity.Showtime;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import utils.Constant;
import java.util.logging.Level;
import java.util.logging.Logger;
/* Loading database for Cineplex */
public class ShowtimeDB {
    
    private static LinkedList<Showtime> list;
   /* Add a Showtime  to the database & database */
    public static void addShowtime(Date showtimeTime, int showtimeMovieId, int showtimeCinemaId){
        Showtime s = new Showtime(list.size()+1, showtimeTime, showtimeMovieId, showtimeCinemaId);
        list.add(s);
        commit();
    }
    
    /* Add a Showtime to the database & database */
    public static void addShowtime(String showtimeTimeStringFormat, int showtimeMovieId, int showtimeCinemaId){
        try {
            Date showtimeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(showtimeTimeStringFormat);                
            Showtime s = new Showtime(list.size()+1, showtimeTime, showtimeMovieId, showtimeCinemaId);
            list.add(s);
            commit();
        } catch (ParseException ex) {
            System.out.println("Parse Exception at addShowtime" + ex.getMessage());
            Logger.getLogger(ShowtimeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* Edit a specific Showtime */
    public static void editShowtime(int showtimeId, Showtime newShowtime) {
        for(Showtime st : list) {
            if (st.getId() == showtimeId) {
                st.setCinemaId(newShowtime.getCinemaId());
                st.setMovieId(newShowtime.getMovieId());
                st.setTime(newShowtime.getTime());
            }
        }
        
    }
    /* commit the changes to the database */
    public static void commit() {
        try {
            PrintWriter pw = new PrintWriter(new File(Constant.DATABASE_PATH + Constant.SHOWTIME_DATABASE));
            for(Showtime s : list) {
                pw.write(new Integer(s.getId()).toString()); pw.write("|");
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pw.write(dateFormatter.format(s.getTime())); pw.write("|");
                pw.write(new Integer(s.getMovieId()).toString()); pw.write("|");
                pw.write(new Integer(s.getCinemaId()).toString()); pw.write("\r\n");
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("IOException in commit CineplexDB " + e.getMessage());
        }
    }
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
    
    /* Get the Showtime list */
    public static LinkedList<Showtime> getShowtimeList() {
        return list;
    }
    
    /* Unit Test part */
    public static void main(String[] args) {
        loadDB(Constant.DATABASE_PATH + Constant.SHOWTIME_DATABASE);
        Date x = new Date();
        addShowtime("2013-12-28 12:12:12",2,3);
    }
}