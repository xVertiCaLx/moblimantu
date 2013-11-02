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
public class CheckBookingsPage {
    private static final CheckBookingsPage INSTANCE = new CheckBookingsPage();
    private CheckBookingsPage(){}
    public static CheckBookingsPage getInstance() {
        return INSTANCE;
    }
    public void launch() {
        Scanner sc = References.getInputStream();
        int choice = 0;
        do {
            System.out.println("Check your booking ...");                    
            System.out.println("1. Check booking history");
            System.out.println("2. Check booking status");
            System.out.println("3. Back to main page");
            System.out.print("Please choose your options: ");
            choice = sc.nextInt();
            switch(choice) {
                case 1: 
                        break;
                case 2:
                        break;
                case 3:
            }
        } while (choice != 3);        
    }
}
