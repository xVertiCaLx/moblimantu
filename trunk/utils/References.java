package utils;

import java.util.Scanner;

public class References {
    private static Scanner inputStream;

    public static Scanner getInputStream() {
        return inputStream;
    }

    public static void setInputStream(Scanner inputStream) {
        References.inputStream = inputStream;
    }
    
}
