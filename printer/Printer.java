/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package printer;

import java.util.LinkedList;

/**
 *
 * @author Khach
 */
public interface Printer {
    public void printList(LinkedList list);
    public void printInstance(Object o);
}
