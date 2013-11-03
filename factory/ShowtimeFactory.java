/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import controller.ShowtimeController;
import entity.Showtime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Vu
 */
public class ShowtimeFactory {
    public static Showtime createNewInstance(Date showtimeTime, int showtimeMovieId, int showtimeCinemaId) {
        LinkedList<Showtime> list = ShowtimeController.getShowtimeList();
        return new Showtime(list.size()+1,showtimeTime, showtimeMovieId, showtimeCinemaId);
    }
    
    public static Showtime createNewInstance(String showtimeTimeStringFormat, int showtimeMovieId, int showtimeCinemaId) {
        try {
            LinkedList<Showtime> list = ShowtimeController.getShowtimeList();
            Date showtimeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(showtimeTimeStringFormat);
            return new Showtime(list.size()+1,showtimeTime, showtimeMovieId, showtimeCinemaId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
