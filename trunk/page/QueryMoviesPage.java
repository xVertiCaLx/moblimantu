/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import db.MovieDB;
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
        LinkedList result = null;
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
        do {
            
        } while (choice != 0);
    }
}
