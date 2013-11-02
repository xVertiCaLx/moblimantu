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
    public static LinkedList<Staff> list;
    
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
    
    /* Authenticate a user*/
    public static boolean authenticate(String username, String password) {
        try {
            String original = password;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(original.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(Integer.toHexString((int) (b & 0xff)));
            }
            String digestedPassword = sb.toString();
            for(Staff s : list) {
                if (s.getPassword().compareTo(digestedPassword) == 0 && 
                    s.getUsername().compareTo(username) == 0) return true;
            }
            return false;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No MD5 algorithm to authenticate " + e.getMessage());
            return false;
        }
    }
}
