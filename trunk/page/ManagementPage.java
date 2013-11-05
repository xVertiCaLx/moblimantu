/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import controller.BookingController;
import controller.CinemaController;
import controller.MovieController;
import controller.ShowtimeController;
import controller.StaffController;
import entity.Movie;
import entity.Showtime;
import factory.MovieFactory;
import factory.ShowtimeFactory;
import java.util.LinkedList;
import java.util.Scanner;
import printer.CinemaPrinter;
import printer.MoviePrinter;
import printer.ShowtimePrinter;
import utils.References;

/**
 *
 * @author Khach
 */
public class ManagementPage {
    private static final ManagementPage INSTANCE = new ManagementPage();
    private Scanner sc = References.getInputStream();
    private ManagementPage(){}
    public static ManagementPage getInstance() {
        return INSTANCE;
    }
    
    private String userName, password;
    
    private boolean login() {
        System.out.print("User name: ");
        userName = sc.nextLine();
        System.out.print("Password: ");
        password = sc.nextLine();
        if (StaffController.authenticate(userName, password)) return true;
        return false;
    }
    
    public void launch() {
        int choice = 0;
        do {
            System.out.println("Staff Managerment ...");
            System.out.println("1. Login to your account");
            System.out.println("2. Go back to main page");
            System.out.print("Please choose your option: ");            
            choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                if (login()) {
                    System.out.println("Login successfully!");
                    startManagement();
                } else {
                    System.out.println("Login failed! Please try again.");
                }
            }
        } while (choice != 2);
    }
    private void startManagement() {
        int choice = 0;
        do {
            System.out.println("Staff Function ...");
            System.out.println("1. Add movie");
            System.out.println("2. Edit movie");
            System.out.println("3. Add showtime");
            System.out.println("4. Edit showtime");
            System.out.println("5. Go back to Staff Management page");
            System.out.print("Please choose your option: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: addShowtime();
                        break;
                case 2: editShowtime();
                        break;
            }
        } while (choice != 5);
    }
    private void addMovie() {
        int choice = 0;
        do {
            System.out.println("Adding movie ...");
            System.out.print("Movie name: ");
            String name = sc.nextLine();
            System.out.print("Movie type (0 - Regular, 1 - BlockBuster, 2 - 3D Movie): ");
            int type = Integer.parseInt(sc.nextLine());
            System.out.print("Movie status (0 - Coming Soon, 1 - Preview, 2 - Now Showing, 3 - End of Showing):");
            int status = Integer.parseInt(sc.nextLine());
            System.out.print("Movie rating: ");
            double rating = Double.parseDouble(sc.nextLine());
            MovieController.addMovie(type, name, status, rating);
            System.out.println("Movie added\n");
            System.out.println("1. Add another movie");
            System.out.println("2. Go back to Staff Function page"); 
            System.out.print("Please choose your option: ");
            choice = Integer.parseInt(sc.nextLine());
        } while (choice == 1);
    }
    
    private void editMovie() {
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
    
    private void addShowtime() {
        LinkedList list;
        int choice = 0;
        do {
            System.out.println("Add a showtime ...");
            /*
             * get Movie Id
             */
            list = MovieController.getMovieList();
            MoviePrinter.getInstance().printList(list);
            System.out.print("Enter movie Id to add showtime, enter 0 to cancel: ");
            int movieId = Integer.parseInt(sc.nextLine());
            if (movieId == 0) break;
            if (MovieController.getMovieById(movieId) == null) continue;
            /*
             * get cinema Id
             */
            list = CinemaController.getCinemaList();
            CinemaPrinter.getInstance().printList(list);
            System.out.print("Enter cinema Id to add showtime, enter 0 to cancel: ");            
            int cinemaId = Integer.parseInt(sc.nextLine());
            if (cinemaId == 0) break;
            if (CinemaController.getCinemaById(cinemaId) == null) continue;
            
            System.out.print("Enter time to show (with format YYYY-MM-DD hh:mm:ss): ");
            String time = sc.nextLine();
            ShowtimeController.addShowtime(time, movieId, cinemaId);
            System.out.println("Showtime added ...");
            System.out.println("1. Add another showtime");
            System.out.println("2. Go back to Staff Function page ...");
            System.out.println("Please choose your option: ");
            choice = Integer.parseInt(sc.nextLine());
        } while (choice != 2);
    }
    
    private void editShowtime() {
        LinkedList list;
        int choice = 0;
        do {
            System.out.println("Edit/Update a showtime ...");
            /*
             * get cinema Id
             */
            list = CinemaController.getCinemaList();
            CinemaPrinter.getInstance().printList(list);
            System.out.print("Enter Cinema Id to edit showtime, enter 0 to cancel: ");
            int cinemaId = Integer.parseInt(sc.nextLine());
            if (cinemaId == 0) break;
            if (CinemaController.getCinemaById(cinemaId) == null) continue;
            /*
             * get showtime list base on cinemaID
             */
            list = ShowtimeController.getShowtimesByCinema(cinemaId);
            do {
                ShowtimePrinter.getInstance().printList(list);
                System.out.print("Enter showtime Id to edit showtime, enter 0 to cancel: ");            

                int showtimeId = Integer.parseInt(sc.nextLine());
                if (showtimeId == 0) break;

                Showtime showtime = ShowtimeController.getShowtimeById(showtimeId);
                if (showtime == null) continue;

                LinkedList bookings = BookingController.getBookingByShowtime(showtimeId);
                if (bookings.size() != 0) {
                    System.out.println("This showtime has some booking already. Can not be edited.");
                    continue;
                }
                
                ShowtimePrinter.getInstance().printInstance(showtime);
                Showtime newShowtime = null;
                System.out.print("Enter field to edit, enter -1 to delete showtime, enter 0 to cancel: ");
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case -1:ShowtimeController.deleteShowtime(showtimeId); 
                            break;
                    case 1: System.out.print("New time (format YYYY-MM-DD hh:mm:ss): ");
                            String newTime = sc.nextLine();
                            newShowtime = ShowtimeFactory.createNewInstance(newTime, showtime.getCinemaId(), showtime.getMovieId());
                            break;
                    case 2: System.out.print("New cinema ID: ");
                            int newCinemaId = Integer.parseInt(sc.nextLine());
                            newShowtime = ShowtimeFactory.createNewInstance(showtime.getTime(), showtime.getMovieId(), newCinemaId);
                            break;
                    case 3: System.out.print("New movie ID: ");
                            int newMovieId = Integer.parseInt(sc.nextLine());
                            newShowtime = ShowtimeFactory.createNewInstance(showtime.getTime(), newMovieId, showtime.getCinemaId());
                            break;
                }
                if (1 <= choice && choice <= 3) {
                    ShowtimeController.editShowtime(showtimeId, newShowtime);
                }
            } while (true);
        } while (choice != 2);        
    }
}
