package com.iteazer.catchmouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class CatchMouseClient {
  private static String doCommand(BufferedReader input, OutputStreamWriter output, String command) throws IOException {
    output.write(command + "\n");
    output.flush();
    return input.readLine();
  }
  public static void main (String[] args) throws IOException {
    Socket clientSocket = new Socket("localhost", 5325);
    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    OutputStreamWriter output = new OutputStreamWriter(clientSocket.getOutputStream());
    while (true) {
      String[] catPosition = doCommand(input, output, "where cat").split(" ");
      int catX = Integer.parseInt(catPosition[0]);
      int catY = Integer.parseInt(catPosition[1]);
      String[] mousePosition = doCommand(input, output, "where mouse").split(" ");
      int mouseX = Integer.parseInt(mousePosition[0]);
      int mouseY = Integer.parseInt(mousePosition[1]);
      String command = null;
      if (catX < mouseX) {
        command = "move right";
      } else if (catX > mouseX) {
        command = "move left";
      } else if (catY < mouseY) {
        command = "move down";
      } else if (catY > mouseY) {
        command = "move up";
      }
      System.out.println(command);
      String result = doCommand(input, output, command);
      if (result.equals("win")) {
        System.out.println("We got it");
        break;
      }
    }
    clientSocket.close();
  }
}
