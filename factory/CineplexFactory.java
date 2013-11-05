package factory;

import controller.CineplexController;
import entity.Cineplex;
import java.util.LinkedList;
import utils.Common;

public class CineplexFactory {
    public static Cineplex createNewInstance(String cineplexName) {
        return new Cineplex(Common.genCineplexId(), cineplexName);
    }
}
