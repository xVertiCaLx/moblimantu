/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import controller.CineplexController;
import entity.Cineplex;
import java.util.LinkedList;
import utils.Common;

/**
 *
 * @author Vu
 */
public class CineplexFactory {
    public static Cineplex createNewInstance(String cineplexName) {
        return new Cineplex(Common.genCineplexId(), cineplexName);
    }
}
