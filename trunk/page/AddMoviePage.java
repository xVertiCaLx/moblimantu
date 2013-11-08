/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import controller.MovieController;
import java.util.Scanner;
import utils.References;

/**
 *
 * @author Khach
 */
public class AddMoviePage {
    private static final AddMoviePage INSTANCE = new AddMoviePage();
    
    private AddMoviePage(){}
    public static AddMoviePage getInstance() {
        return INSTANCE;
    }
    public void launch() {
        Scanner sc = References.getInputStream();
        int choice = 0;
        do {
            System.out.println("===Adding movie===");
            System.out.print("Movie name: ");
            String name = sc.nextLine();
            System.out.print("Movie type (0 - Regular, 1 - BlockBuster, 2 - 3D Movie): ");
            int type = Integer.parseInt(sc.nextLine());
            System.out.print("Movie status (0 - Coming Soon, 1 - Preview, 2 - Now Showing, 3 - End of Showing):");
            int status = Integer.parseInt(sc.nextLine());
            System.out.print("Movie rating: ");
            double rating = Double.parseDouble(sc.nextLine());
            MovieController.addMovie(type, name, status, rating);
            System.out.println();
            System.out.println("Movie added successfully!\n");
            System.out.println();
            System.out.println("1. Add another movie");
            System.out.println("2. Go back to Staff Function page"); 
            System.out.print("Please choose your option: ");
            choice = Integer.parseInt(sc.nextLine());
        } while (choice == 1);
        
    }
}
