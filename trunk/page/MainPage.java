package page;

import java.util.Scanner;
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
            System.out.println("4. Exit");            
            System.out.print("Please choose your option (1-4): ");
            choice = Integer.parseInt(sc.nextLine());
            System.out.println();
            switch(choice) {
                case 1: FindMoviesPage.getInstance().launch();
                        break;
                case 2: CheckBookingsPage.getInstance().launch();
                        break; 
                case 3: ManagementPage.getInstance().launch();
                        break;
            }
        } while (choice != 4);
    }        
}
