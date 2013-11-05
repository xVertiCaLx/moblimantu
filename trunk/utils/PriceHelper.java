/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import controller.CinemaController;
import controller.MovieController;
import controller.ShowtimeController;
import entity.Booking;
import entity.Cinema;
import entity.Movie;
import entity.Showtime;

/**
 *
 * @author Vu
 */
public class PriceHelper {

    public static double PRICE_REGULAR_MOVIE = 10.0;
    public static double PRICE_3D_MOVIE = 15.0;
    public static double PRICE_SENIOR_DISCOUNT = 2.0;
    public static double PRICE_CINEMA_VIP_ADD = 5.0;
    public static double PRICE_HOLIDAY_ADD = 5.0;
    public static int SENIOR_THRESHOLD = 60;
    
    public static double calculatePrice(Booking booking) {
        // Initialize
        double price = PRICE_REGULAR_MOVIE;
        int showtimeId = booking.getShowtimeId();
        Showtime st = ShowtimeController.getShowtimeById(showtimeId);
        int cinemaId = st.getCinemaId();
        Cinema c = CinemaController.getCinemaById(cinemaId);
        int movieId = st.getMovieId();
        Movie m = MovieController.getMovieById(movieId);
        
        //get type of movie
        if (m.getType() == Constant.MOVIE_TYPE_3D || m.getType() == Constant.MOVIE_TYPE_BLOCK_BUSTER) {
            price = PRICE_3D_MOVIE;
        }
        //get senior age
        if (booking.getCustomerAge() >= SENIOR_THRESHOLD) price -= PRICE_SENIOR_DISCOUNT;
        
        //get public holiday
        if (DateHelper.isPublicHoliday(booking.getTime())) price += PRICE_HOLIDAY_ADD;
        
        //get cinema class
        if (c.getCinemaClass() == Constant.CINEMA_VIP) price += PRICE_CINEMA_VIP_ADD;
        
        return price;
    }
}
