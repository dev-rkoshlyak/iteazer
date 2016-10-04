package com.iteazer.protocol;

import java.io.Serializable;

public interface IMessage extends Serializable {
  MessageType getType();
}