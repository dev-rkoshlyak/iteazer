package com.iteazer.serverollie.ollie;

import static com.iteazer.serverollie.Constants.*;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class CommandValidator {

    boolean validate(Command command) {
        if (command == null || !OLLIE_COMMANDS.contains(command.name)) {
            return false;
        }

        boolean valid;
        switch (command.name) {
            case COMMAND_MOVE:
                valid = checkMove(command);
                break;
            case COMMAND_CONNECT:
            case COMMAND_GET_VELOCITY:
                valid = checkNoParametersCommand(command);
                break;
            default:
                valid = false;
        }

        return valid;
    }

    private boolean checkMove(Command command) {
        if (command == null || !command.name.equals(COMMAND_MOVE)) {
            return false;
        }

        Integer dir = command.getParameter(COMMAND_PARAMETER_DIRECTION);
        if (dir == null || dir < OLLIE_MIN_HEADING || dir > OLLIE_MAX_HEADING) {
            return false;
        }

        Integer speed = command.getParameter(COMMAND_PARAMETER_SPEED);
        if (speed == null || speed < OLLIE_MIN_SPEED || speed > OLLIE_MAX_SPEED) {
            return false;
        }

        int duration = command.getWaitAfter();
        if (duration < OLLIE_MIN_TIME || duration > OLLIE_MAX_TIME) {
            return false;
        }

        return command.getParametersCount() == 2;
    }

    private boolean checkNoParametersCommand(Command command) {
        if (command == null || command.getParametersCount() != 0) {
            return false;
        }

        boolean valid;
        switch (command.name) {
            case COMMAND_CONNECT:
            case COMMAND_GET_VELOCITY:
                valid = true;
                break;
            default:
                valid = false;
        }

        return valid;
    }
}