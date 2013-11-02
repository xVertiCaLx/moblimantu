package db;
import entity.Cinema;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
/* Loading database for Cinema */
public class CinemaDB {
    
    public static LinkedList<Cinema> cinemaList;
    public static void loadDB(String filename) {
        try {
            cinemaList = new LinkedList<Cinema>();
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                
                StringTokenizer s = new StringTokenizer(sc.nextLine(),"|");
                /* 
                 * Cinema input format
                 * id|class|name|CineplexID|code
                 */
                int cinemaID = Integer.parseInt(s.nextToken());
                String cinemaClass = s.nextToken();
                String cinemaName = s.nextToken();
                int cinemaCineplexID = Integer.parseInt(s.nextToken());
                String cinemaCode = s.nextToken();
                cinemaList.add(new Cinema(cinemaID, cinemaClass, cinemaName, cinemaCineplexID, cinemaCode));
            }
        } catch (IOException e) {
            System.out.println("IOException at cinemaDB " + e.getMessage());
        }
    }
}