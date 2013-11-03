package db;
import controller.ShowtimeController;
import entity.Cinema;
import entity.Showtime;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import utils.Constant;
/* Loading database for Cinema */
public class CinemaDB {
    
    private static LinkedList<Cinema> list;
    
    /* commit the changes to the database */
    public static void commit() {
        try {
            PrintWriter pw = new PrintWriter(new File(Constant.DATABASE_PATH + Constant.CINEMA_DATABASE));
            for(Cinema c : list) {
                pw.write(new Integer(c.getId()).toString()); pw.write("|");
                pw.write(c.getCinemaClass()); pw.write("|");
                pw.write(c.getName()); pw.write("|");
                pw.write(new Integer(c.getCineplexId()).toString()); pw.write("|");
                pw.write(c.getCinemaCode()); pw.write("\n");
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("IOException in commit CinemaDB " + e.getMessage());
        }
    }
    
    /* Load the Cinema database into list */
    public static void loadDB(String filename) {
        try {
            list = new LinkedList<Cinema>();
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                
                StringTokenizer s = new StringTokenizer(sc.nextLine(),"|");
                /* 
                 * Cinema input format
                 * id|class|name|CineplexID|code
                 */
                int cinemaId = Integer.parseInt(s.nextToken());
                String cinemaClass = s.nextToken();
                String cinemaName = s.nextToken();
                int cinemaCineplexId = Integer.parseInt(s.nextToken());
                String cinemaCode = s.nextToken();
                list.add(new Cinema(cinemaId, cinemaClass, cinemaName, cinemaCineplexId, cinemaCode));
            }
        } catch (IOException e) {
            System.out.println("IOException at cinemaDB " + e.getMessage());
        }
    }

    /* Return the Cinema list */
    public static LinkedList<Cinema> getCinemaList() {
        return list;
    }
}