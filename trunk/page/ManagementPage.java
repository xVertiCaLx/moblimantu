/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import db.MovieDB;
import db.StaffDB;
import java.util.Scanner;
import utils.References;

/**
 *
 * @author Khach
 */
public class ManagementPage {
    private static final ManagementPage INSTANCE = new ManagementPage();
    private ManagementPage(){}
    public static ManagementPage getInstance() {
        return INSTANCE;
    }
    
    private String userName, password;
    
    private boolean login() {
        Scanner sc = References.getInputStream();
        System.out.print("User name: ");
        userName = sc.nextLine();
        System.out.print("Password: ");
        password = sc.nextLine();
        if (StaffDB.authenticate(userName, password)) return true;
        return false;
    }
    public void launch() {
        Scanner sc = References.getInputStream();
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
        Scanner sc = References.getInputStream();        
        int choice = 0;
        do {
            System.out.println("Staff Function ...");
            System.out.println("1. Add movie");
            System.out.println("2. Go back to Staff Management page");
            System.out.print("Please choose your option: ");
            choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                addMovie();
            }
        } while (choice != 2);
    }
    private void addMovie() {
        Scanner sc = References.getInputStream();
        int choice = 0;
        do {
            System.out.println("Adding movie ...");
            System.out.print("Movie name: ");
            String name = sc.nextLine();
            System.out.print("Movie type: ");
            String type = sc.nextLine();
            System.out.print("Movie status: ");
            String status = sc.nextLine();
            System.out.print("Movie rating: ");
            double rating = Double.parseDouble(sc.nextLine());
            MovieDB.addMovie(type, name, status, rating);
            System.out.println("Movie added\n");
            System.out.println("1. Add another movie");
            System.out.println("2. Go back to Staff Function page"); 
            choice = Integer.parseInt(sc.nextLine());
        } while (choice == 1);
    }
}
