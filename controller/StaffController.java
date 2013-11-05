package controller;

import db.StaffDB;
import entity.Staff;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class StaffController {
    
    /* Authenticate a user*/
    public static boolean authenticate(String username, String password) {
        try {
            LinkedList<Staff> list = StaffDB.getStaffList();
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
