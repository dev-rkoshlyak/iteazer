package com.iteazer.protocol;

public class DriveMessage extends BaseMessage {
  private static final long serialVersionUID = 1L;
  private String robotId;
  private long speed;
  private long time;
  public DriveMessage(long id, String robotId, long speed, long time) {
    super(id, MessageType.DRIVE);
    this.robotId = robotId;
    this.speed = speed;
    this.time = time;
  }
  
  public String getRobotId() {
    return robotId;
  }
  public long getSpeed() {
    return speed;
  }
  public long getTime() {
    return time;
  }
}
