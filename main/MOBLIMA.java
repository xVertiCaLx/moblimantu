/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import db.MovieDB;
import db.CinemaDB;
import entity.Cinema;
import entity.Movie;
import java.util.Scanner;
import page.MainPage;
import utils.References;
/**
 *
 * @author Vu
 */
public class MOBLIMA {

    /**
     * @param args the command line arguments
     */
    public static void initDB() {
        MovieDB.loadDB("src\\data\\movie.txt");
        for(Movie m : MovieDB.list) {
            System.out.println(m.getId());
            System.out.println(m.getName());
            System.out.println(m.getStatus());
            System.out.println(m.getType());
        }
        CinemaDB.loadDB("src\\data\\cinema.txt");
        for(Cinema c : CinemaDB.list) {
            System.out.println(c.getId());
            System.out.println(c.getCinemaClass());
            System.out.println(c.getName());
            System.out.println(c.getCineplexId());
            System.out.println(c.getCinemaCode());
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        initDB();
        Scanner scanner = new Scanner(System.in);
        References.setInputStream(scanner);
        MainPage.getInstance().launch();
    }
}
