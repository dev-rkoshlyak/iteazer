package com.iteazer.driver;

import java.io.IOException;

public interface IBB8Driver extends IDriver {
  void drive(long speed, long time, ICommandResultHandler commandResultHandler) throws IOException;
  void subscribeToPosition(IPositionHandler positionHandler);
}
