/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import db.MovieDB;

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
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        initDB();
    }
}
