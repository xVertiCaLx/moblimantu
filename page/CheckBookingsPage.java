/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import controller.BookingController;
import entity.Booking;
import java.util.LinkedList;
import java.util.Scanner;
import utils.References;

/**
 *
 * @author Khach
 */
public class CheckBookingsPage {
    private static final CheckBookingsPage INSTANCE = new CheckBookingsPage();
    private String email, handPhone, bookingRef;
    
    private CheckBookingsPage(){}
    public static CheckBookingsPage getInstance() {
        return INSTANCE;
    }
    
    public void captureInformation() {
        Scanner sc = References.getInputStream();        
        System.out.println("Please enter your information below");
        System.out.print("Email address: ");
        email = sc.nextLine();
        System.out.println("Mobile number: ");
        handPhone = sc.nextLine();
        System.out.println("Booking references: ");
        bookingRef = sc.nextLine();        
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
            choice = Integer.parseInt(sc.nextLine());
            if (choice == 3) break;
            captureInformation();
            LinkedList<Booking> result = null;
            switch(choice) {
                case 1: result = BookingController.getBookingHistory(email, handPhone, bookingRef);
                        break;
                case 2: result = BookingController.getBookingStatus(email, handPhone, bookingRef);
                        break;
            }
            for(Booking booking: result) {
                System.out.println(booking.toString());
            }
        } while (choice != 3);        
    }
}
