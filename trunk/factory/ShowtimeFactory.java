package factory;

import controller.CinemaController;
import controller.SeatLayoutController;
import controller.ShowtimeController;
import entity.SeatLayout;
import entity.Showtime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import utils.Common;

public class ShowtimeFactory {
    
    /* 
     * Create new Showtime instance
     * -> a new SeatLayout object will be created
     */
    public static Showtime createNewInstance(Date showtimeTime, int showtimeMovieId, int showtimeCinemaId) {
        LinkedList<Showtime> list = ShowtimeController.getShowtimeList();
        /* Find corresponding template layout Id for Showtime */
        int templateLayoutId = CinemaController.getCinemaById(showtimeCinemaId).getLayoutTemplateId();
        /* Create a seatLayout for corresponding showtime Id */
        SeatLayout newSeatLayout = SeatLayoutFactory.createNewInstance(templateLayoutId);
        SeatLayoutController.addSeatLayout(newSeatLayout);
        SeatLayoutController.commit();
        
        return new Showtime(Common.genShowtimeId(),showtimeTime, showtimeMovieId, showtimeCinemaId, newSeatLayout.getSeatLayoutId());
    }
    
    /* 
     * Create new Showtime instance
     * -> a new SeatLayout object will be created
     */
    public static Showtime createNewInstance(String showtimeTimeStringFormat, int showtimeMovieId, int showtimeCinemaId) {
        try {
            LinkedList<Showtime> list = ShowtimeController.getShowtimeList();
            /* Find corresponding template layout Id for Showtime */
            int templateLayoutId = CinemaController.getCinemaById(showtimeCinemaId).getLayoutTemplateId();
            
            /* Create a seatLayout for corresponding showtime Id */
            SeatLayout newSeatLayout = SeatLayoutFactory.createNewInstance(templateLayoutId);
            
            SeatLayoutController.addSeatLayout(newSeatLayout);
            SeatLayoutController.commit();
            Date showtimeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(showtimeTimeStringFormat);
            return new Showtime(Common.genShowtimeId(),showtimeTime, showtimeMovieId, showtimeCinemaId, newSeatLayout.getSeatLayoutId());
        } catch (Exception ex) {
            System.out.println("Exception in create new instance Showtime Factory" + ex.getMessage());
        }
        return null;
    }
    public static Showtime clone(Showtime showtime) {
        Showtime newShowtime = new Showtime(showtime.getId(), showtime.getTime(), showtime.getMovieId(), showtime.getCinemaId(), showtime.getSeatLayoutId());
        return newShowtime;
    }
}
