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
public class FindMoviesPage {
    private static final FindMoviesPage INSTANCE = new FindMoviesPage();
    private FindMoviesPage(){}
    public static FindMoviesPage getInstance() {
        return INSTANCE;
    }
    
    public void launch() {
        Scanner sc = References.getInputStream();
        int choice = 0;
        do {
            System.out.println("Finding movies ...");                    
            System.out.println("1. Now showing");
            System.out.println("2. Coming soon and Preview");
            System.out.println("3. Search movie by title");
            System.out.println("4. Back to main page");
            System.out.print("Please choose your options: ");
            choice = Integer.parseInt(sc.nextLine());
            if (1 <= choice && choice <= 3) QueryMoviesPage.getInstance().launch(choice);
        } while (choice != 4);
    }
}
