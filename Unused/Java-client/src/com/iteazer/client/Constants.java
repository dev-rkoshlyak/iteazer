package com.iteazer.client;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class Constants {

    public static final int OLLIE_MIN_TIME = 50;
    public static final int OLLIE_MAX_TIME = 10000;
    public static final int OLLIE_MIN_SPEED = 0;
    public static final int OLLIE_MAX_SPEED = 255;
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

}
