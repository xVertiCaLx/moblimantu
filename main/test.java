/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Khach
 */
public class test {
    public static void main(String[] args) throws ParseException {
          Date now = new Date();
          SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          System.out.println(dateFormatter.format(now));
          System.out.println(now.toString());
          now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-11-05 02:03:04");
          System.out.println(dateFormatter.format(now));          
    }
}
