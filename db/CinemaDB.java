package db;
import entity.Cinema;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
/* Loading database for Cinema */
public class CinemaDB {
    
    public static LinkedList<Cinema> list;
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
}