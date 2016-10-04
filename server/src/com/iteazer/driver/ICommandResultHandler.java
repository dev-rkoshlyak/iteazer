package com.iteazer.driver;

import java.io.IOException;

public interface ICommandResultHandler {
  void commandExecuted(String error) throws IOException;
}