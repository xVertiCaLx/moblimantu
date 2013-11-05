/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Vu
 */
public class Constant {
    public static String DATABASE_PATH = "src\\data\\";
    public static String MOVIE_DATABASE = "movie.txt";
    public static String CINEMA_DATABASE = "cinema.txt";
    public static String CINEPLEX_DATABASE = "cineplex.txt";
    public static String SHOWTIME_DATABASE = "showtime.txt";
    public static String BOOKING_DATABASE = "booking.txt";
    public static String STAFF_DATABASE = "staff.txt";
    public static String SEAT_LAYOUT_DATABASE = "seat_layout.txt";
    public static String PUBLIC_HOLIDAY_DATABASE = "public_holiday.txt";
    public static String ID_DATABASE = "id.txt";
    // Layout Template
    public static int SEAT_TEMPLATE_DEFAULT = 0;
    public static int SEAT_TEMPLATE_STANDARD = 1;
    public static String SEAT_TEMPLATE[] = {"seat_template_default.txt",
                                            "seat_template_standard.txt"};
    
    // Movie Type Template
    public static int MOVIE_TYPE_REGULAR = 0;
    public static int MOVIE_TYPE_BLOCK_BUSTER = 1;
    public static int MOVIE_TYPE_3D = 2;
    public static String MOVIE_TYPE[] = {"Regular", "BlockBuster", "3D Movie"};
    
    // Cinema Type Class
    public static int CINEMA_REGULAR = 0;
    public static int CINEMA_VIP = 1;
    public static String CINEMA_CLASS[] = {"Regular", "Plantium Movie Suite"};
    
    // Status Type Template
    public static int MOVIE_STATUS_COMING_SOON = 0;
    public static int MOVIE_STATUS_PREVIEW = 1;
    public static int MOVIE_STATUS_NOW_SHOWING = 2;
    public static int MOVIE_STATUS_END_OF_SHOWING = 3; // delete movie
    public static String MOVIE_STATUS[] = {"Coming Soon", "Preview", "Now Showing", "End of Showing"};
}
