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
public class PrintSaleRevenueReportPage {
    private static final PrintSaleRevenueReportPage INSTANCE = new PrintSaleRevenueReportPage();
    private Scanner sc;
    private PrintSaleRevenueReportPage(){}
    public static PrintSaleRevenueReportPage getInstance() {
        return INSTANCE;
    }
    public void launch() {
        sc = References.getInputStream();
        int choice = 0;
        do {
            System.out.println("Printe Sale Revenue Report page...");
            System.out.println("1. Sale Revenue Report by movie");
            System.out.println("2. Sale Revenue Report by cineplex");            
            System.out.println("3. Sale Revenue Report by period");
            System.out.print("Please choose your option: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: reportByMovie();
                        break;
                case 2: reportByCineplex();
                        break;
                case 3: reportByPeriod();
                        break;
            }            
        } while (choice != 4);
    }
    
    private void reportByMovie() {
        System.out.println("Revenue report by Movie");
        
    }
    private void reportByCineplex() {
        System.out.println("Revenue report by Cineplex");
    }
    private void reportByPeriod() {
        System.out.println("Revenue report by Period");
    }
}
