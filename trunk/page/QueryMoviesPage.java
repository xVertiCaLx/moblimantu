package page;

import controller.MovieController;
import entity.Movie;
import java.util.LinkedList;
import java.util.Scanner;
import printer.MoviePrinter;
import utils.Constant;
import utils.References;

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
            case 1: result = MovieController.getMoviesByStatus(Constant.MOVIE_STATUS_NOW_SHOWING);
                    break;
            case 2: result = MovieController.getMoviesByStatus(Constant.MOVIE_STATUS_COMING_SOON, Constant.MOVIE_STATUS_PREVIEW);
                    break;
            case 3: System.out.print("Please enter the title of movie: ");
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
            if (result == null) {
                System.out.print("No movie match. Enter 0 to go back to Movie Search Engine: ");
                choice = Integer.parseInt(sc.nextLine());
            } else {
                MoviePrinter.getInstance().printList(result);
                System.out.print("Choose a movie to book (1-" + result.size() + "), 0 to go back: ");
                choice = Integer.parseInt(sc.nextLine());
                System.out.println();
                if (1 <= choice && choice <= result.size()) {
                    ShowtimeByMovieSubPage.getInstance().launch(result.get(choice-1).getId());
                }
            }
        } while (choice != 0);
    }
}
