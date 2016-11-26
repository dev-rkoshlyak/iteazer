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
    private final ResultProcessor resultProcessor = new ResultProcessor();

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
        return processCommand(team.getServerAddress(), team.getDroidMAC(), command);
    }

    private String processCommand(String serverAddress, String macAddress, String[] command) {
        Command cmd = parser.parse(command);
        String result = executor.execute(cmd, serverAddress, macAddress);
        return processResult(cmd, result);
    }

    private String processResult(Command command, String result) {
        if (result == null) {
            return UNSUCCESSFUL_STATUS;
        }

        if (result.equals(UNKNOWN_COMMAND)) {
            return UNKNOWN_COMMAND;
        }

        return resultProcessor.processResult(command, result);
    }

}
