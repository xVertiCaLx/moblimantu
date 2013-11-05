package page;

import controller.MovieController;
import entity.Movie;
import java.util.LinkedList;
import java.util.Scanner;
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
                    result = MovieController.getMoviesByTitle(title);
        }
        int choice = 0;
        /*
         * display all movies in result
         */
        do {
            if (result == null) {
                System.out.print("No movie match... Enter 0 to go back to Find Movies Page: ");
                choice = Integer.parseInt(sc.nextLine());
            } else {
                for (int index = 1; index <= result.size(); index++) {
                    System.out.println(index + ". " + result.get(index - 1).getName());
                }
                System.out.print("Enter one the the above movie or 0 to go back to Find Movies Page: ");
                choice = Integer.parseInt(sc.nextLine());
                if (1 <= choice && choice <= result.size()) {
                    System.out.println("We have not implemented this feature yet. Poor you :))");
                }
            }
        } while (choice != 0);
    }
}
