package controller;

import db.SeatLayoutDB;
import entity.SeatLayout;
import java.util.LinkedList;
import utils.Constant;

public class SeatLayoutController {
    public static char[][] getSeatLayout(int templateLayoutId) {
        return SeatLayoutDB.getSeatLayout(Constant.DATABASE_PATH + Constant.SEAT_TEMPLATE[templateLayoutId]);
    }
    public static LinkedList<SeatLayout> getSeatLayoutList() {
        return SeatLayoutDB.getSeatLayoutList();
    }
    
    public static SeatLayout getSeatLayoutById(int seatLayoutId) {
        LinkedList<SeatLayout> list = SeatLayoutDB.getSeatLayoutList();
        for(SeatLayout sl : list) {
            if (sl.getSeatLayoutId() == seatLayoutId) return sl;
        }
        return null;
    }
    
    public static void updateSeatLayoutById(int seatLayoutId, SeatLayout newLayout) {
        LinkedList<SeatLayout> list = SeatLayoutDB.getSeatLayoutList();
        for(SeatLayout sl : list) {
            if (sl.getSeatLayoutId() == seatLayoutId) {
                sl.setAllSeats(newLayout.getAllSeats());
            }
        }
        SeatLayoutDB.commit();
    }
    
    public static void addSeatLayout(SeatLayout sl) {
        SeatLayoutDB.addSeatLayout(sl);
        SeatLayoutDB.commit();
    }
    public static void commit() {
        SeatLayoutDB.commit();
    }
}
