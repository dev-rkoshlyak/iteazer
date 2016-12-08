import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class ExampleClient {
  private static String doCommand(BufferedReader input, OutputStreamWriter output, String command) throws IOException {
    long wasTime = System.currentTimeMillis();
    output.write(command + "\n");
    output.flush();
    String line = input.readLine();
    long nowTime = System.currentTimeMillis();
    System.out.println(command + " with time = " + (nowTime-wasTime));
    return line;
  }
  
  public static void main (String[] args) throws IOException {
    Socket clientSocket = new Socket("localhost", 32_321);
    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    OutputStreamWriter output = new OutputStreamWriter(clientSocket.getOutputStream());

    String ans = doCommand(input, output, "TeamF parol6");
    System.out.println("Login: " + ans);
    ans = doCommand(input, output, "connect");
    System.out.println("Connect: " + ans);
    
    rollLap(input, output);
    //checkVelocity(input, output);
    //checkAccelOne(input, output);
    //checkAccelerometer(input, output);
    //checkGyroscope(input, output);
    //checkImuAngles(input, output);
    //checkMotors(input, output);
    //checkOdometer(input, output);
    pendulum(input, output);
    //speed2(input, output);
    //pendulum(input, output);
    //destabilize(input, output);
    //getFrequency2(input, output);
    
    clientSocket.close();
  }

  private static void getFrequency(BufferedReader input, OutputStreamWriter output) throws IOException {
    //doCommand(input, output, "setStabilization false");
    int count = 0;
    int n = 1_000;
    int speedV = 0;
    int prev = 0;

    long wasTime = System.currentTimeMillis();

    while (count < n) {
      String velocity = doCommand(input, output, "getVelocity");

      String[] parts = velocity.split(" ");
      int velocityX = extractInt(parts[1]);
      int velocityY = extractInt(parts[2]);

      speedV = (int) ( Math.sqrt(velocityX*velocityX + velocityY*velocityY) * 1000);
      if (speedV != prev) {
        count++;
        prev = speedV;
      }
    }

    long nowTime = System.currentTimeMillis();
    
    double duration = nowTime - wasTime;
    double frequency = (n / duration) * 1000;
    
    System.out.println("Frequency(getVelocity) = " + frequency);
    System.out.println("Duration = " + duration);
  }

  private static void getFrequency2(BufferedReader input, OutputStreamWriter output) throws IOException {
    doCommand(input, output, "setStabilization false");
    int count = 0;
    int n = 1_000;
    int speedV = 0;
    int prev = 0;

    long wasTime = System.currentTimeMillis();

    while (count < n) {
      String velocity = doCommand(input, output, "getVelocity");

      String[] parts = velocity.split(" ");
      int velocityX = extractInt(parts[1]);
      int velocityY = extractInt(parts[2]);

      speedV = (int) ( Math.sqrt(velocityX*velocityX + velocityY*velocityY) * 1000);
      if (speedV != prev) {
        count++;
        if (count % 100 == 0) {
          doCommand(input, output, "setColor 0xFF0000");
        }
        if (count % 100 == 50) {
          doCommand(input, output, "setColor 0x00FF00");
        }
        prev = speedV;
      }
    }

    long nowTime = System.currentTimeMillis();
    
    double duration = nowTime - wasTime;
    double frequency = (n / duration) * 1000;
    
    System.out.println("Frequency(getVelocity) = " + frequency);
    System.out.println("Duration = " + duration);
  }

  private static void destabilize(BufferedReader input, OutputStreamWriter output) throws IOException {
    String r = doCommand(input, output, "setStabilization false");
    System.out.println(r);
  }
  
  private static void speed2(BufferedReader input, OutputStreamWriter output) throws IOException {
    doCommand(input, output, "Disabled");
    doCommand(input, output, "setColor 0xFF0000");
    int i = 0;
    int j = -1_000_000_000;
    int color = 0;
    while (i >= 0) {
      i++;
      j++;
      String velocity = doCommand(input, output, "getVelocity");
      //System.out.println(velocity);
      String[] parts = velocity.split(" ");
      int velocityX = extractInt(parts[1]);
      int velocityY = extractInt(parts[2]);
      //System.out.println("velocityX: " + velocityX + " velocityY: " + velocityY);
      double speedV = Math.sqrt(velocityX*velocityX + velocityY*velocityY);
      System.out.println("curSpeed: " + speedV);
      if (speedV > 5) {
        doCommand(input, output, "setColor 0x0000FF");
        j = 0;
      }
      if (j >= 10) {
        doCommand(input, output, "setColor 0x00FF00");
        j = -1_000_000_100;
      }
    }
  }

  // works for BB8
  private static void pendulum(BufferedReader input, OutputStreamWriter output) throws IOException {
    doCommand(input, output, "setStabilization false");
    int i = 0;
    int color = 0;
    double min = 1500;
    double minSpeed = 1_000_000_000;
    while (i < 100) {
      String velocity = doCommand(input, output, "getVelocity");
      //System.out.println(velocity);
      String[] parts = velocity.split(" ");
      int velocityX = extractInt(parts[1]);
      int velocityY = extractInt(parts[2]);
      //System.out.println("velocityX: " + velocityX + " velocityY: " + velocityY);
      int speedV = (int) ( Math.sqrt(velocityX*velocityX + velocityY*velocityY) * 100);
      System.out.println("Cur speed = " + speedV);
      
      if (speedV <= minSpeed) {
            minSpeed= Math.max(min, speedV);
            if (color != 1) {
              doCommand(input, output, "setColor 0xFF0000");
              i++;
            }
            color = 1;
      } else {
            if (color != 2) 
              doCommand(input, output, "setColor 0x000000");
            color = 2;
      }
    }
    
    doCommand(input, output, "setColor 0xFFFFFF");
  }

  
  private static void checkOdometer(BufferedReader input, OutputStreamWriter output) throws IOException { 
    int i = 0;
    int x, y;
    int xp = 0;
    int yp = 0;
    int direction = 0;
    while (i >= 0) {
      doCommand(input, output, "roll " + direction + " 40 2150");
      i++;
      String r = doCommand(input, output, "getOdometer");
      System.out.println(r);
      String[] parts = r.split(" ");
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
      String[] parts = r.split(" ");
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
      String[] parts = r.split(" ");
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
      String[] parts = r.split(" ");
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
      String[] parts = r.split(" ");
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
    doCommand(input, output, "roll 0 0");

  }
  
  private static void checkAccelOne(BufferedReader input, OutputStreamWriter output) throws IOException {
    int i = 0;
    while (i >= 0) {
      i++;
      String r = doCommand(input, output, "getAccelOne");
      String[] parts = r.split(" ");
      int accelOne = extractInt(parts[1]);
      System.out.println("accelOne: " + accelOne);
        if (Math.abs(accelOne - 100) <= 20) {
          doCommand(input, output, "setColor 0x000000");
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
    int speed = 40;
    int i = 0;
    int j = 0;
    while (i < 5) {
      speed += 2;
      if (speed > 50) speed = 50;
      String r = doCommand(input, output, "roll " + direction + " " + speed + " 500");
      
      System.out.println("set speed: " + speed);
      String velocity = doCommand(input, output, "getVelocity");
      //System.out.println(velocity);
      String[] parts = velocity.split(" ");
      int velocityX = extractInt(parts[1]);
      int velocityY = extractInt(parts[2]);
      //System.out.println("velocityX: " + velocityX + " velocityY: " + velocityY);
      double speedV = Math.sqrt(velocityX*velocityX + velocityY*velocityY);
      System.out.println("curSpeed: " + speedV);
      if (speedV > 1200) {
        j++;
        if (j >= 2) {
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
    int res;
    try {
      res = Integer.parseInt(s);
    } catch (Exception ex) {
      System.out.println("s= \""+s+"\"");
      res = 0;
    }
    return res;
  }
}
