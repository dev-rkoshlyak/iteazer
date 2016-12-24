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
            case COMMAND_SET_COLOR:
                valid = checkSetColor(command);
                break;
            case COMMAND_SET_RAW_MOTORS:
                valid = checkSetRawMotors(command);
                break;
            case COMMAND_SET_STABILIZATION:
                valid = checkSetStabilization(command);
                break;
            case COMMAND_SUBSCRIBE_SENSOR:
                valid = checkSubscribeSensor(command);
                break;
            case COMMAND_UNSUBSCRIBE_SENSOR:
                valid = checkUnsubscribeSensor(command);
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

        Integer dir = command.getIntegerParameter(COMMAND_PARAMETER_DIRECTION);
        if (dir == null || dir < OLLIE_MIN_HEADING || dir > OLLIE_MAX_HEADING) {
            return false;
        }

        Integer speed = command.getIntegerParameter(COMMAND_PARAMETER_SPEED);
        if (speed == null || speed < OLLIE_MIN_SPEED || speed > OLLIE_MAX_SPEED) {
            return false;
        }

        int duration = command.getWaitAfter();
        if ((duration < OLLIE_MIN_TIME || duration > OLLIE_MAX_TIME)
                && duration != 0) {
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
            case COMMAND_GET_ACCEL_ONE:
            case COMMAND_GET_ACCELEROMETER:
            case COMMAND_GET_GYROSCOPE:
            case COMMAND_GET_IMU_ANGLES:
            case COMMAND_GET_MOTORS:
            case COMMAND_GET_ODOMETER:
            case COMMAND_GET_COLLISIONS:
                valid = true;
                break;
            default:
                valid = false;
        }

        return valid;
    }

    private boolean checkSetColor(Command command) {
        if (command == null || !command.name.equals(COMMAND_SET_COLOR)) {
            return false;
        }

        Integer color = command.getIntegerParameter(COMMAND_PARAMTER_COLOR);
        if (color == null || color < 0x00_00_00 || color > 0xFF_FF_FF) {
            return false;
        }

        return command.getParametersCount() == 1;
    }

    private boolean checkRawMotorPower(Integer power) {
        if (power == null) {
            return false;
        }
        return OLLIE_MIN_POWER <= power && power <= OLLIE_MAX_POWER;
    }

    private boolean checkSetRawMotors(Command command) {
        if (command == null || !command.name.equals(COMMAND_SET_RAW_MOTORS)) {
            return false;
        }

        Integer left = command.getIntegerParameter(COMMAND_PARAMETER_LEFT);
        Integer right = command.getIntegerParameter(COMMAND_PARAMETER_RIGHT);
        return checkRawMotorPower(left) && checkRawMotorPower(right) && command.getParametersCount() == 2;
    }

    private boolean checkSetStabilization(Command command) {
        if (command == null || !command.name.equals(COMMAND_SET_STABILIZATION)) {
            return false;
        }

        Integer stabilization = command.getIntegerParameter(COMMAND_PARAMETER_STABILIZATION);
        if (stabilization == null || (stabilization != 0 && stabilization != 1)) {
            return false;
        }

        return command.getParametersCount() == 1;
    }

    private boolean checkSubscribeSensor(Command command) {
        if (command == null || !command.name.equals(COMMAND_SUBSCRIBE_SENSOR)) {
            return false;
        }

        String sensor = command.getStringParameter(COMMAND_PARAMETER_SENSOR);
        if (sensor == null || !OLLIE_SENSORS.contains(sensor)) {
            return false;
        }

        return command.getParametersCount() == 1;
    }

    private boolean checkUnsubscribeSensor(Command command) {
        if (command == null || !command.name.equals(COMMAND_UNSUBSCRIBE_SENSOR)) {
            return false;
        }

        String sensor = command.getStringParameter(COMMAND_PARAMETER_SENSOR);
        if (sensor == null || !OLLIE_SENSORS.contains(sensor)) {
            return false;
        }

        return command.getParametersCount() == 1;
    }

}
