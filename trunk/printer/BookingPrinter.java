package printer;

import java.util.LinkedList;

public class BookingPrinter implements Printer {
    private static final BookingPrinter INSTANCE = new BookingPrinter();
    private BookingPrinter(){}
    public static BookingPrinter getInstance() {
        return INSTANCE;
    }

    @Override
    public void printList(LinkedList list) {
        for (Object booking: list) {
            System.out.println();
        }
    }

    @Override
    public void printInstance(Object o) {
        
    }
    
}
