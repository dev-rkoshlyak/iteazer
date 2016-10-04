package com.iteazer.server;

import java.io.IOException;

public class ITeazerServer {
  public static void main(String[] args) throws IOException {
    new MainServer(8007).startServer();
  }
}
