/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

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
    
    private boolean login(Scanner sc) {
        System.out.print("User name: ");
        userName = sc.next();
        System.out.print("Password: ");
        password = sc.next();
        if (StaffDB.authenticate(userName, password)) return true;
        return false;
    }
    public void launch() {
        Scanner sc = References.getInputStream();
        int choice = 0;
        do {
            System.out.println("Staff managerment ...");
            System.out.println("1. Login to your account");
            System.out.println("2. Go back to main page");
            choice = sc.nextInt();
            if (choice == 1) {
                if (login(sc)) {
                    System.out.println("Login successfully!");
                    startManagement(sc);
                } else {
                    System.out.println("Login failed! Please try again.");
                }
            }
        } while (choice != 2);
    }
    private void startManagement(Scanner sc) {
        int choice = 0;
        do {
            System.out.println("1. Update movie");
        }
    }
}
