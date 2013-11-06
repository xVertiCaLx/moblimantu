package page;

import java.util.Scanner;
import utils.References;

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
            System.out.println("Movies Search Engine");                    
            System.out.println("1. Search Now Showing movie");
            System.out.println("2. Search Coming Soon and Preview movie");
            System.out.println("3. Search movie by title");
            System.out.println("4. Back to main page");
            System.out.print("Please choose your options: ");
            choice = Integer.parseInt(sc.nextLine());
            System.out.println();
            if (1 <= choice && choice <= 3) QueryMoviesPage.getInstance().launch(choice);
        } while (choice != 4);
    }
}
