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
                    result = null;
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
            case COMMAND_SET_RAW_MOTORS:
                cmd = parseSetRawMotors(cmd, command);
                break;
            case COMMAND_SET_STABILIZATION:
                cmd = parseSetStabilization(cmd, command);
                break;
            case COMMAND_SUBSCRIBE_SENSOR:
            case COMMAND_UNSUBSCRIBE_SENSOR:
                cmd = parseSubscribesSecnsor(cmd, command);
                break;
            case COMMAND_CONNECT:
            case COMMAND_GET_VELOCITY:
            case COMMAND_GET_ACCEL_ONE:
            case COMMAND_GET_ACCELEROMETER:
            case COMMAND_GET_GYROSCOPE:
            case COMMAND_GET_IMU_ANGLES:
            case COMMAND_GET_MOTORS:
            case COMMAND_GET_ODOMETER:
            case COMMAND_GET_COLLISIONS:
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

    private Command parseSetRawMotors(Command cmd, String[] command) {
        if (command.length != 3) {
            return null;
        }

        try {
            Integer left = Integer.parseInt(command[1]);
            Integer right = Integer.parseInt(command[2]);
            cmd.addParamter(COMMAND_PARAMETER_LEFT, left);
            cmd.addParamter(COMMAND_PARAMETER_RIGHT, right);
            cmd.setWaitAfter(OLLIE_COMMAND_TIMEOUT);
        } catch (Exception ex) {
            cmd = null;
        }

        return cmd;
    }

    private Command parseSetStabilization(Command cmd, String[] command) {
        if (command.length != 2) {
            return null;
        }

        try {
            boolean stabilization = Boolean.parseBoolean(command[1]);
            cmd.addParamter(COMMAND_PARAMETER_STABILIZATION, stabilization ? 1 : 0);
            cmd.setWaitAfter(OLLIE_COMMAND_TIMEOUT);
        } catch (Exception ex) {
            cmd = null;
        }

        return cmd;
    }

    /**
     * parse Subscribe & UNsubscribe sensor commands
     *
     * @param cmd
     * @param command
     * @return
     */
    private Command parseSubscribesSecnsor(Command cmd, String[] command) {
        if (command.length != 2) {
            return null;
        }

        String sensor = command[1];
        cmd.addParamter(COMMAND_PARAMETER_SENSOR, sensor);

        return cmd;
    }
}
