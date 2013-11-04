package db;

import entity.Booking;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Constant;

/* Loading database for Movie */
public class BookingDB {
    private static LinkedList<Booking> list;
    /* commit the changes to the database */
    public static void commit() {
        try {
            PrintWriter pw = new PrintWriter(new File(Constant.DATABASE_PATH + Constant.BOOKING_DATABASE));
            for(Booking b : list) {
                pw.write(new Integer(b.getId()).toString()); pw.write("|");
                pw.write(b.getTransactionId()); pw.write("|");
                pw.write(new Integer(b.getShowtimeId()).toString()); pw.write("|");
                pw.write(b.getCustomerName()); pw.write("|");
                pw.write(b.getCustomerHP()); pw.write("|");
                pw.write(b.getCustomerEmail()); pw.write("|");
                pw.write(new Integer(b.getCustomerAge()).toString()); pw.write("|");
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pw.write(dateFormatter.format(b.getTime())); pw.write("|");
                StringBuffer seatList = new StringBuffer();
                for(Integer i : b.getSeatNumbers()) {
                    seatList.append(i.toString());
                    seatList.append("*");
                }
                pw.write(seatList.toString()); pw.write("|");
                pw.write(new Double(b.getPrice()).toString()); pw.write("|");
                pw.write("\r\n");
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("IOException in commit CinemaDB " + e.getMessage());
        }
    }
    
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
    
    /* Return the Booking list */
    public static LinkedList<Booking> getBookingList() {
        if (list == null) System.out.println("DATABASE NOT INITIALIZED !! PUT IN: Common.initDB() TO SETUP DATABASE");
        assert list != null;
        return list;
    }
    
}
