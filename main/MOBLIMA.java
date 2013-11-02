/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import db.MovieDB;
import db.CinemaDB;
import db.CineplexDB;
import db.ShowtimeDB;
import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import entity.Showtime;
import utils.Constant;
/**
 *
 * @author Vu
 */
public class MOBLIMA {

    /**
     * @param args the command line arguments
     */
    public static void initDB() {
        MovieDB.loadDB(Constant.database_path + Constant.movie_database);
        for(Movie m : MovieDB.list) {
            System.out.println(m.getId());
            System.out.println(m.getName());
            System.out.println(m.getStatus());
            System.out.println(m.getType());
        }
        CinemaDB.loadDB(Constant.database_path + Constant.cinema_database);
        for(Cinema c : CinemaDB.list) {
            System.out.println(c.getId());
            System.out.println(c.getCinemaClass());
            System.out.println(c.getName());
            System.out.println(c.getCineplexId());
            System.out.println(c.getCinemaCode());
        }
        CineplexDB.loadDB(Constant.database_path + Constant.cineplex_database);
        for(Cineplex c : CineplexDB.list) {
            System.out.println(c.getId());
            System.out.println(c.getName());
        }
        ShowtimeDB.loadDB(Constant.database_path + Constant.showtime_database);
        for(Showtime s : ShowtimeDB.list) {
            System.out.println(s.getId());
            System.out.println(s.getTime());
            System.out.println(s.getMovieId());
            System.out.println(s.getCinemaId());
        }
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        initDB();
        //Scanner scanner = new Scanner(System.in);
       // References.setInputStream(scanner);
       // MainPage.getInstance().launch();
    }
}
