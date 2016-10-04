package com.iteazer.protocol;

public class ResultMessage extends BaseMessage {
  private static final long serialVersionUID = 1L;
  private long requestId;
  private String error;
  public ResultMessage(long id, long requestId, String error) {
    super(id, MessageType.RESULT);
    this.requestId = requestId;
    this.error = error;
  }
  public long getRequestId() {
    return requestId;
  }
  public String getError() {
    return error;
  }
}
