package helper;

import controller.CinemaController;
import controller.MovieController;
import controller.ShowtimeController;
import entity.Booking;
import entity.Cinema;
import entity.Movie;
import entity.Showtime;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import utils.Constant;

public class PriceHelper {

    //price for a normal regular movie
    public static double priceRegularMovie = 10.0;
    
    //price for a 3D movie
    public static double price3DMovie = 15.0;
    
    //discount for senior citizen
    public static double priceSeniorDiscount = 2.0;
    
    //addtitional charge for VIP Class Cinema
    public static double priceCinemaVIPAdd = 5.0;
    
    //additional charge for public holiday
    public static double priceHolidayAdd = 5.0;
    
    //addtitional charge for weekend
    public static double priceWeekendAdd = 3.0;
    
    public static int SENIOR_THRESHOLD = 60;
    
    public static void commit() {
        try {
            PrintWriter pw = new PrintWriter(new File(Constant.DATABASE_PATH+Constant.PRICE_CONFIG_DATABASE));
            pw.write(new Double(priceRegularMovie).toString());    pw.write("\r\n");
            pw.write(new Double(price3DMovie).toString());         pw.write("\r\n");
            pw.write(new Double(priceSeniorDiscount).toString());  pw.write("\r\n");
            pw.write(new Double(priceCinemaVIPAdd).toString());   pw.write("\r\n");
            pw.write(new Double(priceHolidayAdd).toString()); pw.write("\r\n");
            pw.write(new Double(priceWeekendAdd).toString()); pw.write("\r\n");
            pw.close();
        } catch (IOException e) {
            System.out.println("IOException in commit public holiday");
        }
    }
    
    public static void initPriceConfig() {
        try {
            Scanner sc = new Scanner(new File(Constant.DATABASE_PATH+Constant.PRICE_CONFIG_DATABASE));
            priceRegularMovie = sc.nextDouble();
            price3DMovie = sc.nextDouble();
            priceSeniorDiscount = sc.nextDouble();
            priceCinemaVIPAdd = sc.nextDouble();
            priceHolidayAdd = sc.nextDouble();
            priceWeekendAdd = sc.nextDouble();
        } catch (IOException e) {
            System.out.println("IOException in initPrice" + e.getMessage());
        }
    }
    public static void setPriceRegularMovie(double newPriceRegularMovie) {
        priceRegularMovie = newPriceRegularMovie;
        commit();
    }
    public static void setPrice3DMovie(double newPrice3DMovie) {
        price3DMovie = newPrice3DMovie;
        commit();
    }
    
    public static void setPriceSeniorDiscount(double newPriceSeniorDiscount) {
        priceSeniorDiscount = newPriceSeniorDiscount;
        commit();
    }
    public static void setPriceCinemaVIPAdd(double newPriceCinemaVIPAdd) {
        priceCinemaVIPAdd = newPriceCinemaVIPAdd;
        commit();
    }
    public static void setPriceHolidayAdd(double newPriceHolidayAdd) {
        priceHolidayAdd = newPriceHolidayAdd;
        commit();
    }
    public static double calculatePrice(int showtimeId, int customerAge) {
        // Initialize
        double price = priceRegularMovie;
        Showtime st = ShowtimeController.getShowtimeById(showtimeId);
        int cinemaId = st.getCinemaId();
        Cinema c = CinemaController.getCinemaById(cinemaId);
        int movieId = st.getMovieId();
        Movie m = MovieController.getMovieById(movieId);
        
        //get type of movie
        if (m.getType() == Constant.MOVIE_TYPE_3D || m.getType() == Constant.MOVIE_TYPE_BLOCK_BUSTER) {
            price = price3DMovie;
        }
        //get senior age
        if (customerAge >= SENIOR_THRESHOLD) price -= priceSeniorDiscount;
        
        //get public holiday
        if (DateHelper.isPublicHoliday(st.getTime())) price += priceHolidayAdd;
        
        //get cinema class
        if (c.getCinemaClass() == Constant.CINEMA_VIP) price += priceCinemaVIPAdd;
        
        //if weekend
        if (DateHelper.isWeekend(st.getTime())) price += priceWeekendAdd;
        
        return price;
    }
}
