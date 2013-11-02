package db;
import entity.Cineplex;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/* Loading database for Cineplex */
public class CineplexDB {
    
    public static LinkedList<Cineplex> list;
    
    /* Load the cineplex database to list */
    public static void loadDB(String filename) {
        try {
            list = new LinkedList<Cineplex>();
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                
                StringTokenizer s = new StringTokenizer(sc.nextLine(),"|");
                /*
                 * Cineplex Input format
                 * id|name
                 */
                int cineplexId = Integer.parseInt(s.nextToken());
                String cineplexName = s.nextToken();
                list.add(new Cineplex(cineplexId, cineplexName));
            }
        } catch (IOException e) {
            System.out.println("IOException at cineplexID " + e.getMessage());
        }
    }
    
    /* Get a specific Cineplex with given Id */
    public static Cineplex getCineplexById(int Id) {
        for(Cineplex c : list) {
            if (c.getId() == Id)  return c;
        }
        return null;
    }
}