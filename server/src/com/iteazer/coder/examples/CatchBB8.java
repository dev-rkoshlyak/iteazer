package com.iteazer.coder.examples;

import java.util.List;

import com.iteazer.coder.BB8Device;
import com.iteazer.coder.GameType;
import com.iteazer.coder.IDevice;
import com.iteazer.coder.IGame;
import com.iteazer.coder.ITeazer;

public class CatchBB8 {
  public static void main(String[] args) {
    IGame game = ITeazer.getGame("localhost:8083", GameType.BB8_FIND);
    List<IDevice> devices = game.getDevices();
    BB8Device bb8 = (BB8Device) devices.get(0);
    bb8.drive(10);
  }
}
