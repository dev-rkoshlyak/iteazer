package com.iteazer.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class Contest {

    static String name;
    static Results results;
    /**
     * <li>-1 - disabled </li>
     * <li> [0 .. MAX_ROUND-1] - round i+1 </li>
     */
    static int finalSubmitStatus;

    static {
        results = new Results();
        name = "Ollie contest";
        finalSubmitStatus = -1;
    }

    public static Results getResults() {
        return results;
    }

    public static int finalSubmit() {
        return finalSubmitStatus;
    }

    public static void setFinalSubmitStatus(int status) {
        finalSubmitStatus = status;
    }

    public static void serializeResults() {
        FileOutputStream fos = null;
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            System.out.println();
            fos = new FileOutputStream(PathHelper.getProjectHomeFolder() + "/logs/results_" + sdf.format(cal.getTime()) + ".txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(results);
            oos.flush();
            oos.close();
        } catch (Exception ex) {

        }
    }

    public static void deserializedResultr(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream oin = new ObjectInputStream(fis);
            results = (Results) oin.readObject();
        } catch (Exception ex) {

        }
    }
}
