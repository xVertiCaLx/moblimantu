package db;
import static db.CineplexDB.commit;
import entity.Showtime;
import factory.ShowtimeFactory;
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
import utils.Common;
/* Loading database for Cineplex */
public class ShowtimeDB {
    
    private static LinkedList<Showtime> list;
    /* commit the changes to the database */
    public static void commit() {
        try {
            PrintWriter pw = new PrintWriter(new File(Constant.DATABASE_PATH + Constant.SHOWTIME_DATABASE));
            for(Showtime s : list) {
                pw.write(new Integer(s.getId()).toString()); pw.write("|");
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pw.write(dateFormatter.format(s.getTime())); pw.write("|");
                pw.write(new Integer(s.getMovieId()).toString()); pw.write("|");
                pw.write(new Integer(s.getCinemaId()).toString()); pw.write("|");
                pw.write(new Integer(s.getSeatLayoutId()).toString()); pw.write("\r\n");
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
                 * id|time|movie_id|cinema_id|seatlayoutid
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
                int showtimeSeatLayoutId = Integer.parseInt(s.nextToken());
                list.add(new Showtime(showtimeId, showtimeTime, showtimeMovieId, showtimeCinemaId, showtimeSeatLayoutId));
            }
        } catch (IOException e) {
            System.out.println("IOException at Showtime " + e.getMessage());
        }
    }

    /* Get the Showtime list */
    public static LinkedList<Showtime> getShowtimeList() {
        return list;
    }
    
    /* Unit Test part */
    public static void main(String[] args) {
        System.out.println("UNIT TEST FOR SHOWTIME DB");
        Common.initDB();
        System.out.println("FINISH INIT DB");
        Showtime newShowtime = ShowtimeFactory.createNewInstance("2013-12-28 08:30:34", 1, 2);
        list.add(newShowtime);
        commit();
        ShowtimeDB.loadDB(Constant.DATABASE_PATH + Constant.SHOWTIME_DATABASE);
        for(Showtime s : ShowtimeDB.getShowtimeList()) {
            System.out.print(s.getId() + " ");
            System.out.print(s.getTime() + " ");
            System.out.print(s.getMovieId() + " ");
            System.out.print(s.getCinemaId() + " ");
            System.out.println(s.getSeatLayoutId() + " ");
        }
    }
}