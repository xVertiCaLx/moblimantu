/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Staff;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Vu
 */
public class StaffDB {
    private static LinkedList<Staff> list;
    
    /* Load the movie database into list */
    public static void loadDB(String filename) {
        try {
            list = new LinkedList<Staff>();
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                
                StringTokenizer s = new StringTokenizer(sc.nextLine(),"|");
                /* 
                 * Staff input format
                 * username, format
                 */
                
                String username = s.nextToken();
                String password = s.nextToken();
                list.add(new Staff(username,password));
            }
        } catch (IOException e) {
            System.out.println("IOException at movieDB " + e.getMessage());
        }
    }
    
    public static LinkedList<Staff> getStaffList() {
        if (list == null) System.out.println("DATABASE NOT INITIALIZED !! PUT IN: Common.initDB() TO SETUP DATABASE");
        assert list != null;
        return list;
    }
}
