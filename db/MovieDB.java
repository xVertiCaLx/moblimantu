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
                String movieType = s.nextToken();
                String movieName = s.nextToken();
                String movieStatus = s.nextToken();
                double movieRating = Double.parseDouble(s.nextToken());
                list.add(new Movie(movieId, movieType, movieName, movieStatus, 
                                   movieRating));
            }
        } catch (IOException e) {
            System.out.println("IOException at movieDB " + e.getMessage());
        }
    }
    /* 
     * Return a set of Movie which has the same title as args 
     */
    public static LinkedList<Movie> getMoviesByStatus(String ...args) {
        LinkedList<Movie> Result = new LinkedList<Movie>();
        for(Movie m : list) {
            boolean isSelect = false;
            for(String status : args) 
            if (m.getStatus().compareTo(status) == 0) {
                isSelect = true;
            }
            if (isSelect) Result.add(m);
        }
        return Result;
    }
    /* 
     * Return a set of Movie which has the same title as title 
     */
    public static LinkedList<Movie> getMoviesByTitle(String title) {
        LinkedList<Movie> Result = new LinkedList<Movie>();
        for(Movie m : list) {
            if (m.getName().compareTo(title) == 0) {
                Result.add(m);
            }
        }
        return Result;
    }
    
    /*
     * Return a specific movie with given Id
     */
    public static Movie getMovieById(int Id) {
        for(Movie m : list) {
            if (m.getId() == Id) return m;
        }
        return null;
    }
}
