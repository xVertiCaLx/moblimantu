package page;

import controller.BookingController;
import entity.Booking;
import java.util.LinkedList;
import java.util.Scanner;
import printer.BookingPrinter;
import utils.Common;
import utils.References;

public class CheckBookingsPage {
    private static final CheckBookingsPage INSTANCE = new CheckBookingsPage();
    private String email, handPhone;
    
    private CheckBookingsPage(){}
    public static CheckBookingsPage getInstance() {
        return INSTANCE;
    }
    
    public void captureInformation() {
        Scanner sc = References.getInputStream();        
        System.out.println("Please enter your information below");
        System.out.print("Email address:\t");
        email = sc.nextLine();
        System.out.print("Mobile number:\t");
        handPhone = sc.nextLine();
    }
    
    public void launch() {
        Scanner sc = References.getInputStream();
        int choice = 0;
        do {
            System.out.println("===CHECK YOUR BOOKING===");                    
            System.out.println("1. Check booking history");
            System.out.println("2. Check booking status");
            System.out.println("3. Back to main page");
            System.out.print("Please choose your options: ");
            choice = Integer.parseInt(sc.nextLine());
            if (choice == 3) break;
            captureInformation();
            LinkedList<Booking> result = null;
            switch(choice) {
                case 1: result = BookingController.getBookingHistory(email, handPhone);
                        break;
                case 2: result = BookingController.getBookingStatus(email, handPhone);
                        break;
            }
            if (result == null || result.size() == 0) {
                System.out.println("ERROR: There is no result match with your information");
                System.out.println();
                continue;
            }
            BookingPrinter.getInstance().printList(result);
            System.out.print("Choose a booking to see booking invoice (1-" + result.size() + "), 0 to cancel: ");
            choice = Integer.parseInt(sc.nextLine());
            System.out.println();
            if (1 <= choice && choice <= result.size()) {
                BookingPrinter.getInstance().printInstance(result.get(choice - 1));
                System.out.println();
            }
        } while (choice != 3);        
    }
    public static void main(String[] args) {
        Common.initDB();
        getInstance().launch();
    }
}
