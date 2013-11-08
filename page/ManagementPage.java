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
            System.out.println("===Staff Managerment===");
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
            System.out.println("5. Print Sale Revenue report");
            System.out.println("6. Configure system setting");
            System.out.println("7. Go back to Staff Management page");
            System.out.print("Please choose your option: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: addMovie();
                        break;
                case 2: editMovie();
                        break;
                case 3: addShowtime();
                        break;
                case 4: editShowtime();
                        break;
                case 5: printReport();
                        break;
                case 6: configureSetting();
                        break;
            }
        } while (choice != 7);
    }
    
    private void addMovie() {
        AddMoviePage.getInstance().launch();
    }
    
    private void editMovie() {
        EditMoviePage.getInstance().launch();
    }
    
    private void addShowtime() {
        AddShowtimePage.getInstance().launch();
    }
    
    private void editShowtime() {
        EditShowtimePage.getInstance().launch();
    }
    
    private void printReport() {
        PrintSaleRevenueReportPage.getInstance().launch();
    }
    
    private void configureSetting() {
        ConfigureSettingPage.getInstance().launch();
    }
}
