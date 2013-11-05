package controller;

import db.CineplexDB;
import entity.Cineplex;
import factory.CineplexFactory;
import java.util.LinkedList;

public class CineplexController {
    
    /* Add a Cineplex to the database & database */
    public static void addCineplex(String cineplexName){
        LinkedList<Cineplex> list = new LinkedList<Cineplex>();
        Cineplex c = CineplexFactory.createNewInstance(cineplexName);
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
    
    /* Return the Cineplex list */
    public static LinkedList<Cineplex> getCineplexList() {
        return CineplexDB.getCineplexList();
    }
}
