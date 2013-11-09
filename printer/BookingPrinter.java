package printer;

import controller.CinemaController;
import controller.CineplexController;
import controller.MovieController;
import controller.ShowtimeController;
import entity.Booking;
import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import entity.Showtime;
import helper.DateHelper;
import java.util.LinkedList;

public class BookingPrinter implements Printer {
    private static final BookingPrinter INSTANCE = new BookingPrinter();
    private BookingPrinter(){}
    public static BookingPrinter getInstance() {
        return INSTANCE;
    }

    @Override
    public void printList(LinkedList list) {
        int index = 0;
        for (Object o: list) {
           Booking booking = (Booking)o;
           Showtime st = ShowtimeController.getShowtimeById(booking.getShowtimeId());
           Movie mv = MovieController.getMovieById(st.getMovieId());
           Cinema cn = CinemaController.getCinemaById(st.getCinemaId());
           Cineplex cnp = CineplexController.getCineplexById(cn.getCineplexId());
           System.out.print(++index + ". ");
           System.out.println("BookingId:\t" + booking.getId() + "\tMovie:\t" + mv.getName() + "\tCinema:\t" + 
                   cn.getName()+ "(" + cnp.getName() + ")");
        }
    }

    @Override
    public void printInstance(Object o) {
        Booking b = (Booking)o;
        Showtime st = ShowtimeController.getShowtimeById(b.getShowtimeId());
        Cinema c = CinemaController.getCinemaById(st.getCinemaId());
        Cineplex cx = CineplexController.getCineplexById(c.getCineplexId());
        Movie m = MovieController.getMovieById(st.getMovieId());
        System.out.println("=========================================");
        System.out.println("\tBOOKING INVOICE ");
        System.out.println("Booking Id: \t" + b.getId());
        if (b.isPaid()) {
            System.out.println("Transaction Id:\t" + b.getTransactionId());
        }        
        System.out.println("Booking time: \t" + DateHelper.getDateStringFormat(b.getTime()));
        System.out.println("Customer: \t" + b.getCustomerName());
        System.out.println("Email: \t\t" + b.getCustomerEmail());
        System.out.println("Movie name: \t" + m.getName());
        System.out.println("Cineplex: \t" + cx.getName());
        System.out.println("Cinema: \t" + c.getName());
        System.out.println("Showing time: \t" + st.getTimeStringFormat());
        System.out.print("Seat: \t\t");
        LinkedList<Integer> seats = b.getSeatNumbers();
        boolean firstSeat = true;
        for(Integer seat : seats) {
            if (firstSeat) System.out.print(seat.toString());
            else System.out.print("," + seat.toString());
            firstSeat = false;
        }
        System.out.println();
        System.out.println("Price: \t\t" + b.getPrice() + " SGD");
        System.out.println("=========================================");        
    }
}
