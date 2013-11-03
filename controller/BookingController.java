/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Booking;
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
    
    public static LinkedList<Booking> getBookingHistory(String email, String handPhone, String bookingRef) {
        return null;
    }
    public static LinkedList<Booking> getBookingStatus(String email, String handPhone, String bookingRef) {
        return null;
    }    
}
