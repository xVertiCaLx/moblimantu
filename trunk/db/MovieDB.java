package db;

import entity.Movie;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/* Loading database for Movie */
public class MovieDB {
    
    public static LinkedList<Movie> list;
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
                String movieType = s.nextToken();
                String movieName = s.nextToken();
                String movieStatus = s.nextToken();
                list.add(new Movie(movieId, movieType, movieName, movieStatus));
            }
        } catch (IOException e) {
            System.out.println("IOException at movieDB " + e.getMessage());
        }
    }
    public static LinkedList getMoviesByStatus(String ...args) {
        return null;
    }
    public static LinkedList getMoviesByTitle(String title) {
        return null;
    }
}
