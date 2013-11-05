package factory;

import controller.SeatLayoutController;
import entity.SeatLayout;
import java.util.LinkedList;
import utils.Common;

public class SeatLayoutFactory {
    public static SeatLayout createNewInstance(int templateLayoutId) {
        char[][] seats = SeatLayoutController.getSeatLayout(templateLayoutId);
        return new SeatLayout(Common.genSeatLayoutId(), templateLayoutId, seats);
    }
}
