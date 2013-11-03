/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import controller.CineplexController;
import entity.Cineplex;
import java.util.LinkedList;

/**
 *
 * @author Vu
 */
public class CineplexFactory {
    public static Cineplex createNewInstance(String cineplexName) {
        LinkedList<Cineplex> list = CineplexController.getCineplexList();
        return new Cineplex(list.size()+1, cineplexName);
    }
}
