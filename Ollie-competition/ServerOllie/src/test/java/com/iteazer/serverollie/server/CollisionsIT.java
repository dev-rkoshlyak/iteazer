package com.iteazer.serverollie.server;

import com.iteazer.serverollie.helpers.TimeHelper;
import org.junit.Test;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class CollisionsIT extends BaseIT {

    @Test
    public void roll() {
        TimeHelper th = new TimeHelper();

        try {
            int timeFromSensorLast = -1;
            int speed = 120;
            int direction = 0;
            //doCommand("setRawMotors " + speed + " " + speed);
            doCommand("roll " + direction + " " + speed);

            doCommand("subscribe Collisions");

            while (true) {
                String r = doCommand("getCollisions");
                System.out.println(r);
                String[] res = r.split(" ");
                int collision = Integer.parseInt(res[1]);
                int timeFromSensor = Integer.parseInt(res[2]);
                if (timeFromSensor < timeFromSensorLast) {
                    if (collision == 1) {
                        direction = 180 - direction;
                        doCommand("roll " + direction + " " + speed);
                    }
                }
                timeFromSensorLast = timeFromSensor;
                th.wait(1000);
            }
        } catch (Exception ex) {
            System.err.println("Exception: " + ex.getMessage());
        }
    }
}
