package db;

import entity.SeatLayout;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import utils.Constant;

public class SeatLayoutDB {
    private static LinkedList<SeatLayout> list;
    /* Add a new seat layout */
    public static void addSeatLayout(SeatLayout newSeatLayout) {
        list.add(newSeatLayout);
    }
    
    /* Commit the changes to the database */
    public static void commit() {
        try 
        {
            PrintWriter pw = new PrintWriter(new File(Constant.DATABASE_PATH + Constant.SEAT_LAYOUT_DATABASE));
            for(SeatLayout sl : list) {
                pw.write(new Integer(sl.getSeatLayoutId()).toString()); pw.write("|");
                pw.write(new Integer(sl.getLayoutTemplate()).toString()); pw.write("|");
                StringBuffer seatStatus = new StringBuffer();
                seatStatus.append("*");
                for(int i = 0; i < sl.getLength(); i++) {
                    for(int j = 0; j < sl.getWidth(); j++) 
                    if (sl.isSeatBooked(i, j)) {
                        seatStatus.append(i);
                        seatStatus.append("*");
                        seatStatus.append(j);
                        seatStatus.append("*");
                    }
                }
                pw.write(seatStatus.toString()); pw.write("\r\n");
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("IOException in commit MovieDB " + e.getMessage());
        } catch (Exception e) {
        
        }
    }
    /* Load the Seat layout database into list */
    public static void loadDB(String filename) {
        try {
            list = new LinkedList<SeatLayout>();
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                
                StringTokenizer s = new StringTokenizer(sc.nextLine(),"|");
                /* 
                 * Layout input format 
                 * id|tempalte_id| list of booking: seat1*seat2* ...*seat
                 */
                int seatLayoutId = Integer.parseInt(s.nextToken());
                int seatLayoutTemplateId = Integer.parseInt(s.nextToken());
                char[][] seatLayoutSeats = getSeatLayout(Constant.DATABASE_PATH+Constant.SEAT_TEMPLATE[seatLayoutTemplateId]);
                
                SeatLayout sl = new SeatLayout(seatLayoutId, seatLayoutTemplateId, seatLayoutSeats);
                if (s.hasMoreTokens()) {
                    StringTokenizer seatTaken = new StringTokenizer(s.nextToken(),"*");
                    while (seatTaken.hasMoreTokens()) {
                        int X = Integer.parseInt(seatTaken.nextToken());
                        int Y = Integer.parseInt(seatTaken.nextToken());
                        if (sl.isSeatAvailable(X, Y))
                        sl.setBooked(X, Y);
                    }
                }
                list.add(sl);
            }
        } catch (IOException e) {
            System.out.println("IOException at SeatLayoutDB " + e.getMessage());
        }
    }
    
    public static char[][] getSeatLayout(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            int height = sc.nextInt(), width = sc.nextInt();
            char[][] layout = new char[height][width];
            sc.nextLine();
            for(int i = 0; i < height; i++) {
                String s = sc.nextLine();
                for(int j = 0; j < width; j++) {
                    char c = s.charAt(j);
                    if (c == '*') layout[i][j] = SeatLayout.SEAT_AVAILABLE;
                    else layout[i][j] = SeatLayout.NOT_A_SEAT;
                }
            }
            return layout;
        } catch (IOException e) {
            System.out.println("IOException at loadSeatLayout" + e.getMessage());
        }
        return null;
    }
    /* Get the movie list */
    public static LinkedList<SeatLayout> getSeatLayoutList() {
        if (list == null) System.out.println("DATABASE NOT INITIALIZED !! PUT IN: Common.initDB() TO SETUP DATABASE");
        assert list != null;
        return list;
    }
    
    /* Unit Testing part*/
    public static void main(String[] args) {
        loadDB(Constant.DATABASE_PATH + Constant.SEAT_LAYOUT_DATABASE);
        for(SeatLayout sl : list) {
            char[][] layout = sl.getAllSeats();
            for(int i = 0; i < layout.length; i++) {
                for(int j = 0; j < layout[0].length; j++) {
                    if (sl.isSeatAvailable(i, j)) System.out.print("A");
                    else if (sl.isSeatBooked(i, j)) System.out.print("x");
                    else System.out.print(".");
                }
                System.out.println();
            }
        }
    }
}
