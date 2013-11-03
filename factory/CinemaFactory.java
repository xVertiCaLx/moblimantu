/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import controller.CinemaController;
import entity.Cinema;
import java.util.LinkedList;

/**
 *
 * @author Vu
 */
public class CinemaFactory {
    public static Cinema createNewInstance(String cinemaClass, String cinemaName, int cineplexId, String cinemaCode) {
        LinkedList<Cinema> list = CinemaController.getCinemaList();
        return new Cinema(list.size()+1, cinemaClass, cinemaName, cineplexId, cinemaCode);        
    }
}
