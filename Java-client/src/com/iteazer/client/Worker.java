package com.iteazer.client;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static com.iteazer.client.Constants.*;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class Worker {

    private static final Map<String, Integer> ollieShifts = new HashMap<>();

    public static void connectToOllie(String serverAddres, String macAddress) {
        //http://127.0.0.1:1337/ollie/disconnect?MAC=XX:XX:XX:XX:XX:XX
        String url = serverAddres + "/ollie/connect?MAC=" + macAddress;
        sendHTTPRequestWithoutResponse(url);
    }

    public static void disconnectToOllie(String serverAddres, String macAddress) {
        //http://127.0.0.1:1337/ollie/disconnect?MAC=XX:XX:XX:XX:XX:XX
        String url = serverAddres + "/ollie/disconnect?MAC=" + macAddress;
        sendHTTPRequestWithoutResponse(url);
    }

    public static void setColorOllie(String serverAddres, String macAddress,
            int red, int green, int blue) {
        //http://127.0.0.1:1337/ollie/setColor?MAC=XX:XX:XX:XX:XX:XX&r=0&g=128&b=255
        String url = serverAddres + "/ollie/setColor?MAC=" + macAddress
                + "&r=" + red + "&g=" + green + "&b=" + blue;
        sendHTTPRequestWithoutResponse(url);
    }

    public static void performCommandsOllie(String serverAddres, String macAddress,
            String[] commands) {
        for (String command : commands) {
            System.out.println("performing command: " + command);
            performCommandOllie(serverAddres, macAddress, command);
        }
    }

    public static void performCommandOllie(String serverAddres, String macAddress,
            String command) {
        char direction = getDirection(command);
        int time = getTime(command);
        int speed = getSpeed(command);

        if (direction == (char) 0 || time == -1 || speed == -1) {
            System.err.println("Unrecognized command: \"" + command + '\"');
            return;
        }

        int directionInt = getDirection(direction, macAddress);

        //http://127.0.0.1:1337/ollie/roll?MAC=XX:XX:XX:XX:XX:XX&speed=50&direction=180
        String url = serverAddres + "/ollie/roll?MAC=" + macAddress
                + "&speed=" + speed + "&direction=" + directionInt;
        sendHTTPRequestWithoutResponse(url);

        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException ex) {
            System.err.println("Exception during wait execution command: \""
                    + command + '\"');
            System.err.println(ex.getMessage());
        }

    }

    public static void setShift(String macAddress, int shift) {
        ollieShifts.put(macAddress, shift);
    }

    public static int getShift(String macAddress) {
        if (!ollieShifts.containsKey(macAddress)) {
            setShift(macAddress, 0);
        }

        return ollieShifts.get(macAddress);
    }

    static char getDirection(String command) {
        char direction = command.charAt(0);

        if (direction == 'R') {
            Random rnd = new Random();
            int i = rnd.nextInt(OLLIE_DIRECTIONS.size());
            direction = (char) OLLIE_DIRECTIONS.toArray()[i];
        }

        if (!OLLIE_DIRECTIONS.contains(direction)) {
            char dirrectionUp = Character.toUpperCase(direction);
            if (!OLLIE_DIRECTIONS.contains(dirrectionUp)) {
                System.err.println("Wrong direction! \"" + direction + '\"');
                direction = (char) 0;
            } else {
                direction = dirrectionUp;
            }
        }

        return direction;
    }

    static int getDirection(char direction, String macAddress) {
        int directionInt = -1;
        switch (direction) {
            case 'N':
                directionInt = 0;
                break;
            case 'W':
                directionInt = 90;
                break;
            case 'S':
                directionInt = 180;
                break;
            case 'E':
                directionInt = 270;
                break;
        }

        return directionInt + getShift(macAddress);
    }

    static int getTime(String command) {
        int begin = command.indexOf('_') + 1;
        int end = command.lastIndexOf('_');
        if (begin == end) {
            // there is no or only one underscore in the command
            System.err.println("Wrong time");
            return -1;
        }
        String time = command.substring(begin, end);
        int timeInt;
        try {
            timeInt = Integer.valueOf(time);
        } catch (NumberFormatException ex) {
            timeInt = -1;
            System.err.println("Wrong time! \"" + time + '\"');
        }

        if (timeInt < OLLIE_MIN_TIME || timeInt > OLLIE_MAX_TIME) {
            timeInt = -1;
            System.err.println("Wrong time! \"" + time + '\"');
        }

        return timeInt;
    }

    static int getSpeed(String command) {
        int begin = command.lastIndexOf('_') + 1;
        if (begin == -1) {
            // there is no underscore in the command
            System.err.println("Wrong speed");
            return -1;
        }
        String speed = command.substring(begin);
        int speedInt;
        try {
            speedInt = Integer.valueOf(speed);
        } catch (NumberFormatException ex) {
            speedInt = -1;
            System.err.println("Wrong speed! \"" + speed + '\"');
        }

        if (speedInt < OLLIE_MIN_SPEED || speedInt > OLLIE_MAX_SPEED) {
            speedInt = -1;
            System.err.println("Wrong speed! \"" + speed + '\"');
        }

        return speedInt;
    }

    static void sendHTTPRequestWithoutResponse(String urlString) {
        try {
            URL url = new URL(urlString);
            try (InputStream is = url.openStream()) {
                is.available();
            }
        } catch (Exception ex) {
            System.err.println("Couldn't acces url: \"" + urlString + '\"');
            System.err.println("Exception: " + ex.getMessage());
            System.err.println(ex);
        }
    }

}
