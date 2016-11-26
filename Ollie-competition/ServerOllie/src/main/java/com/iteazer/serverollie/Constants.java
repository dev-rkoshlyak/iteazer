package com.iteazer.serverollie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class Constants {

    public static final int SERVER_PORT_ID = 32_321;


    public static final int OLLIE_MIN_TIME = 50;
    public static final int OLLIE_MAX_TIME = 10000;
    public static final int OLLIE_MIN_SPEED = 0;
    public static final int OLLIE_MAX_SPEED = 255;
    public static final int OLLIE_MIN_HEADING = 0;
    public static final int OLLIE_MAX_HEADING = 359;


    public static final String PROJECT_CAPTION = "ServerOllie";

    public static final String ENCODING = "utf-8";
    public static final String FILE_TEAMS_INFO = "teams.txt";

    /**
     * returns after successful command execution
     */
    public static final String SUCCESSFUL_STATUS = "success";

    /**
     * returns after UNsuccessful command execution
     */
    public static final String UNSUCCESSFUL_STATUS = "fail";

    /**
     * separator after status
     */
    public static final String RESULT_SEPARATOR = "\t";

    public static final String UNKNOWN_COMMAND = "unknownParameter";

    public static final String COMMAND_CONNECT = "connect";

    public static final String COMMAND_MOVE = "roll";
    public static final String COMMAND_PARAMETER_DIRECTION = "direction";
    public static final String COMMAND_PARAMETER_SPEED = "speed";


    /**
     *
     * all possible commands for ollie:
     * <ul>
     * <li> connect - "move direction speed time" </li>
     * <li> roll - "roll direction speed time". For example, "roll 359 255 50" </li>
     * </ul>
     */
    public static final Set<String> OLLIE_COMMANDS
            = new HashSet<>(Arrays.asList(COMMAND_CONNECT, COMMAND_MOVE));

    public static final String SUCCESSFUL_AUTH_MSG = "Loged in!";
}
