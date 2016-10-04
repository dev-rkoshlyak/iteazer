package com.iteazer.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
  private int port;
  
  public MainServer(int port) {
    this.port = port;
  }
  
  public void startServer() throws IOException {
    ServerSocket serverSocket = new ServerSocket(port);
    while (true) {
      Socket clientSocket = serverSocket.accept();
      TeamServer teamServer = new TeamServer(clientSocket);
      teamServer.run();
    }
  }
}
