package factory;

import entity.Booking;
import helper.PriceHelper;
import java.util.Date;
import java.util.LinkedList;
import utils.Common;

public class BookingFactory {
    
    public static Booking createNewInstance(int showtimeId, String customerName, 
                                            String customerHP, String customerEmail, 
                                            int customerAge, LinkedList<Integer> seatNumbers) {
        /* Generate unique booking id */
        int bookingId = Common.genBookingId();
        
        /* Later this field is replaced by payment method*/
        String transactionId = "NOT_AVAILABLE_YET";
        
        Date time = new Date();
        
        /* Calculate the price */
        double price = PriceHelper.calculatePrice(showtimeId, customerAge) * seatNumbers.size();
        
        return new Booking(bookingId, transactionId, showtimeId, customerName,
                            customerHP, customerEmail, customerAge, time, seatNumbers, price);
    } 
}
