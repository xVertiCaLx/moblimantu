package page;

import java.util.Scanner;
import main.MovieSearchGUI;
import utils.Common;
import utils.References;

public class MainPage {
    private static final MainPage INSTANCE = new MainPage();    
    private MainPage(){}
    public static MainPage getInstance() {
        return INSTANCE;
    }
    public void launch() {
        Scanner sc = References.getInputStream();
        int choice = 0;
        do {          
            System.out.println(" ======================================================");
            System.out.println("|                 WELCOME TO MOBLIMA                   |");
            System.out.println("|    MOvie Booking and LIsting Management Application  |");
            System.out.println(" ======================================================");
            System.out.println("1. Find movies");
            System.out.println("2. Check bookings");
            System.out.println("3. Management (for staff only)");
            System.out.println("4. Search movies and showtimes with GUI");
            System.out.println("5. Exit");            
            System.out.print("Please choose your option (1-5): ");
            choice = Integer.parseInt(sc.nextLine());
            System.out.println();
            switch(choice) {
                case 1: FindMoviesPage.getInstance().launch();
                        break;
                case 2: CheckBookingsPage.getInstance().launch();
                        break; 
                case 3: ManagementPage.getInstance().launch();
                        break;
                case 4: showUpGUI();
                        break;
            }
        } while (choice != 5);
    }    
    
    private void showUpGUI() {
        String[] tempArg = new String[0];
        MovieSearchGUI.main(tempArg);
    }
    
    public static void main(String[] args) {
        Common.initDB();
        getInstance().launch();
    }
}
