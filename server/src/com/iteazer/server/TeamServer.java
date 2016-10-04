package com.iteazer.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.iteazer.driver.BB8EmulatorDriver;
import com.iteazer.driver.IBB8Driver;
import com.iteazer.driver.ICommandResultHandler;
import com.iteazer.protocol.AuthMessage;
import com.iteazer.protocol.BaseMessage;
import com.iteazer.protocol.DriveMessage;
import com.iteazer.protocol.ResultMessage;

public class TeamServer extends Thread {
  private final IBB8Driver bb8Driver = new BB8EmulatorDriver();
  private final ObjectInputStream inputStream;
  private final ObjectOutputStream outputStream;
  private long id;
  private boolean isAuthorized = false;
  public TeamServer(Socket socket) throws IOException {
    id = 0;
    inputStream = new ObjectInputStream(socket.getInputStream());
    outputStream = new ObjectOutputStream(socket.getOutputStream());
  }
  
  private void processMessage() throws IOException {
    BaseMessage message = BaseMessage.readMessage(inputStream);
    switch (message.getType()) {
      case AUTH:
        AuthMessage authMessage = (AuthMessage) message;
        if (authMessage.getUser().equals("user1") && authMessage.getPassword().equals("password1")) {
          isAuthorized = true;
          new ResultMessage(++id, authMessage.getId(), null).writeMessage(outputStream);
        } else {
          new ResultMessage(++id, authMessage.getId(), "Wrong password").writeMessage(outputStream);
        }
        break;
      case DRIVE:
        if (isAuthorized) {
          DriveMessage driveMessage = (DriveMessage) message;
          if (driveMessage.getRobotId().equals("robot1")) {
            bb8Driver.drive(driveMessage.getSpeed(), driveMessage.getTime(), new ICommandResultHandler() {
              public void commandExecuted(String error) throws IOException {
                new ResultMessage(++id, driveMessage.getId(), error).writeMessage(outputStream);
              }
            });
          } else {
            new ResultMessage(++id, driveMessage.getId(), "Unknown robot id").writeMessage(outputStream);
          }
        } else {
            new ResultMessage(++id, message.getId(), "Please login first").writeMessage(outputStream);
        }
        break;
      default:
        new ResultMessage(++id, message.getId(), "Unknown message type error").writeMessage(outputStream);
        break;
    }
  }

  public void run() {
    try {
      while (true) {
        processMessage();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
