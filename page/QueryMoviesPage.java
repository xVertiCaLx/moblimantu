/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import db.MovieDB;
import entity.Movie;
import java.util.LinkedList;
import java.util.Scanner;
import utils.References;

/**
 *
 * @author Khach
 */
public class QueryMoviesPage {
    private static final QueryMoviesPage INSTANCE = new QueryMoviesPage();
    private QueryMoviesPage(){}
    public static QueryMoviesPage getInstance() {
        return INSTANCE;
    }
    
    public void launch(int option) {
        LinkedList<Movie> result = null;
        Scanner sc = References.getInputStream();        
        switch(option) {
            case 1: result = MovieDB.getMoviesByStatus("now_showing");
                    break;
            case 2: result = MovieDB.getMoviesByStatus("coming_soon", "preview");
                    break;
            case 3: System.out.println("Please enter the title of movie: ");
                    String title = sc.next();
                    result = MovieDB.getMoviesByTitle(title);
        }
        int choice = 0;
        /*
         * display all movies in result
         */
        do {
            if (result == null || result.size() == 0) {
                System.out.println("No movie match... Enter 0 to go back to Find Movies Page");
                choice = sc.nextInt();
            } else {
                for (int index = 1; index <= result.size(); index++) {
                    System.out.println(index + ". " + result.get(index - 1).getName());
                }
                System.out.println("Enter one the the above movie or 0 to go back to Find Movies Page: ");
                choice = sc.nextInt();
                if (1 <= choice && choice <= result.size()) {
                    
                }
            }
        } while (choice != 0);
    }
}