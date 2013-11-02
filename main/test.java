/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Khach
 */
public class test {
    public static void main2(String[] args) {
          Date now = new Date();
          SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, yyyy-MM-dd HH:mm:ss");
          System.out.println(dateFormatter.format(now));
    }
}
