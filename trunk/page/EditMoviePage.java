/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import controller.MovieController;
import entity.Movie;
import factory.MovieFactory;
import java.util.LinkedList;
import java.util.Scanner;
import printer.MoviePrinter;
import utils.Common;
import utils.References;

/**
 *
 * @author Khach
 */
public class EditMoviePage {
    private static final EditMoviePage INSTANCE = new EditMoviePage();
    private Scanner sc;
    private EditMoviePage(){}
    public static EditMoviePage getInstance() {
        return INSTANCE;
    }
    private Movie getMovieToEdit() {
        System.out.println("List of movie in the system: ");
        LinkedList<Movie> list = MovieController.getMovieList();        
        MoviePrinter.getInstance().printList(list);
        System.out.print("Enter movie (1 - " + list.size() + ") to edit, enter 0 to cancel: ");
        int index = Integer.parseInt(sc.nextLine());
        if (1 <= index && index <= list.size()) {
            return list.get(index - 1);        
        }
        return null;
    }
    
    public void launch() {
        sc = References.getInputStream();        
        int choice = 0;
        do {
            Movie movie = getMovieToEdit();
            if (movie == null) break;
            MoviePrinter.getInstance().printInstance(movie);
            Movie newMovie = MovieFactory.clone(movie);
            System.out.print("Enter field to edit, enter 0 to cancel: ");
            choice = Integer.parseInt(sc.nextLine());
            if (choice == 0) continue;
            switch(choice) {
                case MoviePrinter.MOVIE_TITLE: 
                        System.out.print("New movie title: ");
                        String newTittle = sc.nextLine();
                        newMovie.setName(newTittle);
                        break;
                case MoviePrinter.MOVIE_TYPE: 
                        System.out.print("New movie type (0 - Regular, 1 - BlockBuster, 2 - 3D Movie): ");
                        int newType = Integer.parseInt(sc.nextLine());
                        newMovie.setType(newType);
                        break;
                case MoviePrinter.MOVIE_STATUS: 
                        System.out.print("New movie status (0 - Coming Soon, 1 - Preview, 2 - Now Showing, 3 - End of Showing): ");
                        int newStatus = Integer.parseInt(sc.nextLine());
                        newMovie.setStatus(newStatus);
                        break;
                case MoviePrinter.MOVIE_RATING: 
                        System.out.print("New movie rating: ");
                        double newRating = Double.parseDouble(sc.nextLine());
                        newMovie.setRating(newRating);
                        break;
            }
            if ((MoviePrinter.MOVIE_TITLE <= choice && choice <= MoviePrinter.MOVIE_RATING)) {
                MovieController.editMovie(movie.getId(), newMovie);                
                System.out.println("Movie editted\n");
            }
        } while (true);
    }
    
    // Unit Testing
    public static void main (String[] args) {
        Common.initDB();
        getInstance().launch();
    }
}
