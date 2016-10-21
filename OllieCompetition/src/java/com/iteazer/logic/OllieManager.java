package com.iteazer.logic;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static com.iteazer.logic.Constants.*;
import java.util.Scanner;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class OllieManager {

    private static final Map<String, Integer> ollieShifts = new HashMap<>();

    public static void setHeading(String serverAddress, String macAddress, int heading) {
        if (isConnected(serverAddress, macAddress)) {
//            String url = serverAddress + "/ollie/setHeading?MAC=" + macAddress + "&heading=" + heading;
  //          sendHTTPRequestWithoutResponse(url);
        }
    }

    public static boolean isConnected(String serverAddress, String macAddress) {
        String url = serverAddress + "/ollie/isConnected?MAC=" + macAddress;
        String res = sendHTTPRequest(url);
        return res.equals(CONNECTED_RESPOND);
    }

    public static void connectToOllie(String serverAddres, String macAddress) {
        if (!isConnected(serverAddres, macAddress)) {
            //http://127.0.0.1:1337/ollie/disconnect?MAC=XX:XX:XX:XX:XX:XX
            String url = serverAddres + "/ollie/connect?MAC=" + macAddress;
            sendHTTPRequestWithoutResponse(url);
            setHeading(serverAddres, macAddress, 0);
            wait(5000);
        }
    }

    public static void disconnectToOllie(String serverAddress, String macAddress) {
        if (isConnected(serverAddress, macAddress)) {
            //http://127.0.0.1:1337/ollie/disconnect?MAC=XX:XX:XX:XX:XX:XX
            String url = serverAddress + "/ollie/disconnect?MAC=" + macAddress;
            sendHTTPRequestWithoutResponse(url);
        }
    }

    public static void setColorOllie(String serverAddress, String macAddress,
            int red, int green, int blue) {
        if (isConnected(serverAddress, macAddress)) {
            //http://127.0.0.1:1337/ollie/setColor?MAC=XX:XX:XX:XX:XX:XX&r=0&g=128&b=255
            int color = blue + 256 * (green + 256 * red);
            String url = serverAddress + "/ollie/setColor?MAC=" + macAddress
                    + "&color=0x" + Integer.toHexString(color);
            sendHTTPRequestWithoutResponse(url);
        }
    }

    public static void rollOllie(String serverAddress, String macAddress, int speed, int direction) {
        if (isConnected(serverAddress, macAddress)) {
            //http://127.0.0.1:1337/ollie/roll?MAC=XX:XX:XX:XX:XX:XX&speed=50&direction=180
            direction = getDirectionWithShift(direction, macAddress);

            String url = serverAddress + "/ollie/roll?MAC=" + macAddress
                    + "&speed=" + speed + "&direction=" + direction;
            sendHTTPRequestWithoutResponse(url);
        }
    }

    private static int getDirectionWithShift(int direction, String macAddress) {
        return (direction + getShift(macAddress)) % 360;
    }

    public static int performCommandsOllie(String serverAddress, String macAddress,
            String[] commands) {
        connectToOllie(serverAddress, macAddress);

        int totalTime = 0;

        for (String command : commands) {
            System.out.println("performing command: " + command);
            totalTime += performCommandOllie(serverAddress, macAddress, command);
        }

        performCommandOllie(serverAddress, macAddress, "N_" + WAIT_ON_CALIBRATION + "_0");
        return totalTime;
    }

    /**
     *
     * @param serverAddress
     * @param macAddress
     * @param command
     * @return duration of command or 0 if unrecognized
     */
    public static int performCommandOllie(String serverAddress, String macAddress,
            String command) {
        char direction = parseDirection(command);
        int time = parseDuration(command);
        int speed = parseSpeed(command);

        if (direction == (char) 0 || time == -1 || speed == -1) {
            System.err.println("Unrecognized command: \"" + command + '\"');
            return 0;
        }

        int directionInt = directionToInt(direction, macAddress);

        rollOllie(serverAddress, macAddress, speed, directionInt);

        wait(time);

        return time;
    }

    public static void wait(int time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException ex) {
            System.err.println("Exception during waiting");
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

    static char parseDirection(String command) {
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

    static int directionToInt(char direction, String macAddress) {
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

        return directionInt;
    }

    static int parseDuration(String command) {
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

    static int parseSpeed(String command) {
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

    public static String sendHTTPRequest(String urlString) {
        try {
            URL url = new URL(urlString);
            Scanner scanner = new Scanner(url.openStream(), "UTF-8").useDelimiter("\\A");
            String out = scanner.hasNext() ? scanner.next() : "";
            return out;
        } catch (Exception ex) {
            System.err.println("Couldn't acces url: \"" + urlString + '\"');
            System.err.println("Exception: " + ex.getMessage());
            System.err.println(ex);
        }

        return "";
    }

}
