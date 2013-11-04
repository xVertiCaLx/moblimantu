package db;

import entity.Movie;
import entity.Showtime;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import utils.Constant;

/* Loading database for Movie */
public class MovieDB {
    
    private static LinkedList<Movie> list;
    
    /* Commit the changes to the database */
    public static void commit() {
        try 
        {
            PrintWriter pw = new PrintWriter(new File(Constant.DATABASE_PATH + Constant.MOVIE_DATABASE));
            for(Movie m : list) {
                pw.write(new Integer(m.getId()).toString()); pw.write("|");
                pw.write(new Integer(m.getType()).toString()); pw.write("|");
                pw.write(m.getName()); pw.write("|");
                pw.write(new Integer(m.getStatus()).toString()); pw.write("|");
                pw.write(new Double(m.getRating()).toString()); pw.write("\r\n");
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("IOException in commit MovieDB " + e.getMessage());
        }
    }
    /* Load the movie database into list */
    public static void loadDB(String filename) {
        try {
            list = new LinkedList<Movie>();
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                
                StringTokenizer s = new StringTokenizer(sc.nextLine(),"|");
                /* 
                 * movie input format 
                 * id|type|name|status
                 */
                int movieId = Integer.parseInt(s.nextToken());
                int movieType = Integer.parseInt(s.nextToken());
                String movieName = s.nextToken();
                int movieStatus = Integer.parseInt(s.nextToken());
                double movieRating = Double.parseDouble(s.nextToken());
                list.add(new Movie(movieId, movieType, movieName, movieStatus, 
                                   movieRating));
            }
        } catch (IOException e) {
            System.out.println("IOException at movieDB " + e.getMessage());
        }
    }
    
    /* Get the movie list */
    public static LinkedList<Movie> getMovieList() {
        if (list == null) System.out.println("DATABASE NOT INITIALIZED !! PUT IN: Common.initDB() TO SETUP DATABASE");
        assert list != null;
        return list;
    }
}
