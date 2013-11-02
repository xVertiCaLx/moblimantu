/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author Khach
 */
public class References {
    private static Scanner inputStream;

    public static Scanner getInputStream() {
        return inputStream;
    }

    public static void setInputStream(Scanner inputStream) {
        References.inputStream = inputStream;
    }
    
}
