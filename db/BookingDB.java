package db;

import entity.Booking;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Loading database for Movie */
public class BookingDB {
    public static LinkedList<Booking> list;
    
    /* Load the booking database into list */
    public static void loadDB(String filename) {
        try {
            list = new LinkedList<Booking>();
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                
                StringTokenizer s = new StringTokenizer(sc.nextLine(),"|");
                /* 
                 * Booking input format
                 * id|transaction_id|showtime_id|customer_name|customer_hp|customer_email|
                 * age|time|seatNumbers|price
                 * 
                 * seatNumbers are tokenized by *
                 */
                int                 bookingId = Integer.parseInt(s.nextToken());
                String              bookingTransactionId = s.nextToken();
                int                 bookingShowtimeId = Integer.parseInt(s.nextToken());
                String              bookingCustomerName = s.nextToken();
                String              bookingCustomerHP = s.nextToken();
                String              bookingCustomerEmail = s.nextToken();
                int                 bookingCustomerAge = Integer.parseInt(s.nextToken());
                Date                bookingTime = null;
                try {
                    bookingTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s.nextToken());
                } catch (ParseException ex) {
                    Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                LinkedList<Integer> bookingSeatNumbers = new LinkedList<Integer>();
                StringTokenizer seatList = new StringTokenizer(s.nextToken(),"*");
                while (seatList.hasMoreTokens()) {
                    bookingSeatNumbers.add(new Integer(seatList.nextToken()));
                }
                double              bookingPrice = Double.parseDouble(s.nextToken());
                
                list.add(new Booking(bookingId, bookingTransactionId, bookingShowtimeId,
                                     bookingCustomerName,
                                     bookingCustomerHP, bookingCustomerEmail,
                                     bookingCustomerAge, bookingTime, bookingSeatNumbers,
                                     bookingPrice));
            }
        } catch (IOException e) {
            System.out.println("IOException at BookingDb " + e.getMessage());
        }
    }
    
    /* Return a specific booking with given Id */
    public static Booking getBookingById(int Id) {
        for(Booking b : list) {
            if (b.getId() == Id) return b;
        }
        return null;
    }
    public static LinkedList<Booking> getBookingHistory(String email, String handPhone, String bookingRef) {
        return null;
    }
    public static LinkedList<Booking> getBookingStatus(String email, String handPhone, String bookingRef) {
        return null;
    }    
}
