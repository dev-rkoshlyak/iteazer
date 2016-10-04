package com.iteazer.protocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BaseMessage implements IMessage {
  private static final long serialVersionUID = 1L;
  private long id;
  private MessageType type;
  public BaseMessage(long id, MessageType type) {
    this.id = id;
    this.type = type;
  }
  public long getId() {
    return id;
  }
  public MessageType getType() {
    return type;
  }

  public void writeMessage(ObjectOutputStream outputStream) throws IOException {
    synchronized (outputStream) {
      outputStream.writeObject(this);
    }
  }
  
  public static BaseMessage readMessage(ObjectInputStream inputStream) throws IOException {
    synchronized (inputStream) {
      try {
        return (BaseMessage) inputStream.readObject();
      } catch (ClassNotFoundException e) {
        throw new IOException(e);
      }
    }
  }
}
