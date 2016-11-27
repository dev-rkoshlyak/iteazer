package com.iteazer.serverollie.helpers;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class TimeHelper {

    public boolean wait(int time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
            return true;
        } catch (InterruptedException ex) {
            System.err.println("Exception during waiting");
            System.err.println(ex.getMessage());
        }
        return false;
    }

}
