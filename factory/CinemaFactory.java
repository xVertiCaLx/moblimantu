/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import controller.CinemaController;
import entity.Cinema;
import java.util.LinkedList;
import utils.Common;

/**
 *
 * @author Vu
 */
public class CinemaFactory {
    public static Cinema createNewInstance(int cinemaClass, String cinemaName, int cineplexId, String cinemaCode, int templateLayoutId) {
        return new Cinema(Common.genCinemaId(), cinemaClass, cinemaName, cineplexId, cinemaCode, templateLayoutId);        
    }
}
