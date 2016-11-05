package com.iteazer.logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    static int currentRound;

    public static int getCurrentRound() {
        return currentRound;
    }

    public static void setCurrentRound(int currentRound) {
        Contest.currentRound = currentRound;
    }

    static Map<String, Path> currentSubmission;

    static {
        results = new Results();
        name = "Ollie contest";
        finalSubmitStatus = -1;
        currentSubmission = new TreeMap<>();
        currentRound = 0;
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

    public static void addCurrentSubmission(Team team, Path path) {
        currentSubmission.put(team.getName(), path);
    }

    public static Path getCurrentSubmissionPath(String teamName) {
        if (currentSubmission.containsKey(teamName)) {
            return currentSubmission.get(teamName);
        }
        return null;
    }

    public static Path getCurrentSubmissionPath(Team team) {
        return getCurrentSubmissionPath(team.getName());
    }

    public static String getCurrentSubmission(String teamName) {
        Path path = getCurrentSubmissionPath(teamName);
        return PathHelper.readFromFile(path);
    }

    public static void removeCurrentSubmission(String teamName) {
        currentSubmission.put(teamName, null);
    }

    public static List<String> getTeamsThatSubmited() {
        List<String> teams = new LinkedList<>();
        for (String team : currentSubmission.keySet()) {
            if (currentSubmission.get(team) != null) {
                teams.add(team);
            }
        }
        return teams;
    }

}
