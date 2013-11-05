package entity;

public class SeatLayout {
    private int seatLayoutId;
    private int layoutTemplate;
    private char[][] seats;
    
    public static final char SEAT_BOOKED = 'B';
    public static final char SEAT_AVAILABLE = 'A';
    public static final char NOT_A_SEAT = 'N';
    public static final char INVALID_SEAT = 'I';
    
    public SeatLayout(int seatLayoutId, int layoutTemplate, char[][] seats) {
        this.seatLayoutId = seatLayoutId;
        this.layoutTemplate = layoutTemplate;
        this.seats = seats;
    }
    public int getSeatLayoutId() {
        return seatLayoutId;
    }
    
    public int getLayoutTemplate() {
        return layoutTemplate;
    }
    
    public char[][] getAllSeats() {
        return seats;
    }

    public void setSeatLayoutId(int seatLayoutId) {
        this.seatLayoutId = seatLayoutId;
    }

    public void setLayoutTemplate(int layoutTemplate) {
        this.layoutTemplate = layoutTemplate;
    }
    public void setAllSeats(char[][] seats) {
        this.seats = seats;
    }
    
    public int getLength() {
        return seats.length;
    }
    public int getWidth() {
        if (seats.length == 0) return 0;
        else return seats[0].length;
    }
    public char getSeat(int X, int Y) {
        if (seats.length == 0) return INVALID_SEAT;
        if (0 <= X && X < seats.length && 0 <= Y && Y < seats[0].length) return seats[X][Y];
        else return INVALID_SEAT;
    }
    
    public boolean isSeatBooked(int X, int Y) {
        return getSeat(X,Y) == SEAT_BOOKED;
    }
    public boolean isSeatAvailable(int X, int Y) {
        return getSeat(X,Y) == SEAT_AVAILABLE;
    }
    public boolean isNotASeat(int X, int Y) {
        return getSeat(X,Y) == NOT_A_SEAT;
    }
    public boolean isFullyBooked() {
        for(int i = 0; i < seats.length; i++)
            for(int j = 0; j < seats[0].length; j++) {
                if (getSeat(i,j) == SEAT_AVAILABLE) return false;
            }
        return true;
    }
    
    public void setBooked(int X, int Y) {
        seats[X][Y] = SEAT_BOOKED;
    }
    public void setAvailable(int X, int Y) {
        seats[X][Y] = SEAT_AVAILABLE;
    }
    
    /**********IMPLEMENTED THIS ONE TO SUPPORT UNIT TEST ***********/
    public void display() {
        
    }
}
