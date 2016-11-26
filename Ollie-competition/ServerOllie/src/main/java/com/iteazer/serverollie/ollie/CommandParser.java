package com.iteazer.serverollie.ollie;

import static com.iteazer.serverollie.Constants.*;

/**
 *
 * @author Wsl_F@ITeazer
 */
class CommandParser {

    private Integer parseInt(String number) {
        Integer result;
        try {
            result = Integer.parseInt(number);
        } catch (Exception ex) {
            result = null;
        }

        return result;
    }

    Command parse(String[] command) {
        if (command == null || command.length == 0) {
            return null;
        }

        String name = command[0];
        if (!OLLIE_COMMANDS.contains(name)) {
            return null;
        }

        Command cmd = new Command(name);
        switch (name) {
            case COMMAND_MOVE:
                cmd = parseMoveCommand(cmd, command);
                break;
            case COMMAND_CONNECT:
            case COMMAND_GET_VELOCITY:
                break;
            default:
                cmd = null;
        }

        return cmd;
    }

    private Command parseMoveCommand(Command cmd, String[] command) {
        if (command.length != 4) {
            return null;
        }

        Integer direction = parseInt(command[1]);
        cmd.addParamter(COMMAND_PARAMETER_DIRECTION, direction);
        Integer speed = parseInt(command[2]);
        cmd.addParamter(COMMAND_PARAMETER_SPEED, speed);
        Integer duration = parseInt(command[3]);
        if (duration == null) {
            cmd = null;
        } else {
            cmd.setWaitAfter(duration);
        }

        return cmd;
    }

}
