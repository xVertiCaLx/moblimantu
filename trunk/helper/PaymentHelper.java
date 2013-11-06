package helper;

import controller.CinemaController;
import controller.ShowtimeController;
import entity.Booking;
import entity.Cinema;
import entity.Showtime;
import java.text.SimpleDateFormat;
import java.util.Date;
import utils.Constant;

public class PaymentHelper {
    public static String makePayment(Booking booking) {
        System.out.println("Processing Payment ...");
        if (paymentResult(booking)) {
            System.out.println("Payment Successfully!");
            return makeTransactionId(booking);
        } else {
            System.out.println("Payment Failed!");
            return Constant.FAILED_TRANSACTION;
        }
    }
    
    public static boolean paymentResult(Booking booking) {
        return true;
    }
    
    public static String makeTransactionId(Booking booking) {
        Showtime st = ShowtimeController.getShowtimeById(booking.getShowtimeId());
        Cinema cn = CinemaController.getCinemaById(st.getCinemaId());
        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmm");
        return cn.getCinemaCode() + dateFormatter.format(now);
    }
}
