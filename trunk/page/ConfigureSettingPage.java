/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import helper.PriceHelper;
import java.util.Scanner;
import utils.Common;
import utils.References;

/**
 *
 * @author Khach
 */
public class ConfigureSettingPage {
    private static final ConfigureSettingPage INSTANCE = new ConfigureSettingPage();
    private Scanner sc;
    private ConfigureSettingPage(){}
    public static ConfigureSettingPage getInstance() {
        return INSTANCE;
    }
    public void launch() {
        sc = References.getInputStream();
        int choice;
        do {
            System.out.println("===Configure system setting page===");
            System.out.println("1. Configure ticket price");
            System.out.println("2. Set up holiday");
            System.out.println("3. Go back to staff page");
            System.out.print("Please choose your option: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: configureTicketPrice();
                        break;
                case 2: configureHoliday();
                        break;
            }
        } while (choice != 3);
    }
    
    private void configureTicketPrice() {
        System.out.println("===Configure ticket price===");
        System.out.println("1. Regular price = \t\t\t\t" + PriceHelper.priceRegularMovie);
        System.out.println("2. 3D movie price = \t\t\t\t" + PriceHelper.price3DMovie);
        System.out.println("3. Amount discount for senior citizen = \t" + PriceHelper.priceSeniorDiscount);
        System.out.println("4. Additional charge for VIP class Cinema = \t" + PriceHelper.priceCinemaVIPAdd);
        System.out.println("5. Additional charge for public holiday = \t" + PriceHelper.priceHolidayAdd);
        System.out.print("Please choose your option (0 to go back): ");
        int choice = Integer.parseInt(sc.nextLine());
        if (1 <= choice && choice <= 5) {
            System.out.print("Enter your new price: ");
            double newPrice = Double.parseDouble(sc.nextLine());
            switch (choice) {
                case 1: PriceHelper.setPriceRegularMovie(newPrice);
                        break;
                case 2: PriceHelper.setPrice3DMovie(newPrice);
                        break;
                case 3: PriceHelper.setPriceSeniorDiscount(newPrice);
                        break;
                case 4: PriceHelper.setPriceCinemaVIPAdd(newPrice);
                        break;
                case 5: PriceHelper.setPriceHolidayAdd(newPrice);
                        break;
            }
            System.out.println("Price adjusted successfully!");
            System.out.println();
        }
    }
    private void configureHoliday() {
        
    }
    public static void main(String[] args) {
        Common.initDB();
        getInstance().launch();
    }
}
