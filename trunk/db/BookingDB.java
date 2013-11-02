package db;

import entity.Booking;
import entity.Movie;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

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
                Timestamp           bookingTime = Timestamp.valueOf(s.nextToken());
                
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
