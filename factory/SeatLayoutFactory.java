/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import controller.SeatLayoutController;
import entity.SeatLayout;
import java.util.LinkedList;
import utils.Common;

/**
 *
 * @author Vu
 */
public class SeatLayoutFactory {
    public static SeatLayout createNewInstance(int templateLayoutId) {
        char[][] seats = SeatLayoutController.getSeatLayout(templateLayoutId);
        return new SeatLayout(Common.genSeatLayoutId(), templateLayoutId, seats);
    }
}
