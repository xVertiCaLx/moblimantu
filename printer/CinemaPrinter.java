package printer;

import entity.Cinema;
import java.util.LinkedList;

public class CinemaPrinter implements Printer {
    private static final CinemaPrinter INSTANCE = new CinemaPrinter();
    private CinemaPrinter(){}
    public static CinemaPrinter getInstance() {
        return INSTANCE;
    }

    @Override
    public void printList(LinkedList list) {
        for (Object cinema: list) {
            System.out.println(((Cinema)cinema).getId() + ". " + ((Cinema)cinema).getName());
        }
    }

    @Override
    public void printInstance(Object o) {
        
    }
}
