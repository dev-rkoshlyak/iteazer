package com.iteazer.protocol;

public class PositionMessage extends BaseMessage {
  private static final long serialVersionUID = 1L;
  private final String robotId;
  private final long x;
  private final long y;
  public PositionMessage(long id, String robotId, long x, long y) {
    super(id, MessageType.POSITION);
    this.robotId = robotId;
    this.x = x;
    this.y = y;
  }
  public String getRobotId() {
    return robotId;
  }
  public long getX() {
    return x;
  }
  public long getY() {
    return y;
  }
}
