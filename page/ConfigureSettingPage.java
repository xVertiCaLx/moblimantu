/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import helper.DateHelper;
import helper.PriceHelper;
import java.util.Date;
import java.util.LinkedList;
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
            System.out.println();
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
        int choice;
        String curDate;
        do {            
            System.out.println("===Configure public holiday===");
            System.out.println("1. See current public holiday");
            System.out.println("2. Add a public holiday");
            System.out.println("3. Remove a public holiday");
            System.out.print("Please choose your option (0 to go back): ");
            choice = Integer.parseInt(sc.nextLine());
            System.out.println();
            if (1 <= choice && choice <= 3) {
                LinkedList<Date> publicHolidays = DateHelper.getPublicHolidays();
                System.out.println("List of public holiday:");
                for(int index = 0; index < publicHolidays.size(); index++) {
                    Date d = publicHolidays.get(index);
                    System.out.println((index + 1) + ". " + DateHelper.getDateStringFormat(d));
                }
                System.out.println();
                switch (choice) {
                    case 1: break;
                    case 2: System.out.print("Add a new public holiday (YYYY-MM-DD): ");
                            curDate = sc.nextLine();
                            DateHelper.addPublicHoliday(curDate);
                            break;
                    case 3: System.out.print("Remove a public holiday (1 - "+ publicHolidays.size() + "): ");
                            int index = Integer.parseInt(sc.nextLine());
                            Date deletedDate = publicHolidays.get(index - 1);
                            DateHelper.removePublicHoliday(DateHelper.getDateStringFormat(deletedDate));
                            break;
                }
                if (choice != 1) {
                    System.out.println("Changes updated successfully!");
                    System.out.println();
                }
            }
        } while (choice != 0);
    }
    public static void main(String[] args) {
        Common.initDB();
        getInstance().launch();
    }
}
