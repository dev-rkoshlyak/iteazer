package com.iteazer.driver;

import java.io.IOException;

public class BB8EmulatorDriver implements IBB8Driver {
  public void drive(long speed, long time, ICommandResultHandler commandResultHandler) throws IOException {
    commandResultHandler.commandExecuted(null);
  }

  public void subscribeToPosition(IPositionHandler positionHandler) {
  }
}
