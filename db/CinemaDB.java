package db;
import entity.Cinema;
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
                pw.write(new Integer(c.getId()).toString()); pw.write(Constant.FIELD_SEPARATOR);
                pw.write(new Integer(c.getCinemaClass()).toString()); pw.write(Constant.FIELD_SEPARATOR);
                pw.write(c.getName()); pw.write(Constant.FIELD_SEPARATOR);
                pw.write(new Integer(c.getCineplexId()).toString()); pw.write(Constant.FIELD_SEPARATOR);
                pw.write(c.getCinemaCode()); pw.write("\r\n");
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
                
                StringTokenizer s = new StringTokenizer(sc.nextLine(),Constant.FIELD_SEPARATOR);
                /* 
                 * Cinema input format
                 * id|class|name|CineplexID|code
                 */
                int cinemaId = Integer.parseInt(s.nextToken());
                int cinemaClass = Integer.parseInt(s.nextToken());
                String cinemaName = s.nextToken();
                int cinemaCineplexId = Integer.parseInt(s.nextToken());
                String cinemaCode = s.nextToken();
                int templateLayoutId = Integer.parseInt(s.nextToken());
                list.add(new Cinema(cinemaId, cinemaClass, cinemaName, cinemaCineplexId, cinemaCode, templateLayoutId));
            }
        } catch (IOException e) {
            System.out.println("IOException at cinemaDB " + e.getMessage());
        }
    }

    /* Return the Cinema list */
    public static LinkedList<Cinema> getCinemaList() {
        if (list == null) System.out.println("DATABASE NOT INITIALIZED !! PUT IN: Common.initDB() TO SETUP DATABASE");
        assert list != null;
        return list;
    }
}