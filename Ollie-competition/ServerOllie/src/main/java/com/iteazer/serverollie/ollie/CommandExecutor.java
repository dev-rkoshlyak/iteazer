package com.iteazer.serverollie.ollie;

import static com.iteazer.serverollie.Constants.*;
import com.iteazer.serverollie.helpers.HttpHelper;
import com.iteazer.serverollie.helpers.TimeHelper;

/**
 *
 * @author Wsl_F@ITeazer
 */
class CommandExecutor {

    private static CommandValidator cmdValidator = new CommandValidator();
    private static HttpHelper httpHelper = new HttpHelper();
    private static TimeHelper timeHelper = new TimeHelper();

    String execute(Command command, String serverAddress, String macAddress) {
        String result;
        if (cmdValidator.validate(command)) {
            String executionString = command.toExecutionString();
            result = httpHelper.sendHttpRequest(serverAddress + executionString + "MAC=" + macAddress);
            int duration = command.getWaitAfter();
            if (duration != 0) {
                if (!timeHelper.wait(duration)) {
                    result = null;
                }
            }
        } else {
            result = UNKNOWN_COMMAND;
        }

        return result;
    }
}
