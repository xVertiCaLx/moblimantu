/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import controller.SeatLayoutController;
import entity.SeatLayout;
import java.util.LinkedList;

/**
 *
 * @author Vu
 */
public class SeatLayoutFactory {
    public static SeatLayout createNewInstance(int templateLayoutId) {
        char[][] seats = SeatLayoutController.getSeatLayout(templateLayoutId);
        LinkedList<SeatLayout> list = SeatLayoutController.getSeatLayoutList();
        return new SeatLayout(list.size()+1, templateLayoutId, seats);
    }
}
