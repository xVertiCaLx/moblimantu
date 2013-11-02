/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import java.util.Scanner;
import utils.References;

/**
 *
 * @author Khach
 */
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
            System.out.println("Welcome to MOBLIMA...");            
            System.out.println("1. Find movies");
            System.out.println("2. Check bookings");
            System.out.println("3. Management (for staff only)");
            System.out.println("4. Exit");            
            System.out.print("Please choose your option: ");
            choice = sc.nextInt();
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
