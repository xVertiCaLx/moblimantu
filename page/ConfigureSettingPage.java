/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import java.util.Scanner;
import utils.References;

/**
 *
 * @author Khach
 */
public class ConfigureSettingPage {
    private static final ConfigureSettingPage INSTANCE = new ConfigureSettingPage();
    private Scanner sc;
    private ConfigureSettingPage(){}
    public static ConfigureSettingPage getInstance() {
        return INSTANCE;
    }
    public void launch() {
        sc = References.getInputStream();
        do {
            System.out.println("Configure system setting page");
            System.out.println("1. Change ticket price");
            System.out.println("2. Set up holiday");
        } while (true);
    }
}
