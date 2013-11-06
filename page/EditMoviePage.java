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
import utils.References;

/**
 *
 * @author Khach
 */
public class EditMoviePage {
    private static final EditMoviePage INSTANCE = new EditMoviePage();
    
    private EditMoviePage(){}
    public static EditMoviePage getInstance() {
        return INSTANCE;
    }
    public void launch() {
        Scanner sc = References.getInputStream();
        LinkedList<Movie> list = MovieController.getMovieList();
        int choice = 0;
        int movieId = 0;
        do {
            System.out.println("List of movie in the system: ");
            MoviePrinter.getInstance().printList(list);
            System.out.print("Enter movie Id to edit, enter 0 to go back: ");
            movieId = Integer.parseInt(sc.nextLine());
            if (movieId == 0) break;            
            Movie movie = MovieController.getMovieById(movieId);
            if (movie == null) continue;
            MoviePrinter.getInstance().printInstance(movie);
            Movie newMovie = MovieFactory.clone(movie);
            System.out.print("Enter field to edit, enter 0 to cancel: ");
            choice = Integer.parseInt(sc.nextLine());
            switch(choice) {
                case 1: System.out.print("New movie title: ");
                        String newTittle = sc.nextLine();
                        newMovie.setName(newTittle);
                        MovieController.editMovie(movieId, newMovie);
                        break;
                case 2: System.out.print("New movie type (0 - Regular, 1 - BlockBuster, 2 - 3D Movie): ");
                        int newType = Integer.parseInt(sc.nextLine());
                        newMovie.setType(newType);
                        break;
                case 3: System.out.print("New movie status (0 - Coming Soon, 1 - Preview, 2 - Now Showing, 3 - End of Showing): ");
                        int newStatus = Integer.parseInt(sc.nextLine());
                        newMovie.setStatus(newStatus);
                        break;
                case 4: System.out.print("New movie rating: ");
                        double newRating = Double.parseDouble(sc.nextLine());
                        newMovie.setRating(newRating);
                        break;
            }
            if ((1 <= choice && choice <= 4)) {
                MovieController.editMovie(movieId, newMovie);                
                System.out.println("Movie editted\n");
            }
        } while (movieId != 0);
    }
}
