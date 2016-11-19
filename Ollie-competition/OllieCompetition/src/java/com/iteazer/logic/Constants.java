package com.iteazer.logic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class Constants {

    public static final int MAX_NUMBER_OF_ATTEMPTS = 2;
    public static final int NUMBER_OF_ROUNDS = 2;
    static final int MAX_TIME_VALUE = 1000_000_000;

    public static final int OLLIE_MIN_TIME = 50;
    public static final int OLLIE_MAX_TIME = 10000;
    public static final int OLLIE_MIN_SPEED = 0;
    public static final int OLLIE_MAX_SPEED = 255;
    public static final int OLLIE_MIN_HEADING = 0;
    public static final int OLLIE_MAX_HEADING = 359;

    public static final int OLLIE_COLOR_ON_CALIBRATION = 0;

    /**
     *
     * all possible directions for the commands:
     * <ul>
     * <li> N - North </li>
     * <li> S - South </li>
     * <li> E - East </li>
     * <li> W - West </li>
     * <li> R - Random </li>
     * </ul>
     */
    public static final Set<Character> OLLIE_DIRECTIONS
            = new HashSet<>(Arrays.asList('N', 'S', 'E', 'W'));

    public static final String CONNECTED_RESPOND = "connected";
    public static final int WAIT_ON_CALIBRATION = 500;
    public static final int ROLL_ON_CALIBRATION = 5_000;
    public static final int SPEED_ON_CALIBRATION = 30;

    public static final String PROJECT_CAPTION = "OllieCompetition";
    
    /**
     * fine for wrong command (additional time in milliseconds)
     */
    public static final int FINE_WRONG_COMMAND = 2_000;
    
    public static final String ADMIN_NAME = "Wsl_F";
}
