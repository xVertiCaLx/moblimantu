package controller;

import db.BookingDB;
import entity.Booking;
import entity.SeatLayout;
import entity.Showtime;
import factory.BookingFactory;
import helper.PaymentHelper;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import page.ConfirmBookingSubPage;
import utils.Common;
import utils.Constant;

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
                                       int customerAge, LinkedList<Integer> bookedSeatNumbers) {
        /* Create a booking given the data */
        Booking b = BookingFactory.createNewInstance(showtimeId, customerName, customerHP, customerEmail, customerAge, bookedSeatNumbers);
        Showtime st = ShowtimeController.getShowtimeById(b.getShowtimeId());
        /* Display the invoice to the user, confirm booking */
        boolean confirm = ConfirmBookingSubPage.getInstance().getConfirmation(b);
        if (!confirm) return false;
        
        /*********************CheckingSeat availability *************/
        SeatLayout sl = SeatLayoutController.getSeatLayoutById(st.getSeatLayoutId());
        for(Integer seat : bookedSeatNumbers) {
            int row = sl.getRow(seat.intValue());
            int col = sl.getCol(seat.intValue());
            if (sl.isSeatAvailable(row, col) == false) {
                // seat is not available
                return false;
            }
        }
        /*************** Enquiry making payment*********************/
        
        String transactionId = PaymentHelper.makePayment(b);
        if (transactionId.equalsIgnoreCase(Constant.FAILED_TRANSACTION)) return false; //transaction unsuccessful
        b.setTransactionId(transactionId);
        /***********************************************************/
        
        /* On successful, add the booking to the list */
        addBooking(b);
        
        /* Update the seat layout of show time*/
        sl = SeatLayoutController.getSeatLayoutById(st.getSeatLayoutId());
        for(Integer seat : bookedSeatNumbers) {
            int row = sl.getRow(seat.intValue());
            int col = sl.getCol(seat.intValue());
            sl.setBooked(row, col);
        }
        //sl.display();
        SeatLayoutController.updateSeatLayoutById(st.getSeatLayoutId(), sl);
        SeatLayoutController.commit();
        /**********************************************************/
        System.out.println();
        System.out.println("Your booking is confirmed. Please keep the invoice for future reference");
        ConfirmBookingSubPage.showDisplay(b);
        System.out.println();
        return true;
    }
    
    /*          Add & commit a booking to database  */
    public static void addBooking(Booking b) {
        LinkedList<Booking> list = BookingDB.getBookingList();
        list.add(b);
        BookingDB.commit();
    }
    
    /*          Get all bookings for an user            */
    public static LinkedList<Booking> getBookingByUser(String customerEmail, String customerHP) {
        LinkedList<Booking> list = BookingDB.getBookingList();
        LinkedList<Booking> result = new LinkedList<Booking>();
        for (Booking b: list){
            if (b.getCustomerEmail().compareToIgnoreCase(customerEmail) == 0 && 
                b.getCustomerHP().compareToIgnoreCase(customerHP) == 0) {
                result.add(b);
            }
        } 
        return result;        
    }
    
    public static LinkedList<Booking> getBookingHistory(String customerEmail, String customerHP) {
        LinkedList<Booking> temp = getBookingByUser(customerEmail, customerHP);
        LinkedList<Booking> result = new LinkedList<Booking>();        
        Date now = new Date();
        for (Booking b: temp) {
            Showtime st = ShowtimeController.getShowtimeById(b.getShowtimeId());            
            if (st.getTime().before(now)) 
                result.add(b);
        }
        return result;
    }
    
    public static LinkedList<Booking> getBookingStatus(String customerEmail, String customerHP) {
        LinkedList<Booking> temp = getBookingByUser(customerEmail, customerHP);
        LinkedList<Booking> result = new LinkedList<Booking>();        
        Date now = new Date();
        for (Booking b: temp) {
            Showtime st = ShowtimeController.getShowtimeById(b.getShowtimeId());
            if (st.getTime().after(now)) 
                result.add(b);
        }
        return result;
    }    
    
    /*         Return the list of booking given a showtime Id           */
    public static LinkedList<Booking> getBookingByShowtimeId(int showtimeId) {
        LinkedList<Booking> list = BookingDB.getBookingList();
        LinkedList<Booking> result = new LinkedList<Booking>();
        for(Booking b: list) 
            if (b.getShowtimeId() == showtimeId) result.add(b);
        return result;
    }
    
    /*      return the list of booking in this month        */
    public static LinkedList<Booking> getBookingInCurrentMonth() {
        LinkedList<Booking> list = BookingDB.getBookingList();
        Calendar curCal = Calendar.getInstance();
        curCal.setTime(new Date());
        return getBookingByMonth(curCal.get(Calendar.MONTH));
    }
    
    /*      return the list of booking for a specific month */
    public static LinkedList<Booking> getBookingByMonth(int month) {
        LinkedList<Booking> list = BookingDB.getBookingList();
        LinkedList<Booking> result = new LinkedList<Booking>();
        for(Booking b: list) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(b.getTime());
            if (cal.get(Calendar.MONTH) == month) result.add(b);
        }
        return result;
    }
    
    //unit test for booking controller
    public static void main(String[] args) {
        Common.initDB();
        int showtimeId = 4;
        Showtime st = ShowtimeController.getShowtimeById(showtimeId);
        int seatLayoutId = st.getSeatLayoutId();
        SeatLayout sl = SeatLayoutController.getSeatLayoutById(seatLayoutId);
        sl.display();
        LinkedList<Integer> seats = new LinkedList<Integer>();
        seats.add(new Integer(19));
        makeBooking(showtimeId,"Pham Quang Vu","96130325","ConanKudo5@gmail.com",20,seats);
        
    }
}
