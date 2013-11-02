/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import db.BookingDB;
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
    
    public void captureInformation(Scanner sc) {
        System.out.println("Please enter your information below");
        System.out.print("Email address: ");
        email = sc.next();
        System.out.println("Mobile number: ");
        handPhone = sc.next();
        System.out.println("Booking references: ");
        bookingRef = sc.next();        
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
            if (choice == 3) break;
            captureInformation(sc);
            LinkedList<Booking> result = null;
            switch(choice) {
                case 1: result = BookingDB.getBookingHistory(email, handPhone, bookingRef);
                        break;
                case 2: result = BookingDB.getBookingStatus(email, handPhone, bookingRef);
                        break;
            }
            for(Booking booking: result) {
                System.out.println(booking.toString());
            }
        } while (choice != 3);        
    }
}
