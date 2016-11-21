package com.iteazer.catchmouse;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.lang.Thread;

public class CatchMouseServer {
  public static void main (String[] args) {
    ServerSocket server = null;
    try {
      server = new ServerSocket(5325);
      while (true) {
        Socket client = server.accept();
        CatchMouseHandler handler = new CatchMouseHandler(client);
        handler.start();
      }
    }
    catch (Exception e) {
      System.err.println("Exception caught:" + e);
    }
  }
}

class CatchMouseHandler extends Thread {
  int catX = 0;
  int catY = 0;
  int mouseX = 5;
  int mouseY = 5;
  boolean mouseDead = false;
  int moveNumber = 0;
  Socket client;
  Random random = new Random();
  CatchMouseHandler(Socket client) {
    this.client = client;
  }
  
  void moveMouse() {
    if (random.nextBoolean()) {
      int move = 1;
      if (mouseX < catX) {
        move = -1;
      }
      if (mouseX == catX && random.nextBoolean()) {
        move = -1;
      }
      mouseX += move;
    } else {
      int move = 1;
      if (mouseY < catY) {
        move = -1;
      }
      if (mouseY == catY && random.nextBoolean()) {
        move = -1;
      }
      mouseY += move;
    }
  }
  
  void moveCat(int dx, int dy) {
    moveNumber++;
    if (moveNumber % 2 == 0) {
      moveMouse();
    }
    catX += dx;
    catY += dy;
    if (mouseX == catX && mouseY == catY) {
      mouseDead = true;
    }
  }

  public void run () {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      PrintWriter writer = new PrintWriter(client.getOutputStream(), true);

      while (true) {
        try {
          String line = reader.readLine();
          String[] parts = line.split(" ");
          String command = parts[0];
          switch (command) {
            case "help":
              writer.println("You are controling cat that moves 2 times faster then mouse, you need to catch mouse. Following command working [where (mouse|cat) => X Y, move (up|down|left|right) => (ok|win)].");
              break;
            case "where":
              String who = parts[1];
              switch (who) {
                case "mouse":
                  writer.println(mouseX + " " + mouseY);
                  break;
                case "cat":
                  writer.println(catX + " " + catY);
                  break;
                default:
                  throw new IllegalStateException("Unknown object: " + who);
              }
              break;
              
            case "move":
              String direction = parts[1];
              switch (direction) {
                case "up":
                  moveCat(0, -1);
                  break;
                case "down":
                  moveCat(0, 1);
                  break;
                case "left":
                  moveCat(-1, 0);
                  break;
                case "right":
                  moveCat(1, 0);
                  break;
                default:
                  throw new IllegalStateException("Unknown direction: " + direction);
              }
              if (mouseDead) {
                writer.println("win");
              } else {
                writer.println("ok");
              }
              break;
            default:
              throw new IllegalStateException("Unknown command: " + command);
          }
          if (mouseDead) {
            client.close();
            break;
          }
        } catch (IOException e) {
          throw e;
        } catch (Exception e) {
          writer.println("Error: type [help] without [] for help");
        }
      }
    }
    catch (IOException e) {
      System.err.println("Exception caught: client disconnected.");
    }
    finally {
      try { client.close(); }
      catch (Exception e ){ ; }
    }
  }
}
