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
            if (number.startsWith("0x")) {
                try {
                    result = Integer.parseInt(number.substring(2), 16);
                } catch (Exception exc) {

                }
            }
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
            case COMMAND_SET_COLOR:
                cmd = parseSetColorCommand(cmd, command);
                break;
            case COMMAND_CONNECT:
            case COMMAND_GET_VELOCITY:
            case COMMAND_GET_ACCEL_ONE:
            case COMMAND_GET_ACCELEROMETER:
            case COMMAND_GET_GYROSCOPE:
            case COMMAND_GET_IMU_ANGLES:
            case COMMAND_GET_MOTORS:
            case COMMAND_GET_ODOMETER:
                break;
            default:
                cmd = null;
        }

        return cmd;
    }

    private Command parseMoveCommand(Command cmd, String[] command) {
        if (command.length != 4 && command.length != 3) {
            return null;
        }

        Integer direction = parseInt(command[1]);
        cmd.addParamter(COMMAND_PARAMETER_DIRECTION, direction);
        Integer speed = parseInt(command[2]);
        cmd.addParamter(COMMAND_PARAMETER_SPEED, speed);
        Integer duration = (command.length == 4) ? parseInt(command[3]) : 0;
        if (duration == null) {
            cmd = null;
        } else {
            cmd.setWaitAfter(duration);
        }

        return cmd;
    }

    private Command parseSetColorCommand(Command cmd, String[] command) {
        if (command.length != 2) {
            return null;
        }

        Integer color = parseInt(command[1]);
        cmd.addParamter(COMMAND_PARAMTER_COLOR, color);

        cmd.setWaitAfter(OLLIE_COMMAND_TIMEOUT);
        return cmd;
    }
}
