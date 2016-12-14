package com.iteazer.serverollie.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.junit.After;
import org.junit.Before;

public class BaseIT {
  private Socket clientSocket = null;
  private BufferedReader input = null;
  private OutputStreamWriter output = null;
  protected String doCommand(String command) throws IOException {
    long wasTime = System.currentTimeMillis();
    output.write(command + "\n");
    output.flush();
    String line = input.readLine();
    long nowTime = System.currentTimeMillis();
    return line;
  }

  @Before
  public void before() throws IOException {
    clientSocket = new Socket("localhost", 32_321);
    input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    output = new OutputStreamWriter(clientSocket.getOutputStream());

    String ans = doCommand("roman roman");
    System.out.println("Login: " + ans);
    ans = doCommand("connect");
    System.out.println("Connect: " + ans);
  }
  
  @After
  public void after() throws IOException {
    clientSocket.close();
  }
}
