/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CineplexDB;
import entity.Cineplex;
import java.util.LinkedList;

/**
 *
 * @author Vu
 */
public class CineplexController {
    
    /* Add a Cineplex to the database & database */
    public static void addCineplex(String cineplexName){
        LinkedList<Cineplex> list = new LinkedList<Cineplex>();
        Cineplex c = new Cineplex(list.size()+1, cineplexName);
        list.add(c);
        CineplexDB.commit();
    }
    /* Get a specific Cineplex with given Id */
    public static Cineplex getCineplexById(int Id) {
        LinkedList<Cineplex> list = new LinkedList<Cineplex>();
        for(Cineplex c : list) {
            if (c.getId() == Id)  return c;
        }
        return null;
    }
}
