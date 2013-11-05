/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Vu
 */
public class DateHelper {
    private static LinkedList<Date> publicHolidays;
    
    public static void commit() {
        try {
            PrintWriter pw = new PrintWriter(new File(Constant.DATABASE_PATH+Constant.PUBLIC_HOLIDAY_DATABASE));
            for(Date d: publicHolidays) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                pw.write(dateFormatter.format(d.getTime())); pw.write("\r\n");
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("IOException in commit public holiday");
        }
    }
    
    public static void initPublicHolidays() {
        try {
            publicHolidays = new LinkedList<Date>();
            Scanner sc = new Scanner(new File(Constant.DATABASE_PATH+Constant.PUBLIC_HOLIDAY_DATABASE));
            while (sc.hasNext()) {
                String s = sc.nextLine();
                publicHolidays.add(new SimpleDateFormat("yyyy-MM-dd").parse(s));
            }
        } catch (IOException e) {
            System.out.println("IOException in getPublicHolidays" + e.getMessage());
        } catch (ParseException e){
            System.out.println("Parse Exception in getPublicHolidays " + e.getMessage());
        } 
            
    }
    
    public static void addPublicHoliday(String year, String month, String day) {
        try {
            String newDate = year + "-" + month + "-" + day;
            publicHolidays.add(new SimpleDateFormat("yyyy-MM-dd").parse(newDate));
            commit();
        } catch (ParseException e) {
            System.out.println("Parse Exception in add Public Holiday" + e.getMessage());
        }
    }
    
    public static void addPublicHoliday(String newDate) {
        try {
            publicHolidays.add(new SimpleDateFormat("yyyy-MM-dd").parse(newDate));
            commit();
        } catch (ParseException e) {
            System.out.println("Parse Exception in add Public Holiday" + e.getMessage());
        }
    }
    
    public static void removePublicHoliday(String year, String month, String day) {
        try {
            String date = year + "-" + month + "-" + day;
            Date removedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            for(Date d: publicHolidays) {
                if (isSameDay(removedDate, d)) publicHolidays.remove(d);
            }
            commit();
        } catch (ParseException e) {
            System.out.println("Parse Exception in remove public holiday " + e.getMessage());
        }
    }
    
    public static void removePublicHoliday(String date) {
        try {
            Date removedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            for(Date d: publicHolidays) {
                if (isSameDay(removedDate, d)) publicHolidays.remove(d);
            }
            commit();
        } catch (ParseException e) {
            System.out.println("Parse Exception in remove public holiday " + e.getMessage());
        }
    }
    
    public static boolean isSameDay(Date dayA, Date dayB) {
        dayA = removeTime(dayA);
        dayB = removeTime(dayB);
        if (dayA.getTime() == dayB.getTime()) return true;
        else return false;
    }
    
    public static boolean isPublicHoliday(String date) {
        try {
            Date curDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            for(Date d: publicHolidays) {
                if (isSameDay(curDate, d)) return true;
            }
        } catch (ParseException e) {
            System.out.println("Parse Exception in is public holiday " + e.getMessage());
        }
        return false;
    }
    
    public static boolean isPublicHoliday(String year, String month, String day) {
        try {
            String newDate = year + "-" + month + "-" + day;
            Date curDate = new SimpleDateFormat("yyyy-MM-dd").parse(newDate);
            for(Date d: publicHolidays) {
                if (isSameDay(curDate, d)) return true;
            }
        } catch (ParseException e) {
            System.out.println("Parse Exception in is public holiday " + e.getMessage());
        }
        return false;
    }
    
    public static boolean isPublicHoliday(Date date) {
        Date curDate = removeTime(date);
        for(Date d: publicHolidays) {
            if (isSameDay(date, d)) return true;
        }
        return false;
    }
    /* Remove timing attribute */
    public static Date removeTime(Date date) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String d = dateFormatter.format(date.getTime());
            return new SimpleDateFormat("yyyy-MM-dd").parse(d);
        } catch (ParseException e) {
            System.out.println("Parse Exception in remove Date");
        }
        return null;
    }
    
    public static LinkedList<Date> getPublicHolidays() {
        return publicHolidays;
    }
    
    //unit test for public holiday
    public static void main(String[] args) {
        Common.initDB();
      //  addPublicHoliday("2008-12-28");
        addPublicHoliday("2005","10","12");
        System.out.println(isPublicHoliday("2008-12-28"));
        System.out.println(isPublicHoliday("2008-12-25"));
        System.out.println(isPublicHoliday("2005-10-12 20:10:10"));
        removePublicHoliday("2005","10","12");
        System.out.println(isPublicHoliday("2005-10-12"));
    }
}
