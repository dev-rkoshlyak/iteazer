package com.iteazer.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.iteazer.logic.Constants.*;
import java.io.Serializable;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class Team implements Serializable {

    static Map<String, Team> allTeams;

    static {
        allTeams = new TreeMap<>();
        readTeams("teams.txt");
        System.out.println("loaded information about " + allTeams.size() + " teams");
    }

    String name;
    // very bad idea to store password here
    private String password;
    private String droidMAC;
    private String serverAddress;

    static void readTeams(String inputFileName) {
        try {
            String projectFolder = PathHelper.getProjectHomeFolder();
            Path filePath = Paths.get(projectFolder + '/' + inputFileName);
            if (Files.exists(filePath)) {
                Files.lines(filePath).forEach(s -> new Team(s));
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Couldn't read teams");
        }
    }

    private void init(String name, String password, String droidMAC, String serverAddress) {
        this.name = name;
        this.password = password;
        this.droidMAC = droidMAC;
        this.serverAddress = serverAddress;

        allTeams.put(name, this);
    }

    public Team(String name, String password) {
        init(name, password, "", "");
    }

    public Team(String s) {
        String str[] = s.split(" |\t");
        init(str[0], str[1], str[2], str[3]);
    }

    public static Team getTeam(String name) {
        return allTeams.get(name);
    }

    public boolean equals(Team team2) {
        return name.equals(team2.name);
    }

    static List<Team> getAllTeams() {
        return (List<Team>) allTeams.values();
    }

    public int doSubmition(String commands) {
        return doSubmition(commands.split("\\r?\\n"), false, 0);
    }

    public int doFinalSubmition(String commands, int roundN) {
        return doSubmition(commands.split("\\r?\\n"), true, roundN);
    }

    private int doSubmition(String[] commands, boolean isFinal, int roundN) {
        int time = OllieManager.performCommandsOllie(serverAddress, droidMAC, commands);
        if (!isFinal) {
            return time;
        }

        Results res = Contest.getResults();
        res.addAttempt(this, time, roundN);
        return time;
    }

    public String getName() {
        return name;
    }

    public void rollStopOnCalibrationOllie(int direction) {
        OllieManager.rollOllie(serverAddress, droidMAC, 0, direction);
        OllieManager.wait(WAIT_ON_CALIBRATION);

        OllieManager.rollOllie(serverAddress, droidMAC, SPEED_ON_CALIBRATION, direction);
        OllieManager.wait(ROLL_ON_CALIBRATION);

        OllieManager.rollOllie(serverAddress, droidMAC, 0, direction);
        OllieManager.wait(2 * WAIT_ON_CALIBRATION);
    }

    public void calibrateOllie(int newShift) {
        if (newShift < OLLIE_MIN_HEADING || newShift > OLLIE_MAX_HEADING) {
            newShift = OLLIE_MIN_HEADING;
        }

        OllieManager.setShift(droidMAC, newShift);

        OllieManager.connectToOllie(serverAddress, droidMAC);

        rollStopOnCalibrationOllie(0);
        rollStopOnCalibrationOllie(180);

        OllieManager.rollOllie(serverAddress, droidMAC, 0, newShift);
        OllieManager.wait(WAIT_ON_CALIBRATION);
        OllieManager.rollOllie(serverAddress, droidMAC, 0, newShift);
    }

    public int getCurrentShift() {
        return OllieManager.getShift(droidMAC);
    }

    public boolean matchesPassword(String s) {
        return s.equals(password);
    }

    public static boolean exists(String name) {
        return allTeams.get(name) != null;
    }
}
