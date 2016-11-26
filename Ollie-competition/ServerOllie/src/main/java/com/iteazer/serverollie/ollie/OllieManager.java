package com.iteazer.serverollie.ollie;

import static com.iteazer.serverollie.Constants.*;
import com.iteazer.serverollie.teams.Team;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class OllieManager {

    private final CommandExecutor executor = new CommandExecutor();
    private final CommandParser parser = new CommandParser();

    private static final OllieManager instance = new OllieManager();

    private OllieManager() {

    }

    public static OllieManager getInstance() {
        return instance;
    }

    /**
     *
     * @param team team submitted a command
     * @param command command divided into "words"
     * @return STATUS + command result
     */
    public String processCommand(Team team, String[] command) {
        return proccesCommand(team.getServerAddress(), team.getDroidMAC(), command);
    }

    private String proccesCommand(String serverAddress, String macAddress, String[] command) {
        Command cmd = parser.parse(command);
        String result = executor.execute(cmd, serverAddress, macAddress);
        return result == null ? UNSUCCESSFUL_STATUS : SUCCESSFUL_STATUS + RESULT_SEPARATOR + result;
    }

}
