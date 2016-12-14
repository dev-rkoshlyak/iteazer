package com.iteazer.serverollie.server;

import java.io.IOException;

import org.junit.Test;

public class GoDownIT extends BaseIT {
  /**
   * Put robot with pitch, run test and robot will be with abs(pitch) < 5 and green.
   */
  @Test
  public void pitchDown() throws IOException, InterruptedException {
    String r = null;
    r = doCommand("setStabilization false");
    r = doCommand("subscribe ImuAngles");
    r = doCommand("setColor 0xFF0000");
    int speed = 50;
    int timeFromSensorLast = -1;
    while (true) {
      r = doCommand("getImuAngles");
      String[] res = r.split(" ");
      int pitch = Integer.parseInt(res[1]);
      int timeFromSensor = Integer.parseInt(res[4]);
      if (timeFromSensor < timeFromSensorLast) {
        int motorSpeed = speed;
        if (pitch > 0) {
          motorSpeed = -speed;
        }
        if (Math.abs(pitch) < 5) {
          r = doCommand("setColor 0x00FF00");
          break;
        }
        r = doCommand("setRawMotors " + motorSpeed + " " + motorSpeed);
        Thread.sleep(1);
        r = doCommand("setRawMotors " + 0 + " " + 0);
      }
      timeFromSensorLast = timeFromSensor;
    }
  }
  
  /**
   * Put robot with pitch and roll, run test and robot will be with abs(pitch) < 5 and abs(roll) < 5 and green.
   */
  @Test
  public void pitchAndRollDown() throws IOException, InterruptedException {
    String r = null;
    r = doCommand("setStabilization false");
    r = doCommand("subscribe ImuAngles");
    r = doCommand("setColor 0xFF0000");
    int speed = 50;
    int timeFromSensorLast = -1;
    while (true) {
      r = doCommand("getImuAngles");
      String[] res = r.split(" ");
      int pitch = Integer.parseInt(res[1]);
      int roll = Integer.parseInt(res[2]);
      int timeFromSensor = Integer.parseInt(res[4]);
      if (timeFromSensor < timeFromSensorLast) {
        System.out.println(r);
        int motorSpeedLeft = speed;
        int motorSpeedRight = speed;
        if (Math.abs(roll) < 5 && Math.abs(pitch) < 5) {
          r = doCommand("setColor 0x00FF00");
          break;
        } else if (Math.abs(roll) > Math.abs(pitch)) {
          if (roll < 0) {
            motorSpeedLeft = -motorSpeedLeft;
          } else {
            motorSpeedRight = -motorSpeedRight;
          }
        } else {
          if (pitch > 0) {
            motorSpeedLeft = -motorSpeedLeft;
            motorSpeedRight = -motorSpeedRight;
          }
        }
        r = doCommand("setRawMotors " + motorSpeedLeft + " " + motorSpeedRight);
        Thread.sleep(1);
        r = doCommand("setRawMotors " + 0 + " " + 0);
      }
      timeFromSensorLast = timeFromSensor;
    }
  }
}
