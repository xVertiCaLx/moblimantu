/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package printer;

import entity.Movie;
import java.util.LinkedList;

/**
 *
 * @author Khach
 */
public class MoviePrinter implements Printer {
    private static final MoviePrinter INSTANCE = new MoviePrinter();
    private MoviePrinter(){}
    public static MoviePrinter getInstance() {
        return INSTANCE;
    }
    
    @Override
    public void printList(LinkedList list) {
        for (Object movie: list) {
            System.out.println(((Movie)movie).getId() + ". " + ((Movie)movie).getName());
        }
    }
    @Override
    public void  printInstance(Object movie) {
        System.out.println("MovieId: " + ((Movie)movie).getId());
        System.out.println("1. Title: " + ((Movie)movie).getName());
        System.out.println("2. Type: " + ((Movie)movie).getType());
        System.out.println("3. Status: " + ((Movie)movie).getStatus());
        System.out.println("4. Rating: " + ((Movie)movie).getRating());
    } 
}
