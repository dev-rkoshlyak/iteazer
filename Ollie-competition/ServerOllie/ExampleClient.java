import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class ExampleClient {
  private static String doCommand(BufferedReader input, OutputStreamWriter output, String command) throws IOException {
    output.write(command + "\n");
    output.flush();
    return input.readLine();
  }
  
  public static void main (String[] args) throws IOException {
    Socket clientSocket = new Socket("localhost", 32_321);
    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    OutputStreamWriter output = new OutputStreamWriter(clientSocket.getOutputStream());

    String ans = doCommand(input, output, "TeamB parol2");
    System.out.println("Login: " + ans);
    ans = doCommand(input, output, "connect");
    System.out.println("Connect: " + ans);
    
    //rollLap(input, output);
    //checkVelocity(input, output);
    //checkAccelOne(input, output);
    //checkAccelerometer(input, output);
    //checkGyroscope(input, output);
    //checkImuAngles(input, output);
    //checkMotors(input, output);
    checkOdometer(input, output);
    
    clientSocket.close();
  }

  private static void checkOdometer(BufferedReader input, OutputStreamWriter output) throws IOException { 
    int i = 0;
    int x, y;
    int xp = 0;
    int yp = 0;
    int direction = 0;
    while (i >= 0) {
      doCommand(input, output, "roll " + direction + " 50 1250");
      i++;
      String r = doCommand(input, output, "getOdometer");
      System.out.println(r);
      String[] parts = r.split("\t");
      x = extractInt(parts[1]);
      y = extractInt(parts[2]);
      int dx = Math.abs(x - xp);
      int dy = Math.abs(y - yp);
      xp = x;
      yp = y;
      if (Math.abs(dx) + Math.abs(dy) < 5) {
        doCommand(input, output, "setColor 0x00FFFF");
        System.out.println("No moves detected");
      } else {
        direction = (direction + 90) % 360;
        if (dx > dy) {
          doCommand(input, output, "setColor 0x0000FF");
          System.out.println("go along axis x");          
        } else {
          doCommand(input, output, "setColor 0xFF0000");
          System.out.println("go along axis y");          
        }
      }
    }  
  }
  
  private static void checkMotors(BufferedReader input, OutputStreamWriter output) throws IOException { 
    int i = 0;
    while (i >= 0) {
      i++;
      String r = doCommand(input, output, "getMotorsBackEmf");
      System.out.println(r);
      String[] parts = r.split("\t");
      int rMotor = extractInt(parts[1]);
      int lMotor = extractInt(parts[2]);
      if (Math.abs(rMotor) + Math.abs(lMotor) < 5) {
        doCommand(input, output, "setColor 0x00FFFF");
        System.out.println("No moves detected");
      } else {
        if (lMotor > 0 && rMotor > 0) {
          doCommand(input, output, "setColor 0x0000FF");
          System.out.println("Go forward");          
        } else {
          if (lMotor < 0 && rMotor < 0) {
            doCommand(input, output, "setColor 0x00FF00");
            System.out.println("Go backward");          
          } else {
            doCommand(input, output, "setColor 0xFF0000");
            System.out.println("Rotate");          
          }
        }
      }
    }  
  }
  
  private static void checkImuAngles(BufferedReader input, OutputStreamWriter output) throws IOException { 
    int i = 0;
    while (i >= 0) {
      i++;
      String r = doCommand(input, output, "getImuAngles");
      System.out.println(r);
      String[] parts = r.split("\t");
      int pitchAngle = extractInt(parts[1]);
      int rollAngle = extractInt(parts[2]);
      int yawAngle = extractInt(parts[3]);
      System.out.println("pitchAngle: " + pitchAngle + "\nrollAngle: " + rollAngle + "\nyawAngle: " + yawAngle + "\n");
      pitchAngle = Math.abs(pitchAngle);
      rollAngle = Math.abs(rollAngle);
      yawAngle = Math.abs(yawAngle);
      
      if (pitchAngle >= rollAngle && pitchAngle >= yawAngle) {
          doCommand(input, output, "setColor 0x0000FF");
      } else {
        if (rollAngle >= yawAngle) {
          doCommand(input, output, "setColor 0x00FF00");
        } else {
          doCommand(input, output, "setColor 0xFF0000");
        }
      }
    }
  }
  
  private static void checkGyroscope(BufferedReader input, OutputStreamWriter output) throws IOException { 
    int i = 0;
    int x,y,z;
    int xp, yp, zp;
    xp = 0; yp = 0; zp = 0;
    while (i >= 0) {
      i++;
      String r = doCommand(input, output, "getGyroscope");
      String[] parts = r.split("\t");
      x = extractInt(parts[1]);
      y = extractInt(parts[2]);
      z = extractInt(parts[3]);
      System.out.println("gyro axis X: " + x + "\ngyro axis Y: " + y + "\ngyro axis Z: " + z + "\n");

      int dx = Math.abs(x - xp);
      int dy = Math.abs(y - yp);
      int dz = Math.abs(z - zp);
      xp = x;
      yp = y;
      zp = z;
      if (dx >= dy && dx >= dz) {
        if (dx > 250) {
          doCommand(input, output, "setColor 0x0000FF");
        }
      } else {
        if (dy >= dz) {
          if (dy > 250) {
            doCommand(input, output, "setColor 0x00FF00");
          }
        } else {
          if (dz > 250) {
            doCommand(input, output, "setColor 0xFF0000");
          }
        }
      }
    }
  }
  
  
  private static void checkAccelerometer(BufferedReader input, OutputStreamWriter output) throws IOException {
    int i = 0;
    while (i >= 0) {
      i++;
      String r = doCommand(input, output, "getAccelerometer");
      String[] parts = r.split("\t");
      int x = extractInt(parts[1]);
      int y = extractInt(parts[2]);
      int z = extractInt(parts[3]);
      System.out.println("accelX: " + x + "\naccelY: " + y + "\naccelZ: " + z + "\n");
      x = Math.abs(x);
      y = Math.abs(y);
      z = Math.abs(z);
      
      if (x >= y && x >= z) {
          doCommand(input, output, "setColor 0x0000FF");
      } else {
        if (y >= z) {
          doCommand(input, output, "setColor 0x00FF00");
        } else {
          doCommand(input, output, "setColor 0xFF0000");
        }
      }
    }
  }
  
  private static void rollLap(BufferedReader input, OutputStreamWriter output) throws IOException {
    System.out.println(doCommand(input, output, "setColor 0xFF0000"));

    doCommand(input, output, "roll 0 50 1000");
    doCommand(input, output, "roll 90 50 1000");
    doCommand(input, output, "roll 180 50 1000");
    doCommand(input, output, "roll 270 50 1000");
    doCommand(input, output, "roll 0 0 1000");

  }
  
  private static void checkAccelOne(BufferedReader input, OutputStreamWriter output) throws IOException {
    int i = 0;
    while (i >= 0) {
      i++;
      String r = doCommand(input, output, "getAccelOne");
      int accelOne = extractInt(r);
      System.out.println("accelOne: " + accelOne);
        if (Math.abs(accelOne - 100) <= 5) {
          doCommand(input, output, "setColor 0x0000FF");
          System.out.println("On the floar");
        } else {
          doCommand(input, output, "setColor 0x00FF00");
          System.out.println("In the air");
        }
    }
  }
  
  private static void checkVelocity(BufferedReader input, OutputStreamWriter output) throws IOException {
    // Я пока не совсем понимаю, что такое Velocity, вроде должна быть скорость, но значения какие-то странные
    int direction = 0;
    int speed = 20;
    int i = 0;
    int j = 0;
    while (i < 5) {
      speed += 5;
      if (speed > 50) speed = 50;
      String r = doCommand(input, output, "roll " + direction + " " + speed + " 750");
      
      System.out.println("cmd res: " + r + "\nset speed: " + speed);
      String velocity = doCommand(input, output, "getVelocity");
      System.out.println(velocity);
      String[] parts = velocity.split("\t");
      int velocityX = extractInt(parts[1]);
      int velocityY = extractInt(parts[2]);
      System.out.println("velocityX: " + velocityX + " velocityY: " + velocityY);
      double speedV = Math.sqrt(velocityX*velocityX + velocityY*velocityY);
      System.out.println("curSpeed: " + speedV);
      if (speedV > 1200) {
        j++;
        if (j >= 3) {
          i++;
          speed = 0;
          j = 0;
          doCommand(input, output, "roll " + direction + " 0 1000");
          direction = 180 - direction;
          System.out.println("turn 180");
          doCommand(input, output, "roll " + direction + " 0 1000");
        }
      }
    }

    doCommand(input, output, "roll " + direction + " 0 1000");
  }
  
  private static int extractInt(String s) {
    int ind = s.indexOf(":");
    int res;
    try {
      res = Integer.parseInt(s.substring(ind+2));
    } catch (Exception ex) {
      res = 0;
    }
    return res;
  }
}
