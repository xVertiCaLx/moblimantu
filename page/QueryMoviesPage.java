package page;

import controller.MovieController;
import entity.Movie;
import java.util.LinkedList;
import java.util.Scanner;
import printer.MoviePrinter;
import utils.Common;
import utils.Constant;
import utils.References;

public class QueryMoviesPage {
    private static final QueryMoviesPage INSTANCE = new QueryMoviesPage();
    private QueryMoviesPage(){}
    public static QueryMoviesPage getInstance() {
        return INSTANCE;
    }
    
    public static final int SEARCH_NOW_SHOWING = 1;
    public static final int SEARCH_COMING_SOON = 2;
    public static final int SEARCH_TITLE = 3;
    
    public void launch(int option) {
        LinkedList<Movie> result = null;
        Scanner sc = References.getInputStream();        
        switch(option) {
            case FindMoviesPage.SEARCH_NOW_SHOWING: 
                    result = MovieController.getMoviesByStatus(Constant.MOVIE_STATUS_NOW_SHOWING);
                    break;
            case FindMoviesPage.SEARCH_COMING_SOON: 
                    result = MovieController.getMoviesByStatus(Constant.MOVIE_STATUS_COMING_SOON, Constant.MOVIE_STATUS_PREVIEW);
                    break;
            case FindMoviesPage.SEARCH_TITLE: 
                    System.out.print("Please enter the title of movie: ");
                    String title = sc.nextLine();
                    System.out.println();
                    result = MovieController.getMoviesByTitle(title);
        }
        int choice = 0;
        /*
         * display all movies in result
         */
        do {
            System.out.println("Searching result:");
            if (result == null || result.size() == 0) {
                System.out.print("No movie match. Redirect to Search movie engine page.");
                break;
            } else {
                MoviePrinter.getInstance().printList(result);
                System.out.print("Choose a movie to book (1 - " + result.size() + "), 0 to go back: ");
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 0) break;
                Movie mv = result.get(choice - 1);
                System.out.println();
                if (1 <= choice && choice <= result.size()) {
                    MoviePrinter.getInstance().printInstance(mv);                
                    if (mv.isNowShowing()) ShowtimeByMovieSubPage.getInstance().launch(result.get(choice-1).getId());
                    else {
                        System.out.println("Can only book for now showing movie!");
                        System.out.println();
                    }
                }
            }
        } while (choice != 0);
    }
    //unit test
    public static void main(String args[]) {
        Common.initDB();
        getInstance().launch(3);
    }
}
