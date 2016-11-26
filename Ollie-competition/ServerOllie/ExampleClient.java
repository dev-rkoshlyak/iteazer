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
    checkAccelerometer(input, output);
    
    clientSocket.close();
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
