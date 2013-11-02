package db;
import static db.CinemaDB.commit;
import static db.CinemaDB.list;
import entity.Cinema;
import entity.Cineplex;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import utils.Constant;

/* Loading database for Cineplex */
public class CineplexDB {
    
    public static LinkedList<Cineplex> list;
    
    /* Add a Cineplex to the database & database */
    public static void addCineplex(String cineplexName){
        Cineplex c = new Cineplex(list.size()+1, cineplexName);
        list.add(c);
        commit();
    }
    
    /* commit the changes to the database */
    public static void commit() {
        try {
            PrintWriter pw = new PrintWriter(new File(Constant.DATABASE_PATH + Constant.CINEPLEX_DATABASE));
            for(Cineplex c : list) {
                pw.write(new Integer(c.getId()).toString()); pw.write("|");
                pw.write(c.getName()); pw.write("\r\n");
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("IOException in commit CineplexDB " + e.getMessage());
        }
    }
    
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