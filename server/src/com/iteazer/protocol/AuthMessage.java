package com.iteazer.protocol;

public class AuthMessage extends BaseMessage {
  private static final long serialVersionUID = 1L;
  private String user;
  private String password;
  public AuthMessage(long id, String user, String password, MessageType type) {
    super(id, type);
    this.user = user;
    this.password = password;
  }
  
  public String getUser() {
    return user;
  }
  
  public String getPassword() {
    return password;
  }
}
