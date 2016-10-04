package com.iteazer.driver;

public class BB8NodeJsDriver implements IBB8Driver {
  public void drive(long speed, long time, ICommandResultHandler commandResultHandler) {
    throw new IllegalStateException("Not implemented");
  }

  public void subscribeToPosition(IPositionHandler positionHandler) {
    throw new IllegalStateException("Not implemented");
  }
}
