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
    public static final int OLLIE_MIN_POWER = -4095;
    public static final int OLLIE_MAX_POWER = 4095;

    public static final int OLLIE_COMMAND_TIMEOUT = 0;

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
    public static final String RESULT_SEPARATOR = " ";

    public static final String UNKNOWN_COMMAND = "unknownParameter";

    public static final String COMMAND_CONNECT = "connect";

    public static final String COMMAND_MOVE = "roll";
    public static final String COMMAND_PARAMETER_DIRECTION = "direction";
    public static final String COMMAND_PARAMETER_SPEED = "speed";

    public static final String COMMAND_GET_VELOCITY = "getVelocity";

    public static final String COMMAND_SET_COLOR = "setColor";
    public static final String COMMAND_PARAMTER_COLOR = "color";

    public static final String COMMAND_SET_RAW_MOTORS = "setRawMotors";
    public static final String COMMAND_PARAMETER_LEFT = "left";
    public static final String COMMAND_PARAMETER_RIGHT = "right";

    public static final String COMMAND_SET_STABILIZATION = "setStabilization";
    public static final String COMMAND_PARAMETER_STABILIZATION = "stabilization";

    public static final String COMMAND_GET_ACCEL_ONE = "getAccelOne";

    public static final String COMMAND_GET_ACCELEROMETER = "getAccelerometer";

    public static final String COMMAND_GET_GYROSCOPE = "getGyroscope";

    public static final String COMMAND_GET_IMU_ANGLES = "getImuAngles";

    public static final String COMMAND_GET_MOTORS = "getMotorsBackEmf";

    public static final String COMMAND_GET_ODOMETER = "getOdometer";

    public static final String COMMAND_GET_COLLISIONS = "getCollisions";

    public static final String COMMAND_SUBSCRIBE_SENSOR = "subscribe";
    public static final String COMMAND_UNSUBSCRIBE_SENSOR = "unsubscribe";
    public static final String COMMAND_PARAMETER_SENSOR = "sensor";

    /**
     *
     * all possible commands for Ollie:
     * <ul>
     * <li> connect to Ollie - "connect"; </li>
     * <li> move Ollie - "roll direction speed time", for example, "roll 359 255
     * 50" OR "roll direction speed", for example, "roll 180 128"; </li>
     * <li> set Ollie's color - "setColor 0xRRGGBB", for example, "setColor
     * 0xFF0000" (red color); </li>
     * <li> get Ollie's Velocity - "getVelocity"; </li>
     * <li> get Ollie's AccelOne - "getAccelOne"; </li>
     * <li> get Ollie's Accelerometer - "getAccelerometer"; </li>
     * <li> get Ollie's Gyroscope - "getGyroscope"; </li>
     * <li> get Ollie's ImuAngles - "getImuAngles"; </li>
     * <li> get Ollie's Motors - "getMotorsBackEmf"; </li>
     * <li> get Ollie's odometer - "getOdometer"; </li>
     * </ul>
     */
    public static final Set<String> OLLIE_COMMANDS
            = new HashSet<>(Arrays.asList(COMMAND_CONNECT,
                    COMMAND_MOVE, COMMAND_SET_COLOR,
                    COMMAND_SET_STABILIZATION,
                    COMMAND_SET_RAW_MOTORS,
                    COMMAND_GET_VELOCITY, COMMAND_GET_ACCEL_ONE,
                    COMMAND_GET_ACCELEROMETER, COMMAND_GET_GYROSCOPE,
                    COMMAND_GET_IMU_ANGLES, COMMAND_GET_MOTORS,
                    COMMAND_GET_ODOMETER,
                    COMMAND_GET_COLLISIONS,
                    COMMAND_SUBSCRIBE_SENSOR, COMMAND_UNSUBSCRIBE_SENSOR
            ));

    public static final String SENSOR_VELOCITY = "Velocity";

    public static final String SENSOR_ACCEL_ONE = "AccelOne";

    public static final String SENSOR_ACCELEROMETER = "Accelerometer";

    public static final String SENSOR_GYROSCOPE = "Gyroscope";

    public static final String SENSOR_IMU_ANGLES = "ImuAngles";

    public static final String SENSOR_MOTORS = "MotorsBackEmf";

    public static final String SENSOR_ODOMETER = "Odometer";

    public static final String SENSOR_COLLISIONS = "Collisions";

    public static final Set<String> OLLIE_SENSORS
            = new HashSet<>(Arrays.asList(SENSOR_VELOCITY,
                    SENSOR_ACCELEROMETER, SENSOR_ACCEL_ONE,
                    SENSOR_GYROSCOPE, SENSOR_IMU_ANGLES,
                    SENSOR_MOTORS, SENSOR_ODOMETER,
                    SENSOR_COLLISIONS
            ));

    public static final String SUCCESSFUL_AUTH_MSG = "Loged in!";
}
