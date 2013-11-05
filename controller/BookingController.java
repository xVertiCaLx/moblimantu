/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.BookingDB;
import entity.Booking;
import factory.BookingFactory;
import java.util.LinkedList;

/**
 *
 * @author Vu
 */
public class BookingController {
    
    /* Return a specific booking with given Id */
    public static Booking getBookingById(int Id) {
        LinkedList<Booking> list = new LinkedList<Booking>();
        for(Booking b : list) {
            if (b.getId() == Id) return b;
        }
        return null;
    }
    
    /* IMPORTANT FUNCTION: MAKE A BOOKING */
    public static boolean makeBooking(int showtimeId, String customerName, 
                                       String customerHP, String customerEmail, 
                                       int customerAge, LinkedList<Integer> seatNumbers) {
        /* Create a booking given the data */
        Booking b = BookingFactory.createNewInstance(showtimeId, customerName, customerHP, customerEmail, customerAge, seatNumbers);
        
        /********************NOT IMPLEMENTED YET********************/
        /* Display the invoice to the user, confirm booking */
        
        /***********************************************************/
        
        
        /*******************NOT IMPLEMENTED YET*********************/
        /* Enquiry making payment*/
        
        /***********************************************************/
        
        /* On successful, add the booking to the list */
        addBooking(b);
        return true;
    }
    
    /*          Add & commit a booking to database  */
    public static void addBooking(Booking b) {
        LinkedList<Booking> list = BookingDB.getBookingList();
        list.add(b);
        BookingDB.commit();
    }
    
    /*          Get all bookings for an user            */
    public static LinkedList<Booking> getBookingByUser(String customerName, String customerEmail, String customerHP) {
        LinkedList<Booking> list = BookingDB.getBookingList();
        LinkedList<Booking> result = new LinkedList<Booking>();
        for (Booking b: list){
            if (b.getCustomerName().compareToIgnoreCase(customerName) == 0 &&
                b.getCustomerEmail().compareToIgnoreCase(customerEmail) == 0 && 
                b.getCustomerHP().compareToIgnoreCase(customerHP) == 0) {
                result.add(b);
            }
        } 
        return result;        
    }
    
    public static LinkedList<Booking> getBookingHistory(String customerName, String customerEmail, String customerHP) {
        return null;
    }
    
    public static LinkedList<Booking> getBookingStatus(String customerName, String customerEmail, String customerHP) {
        return null;
    }    
    
    /*         Return the list of booking given a showtime Id           */
    public static LinkedList<Booking> getBookingByShowtimeId(int showtimeId) {
        LinkedList<Booking> list = BookingDB.getBookingList();
        LinkedList<Booking> result = new LinkedList<Booking>();
        for(Booking b: list) 
            if (b.getShowtimeId() == showtimeId) result.add(b);
        return result;
    }
}
