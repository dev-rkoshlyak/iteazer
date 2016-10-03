# Wsl_F@ITeazer

import pygame

from sphero import Sphero
from time import sleep


class Droid:
    DEFAULT_SPEED = 20

    def __init__(self, deviceAddress):
        self.shift = 0
        print 'Start connecting...'
        self.droid = Sphero(deviceAddress)

        # Request some sensor stream.
        self.droid.cmd(0x02, 0x11, [0, 80, 0, 1, 0x80, 0, 0, 0, 0])
        print 'initialized successfully!'

    def move(self, speed, direction):
        ''' speed - integer in range [0 .. 255]
            direction - integer in range [0 .. 359]'''
        v = speed
        h = ((int)(direction - self.shift) + 360 + 360) % 360
        print 'moving!   v: ' + repr(v) + '		h: ' + repr(h)
        self.droid.cmd(0x02, 0x30, [v, (h & 0xff00) >> 8, h & 0xff, 1])

    def moveUp(self, time, speed=DEFAULT_SPEED):
        self.move(speed, 0)
        sleep(time)

    def moveDown(self, time, speed=DEFAULT_SPEED):
        self.move(speed, 180)
        sleep(time)

    def moveRight(self, time, speed=DEFAULT_SPEED):
        self.move(speed, 90)
        sleep(time)

    def moveLeft(self, time, speed=DEFAULT_SPEED):
        self.move(speed, 270)
        sleep(time)

    def setColor(self, r, g, b):
        self.droid.cmd(0x02, 0x20, [r, g, b, 0])

    def doCommand(self, command):
        directionText = command[0]
        direction = -1
        if directionText == 'U' or directionText == 'N':
            direction = 0
        elif directionText == 'R' or directionText == 'E':
            direction = 90
        elif directionText == 'D' or directionText == 'S':
            direction = 180
        elif directionText == 'L' or directionText == 'W':
            direction = 270
        else:
            return "wrong direction!"

        # print repr(command)

        time = float(int(command[2:6])) / 1000
        speed = int(command[7:-1])
        print repr(command) + '\r\ndirection: ' + repr(direction) + '	time: ' + repr(time) + '	speed: ' + repr(
            speed)

        if directionText == 'U' or directionText == 'N':
            self.moveUp(time, speed)
        elif directionText == 'R' or directionText == 'E':
            self.moveRight(time, speed)
        elif directionText == 'D' or directionText == 'S':
            self.moveDown(time, speed)
        elif directionText == 'L' or directionText == 'W':
            self.moveLeft(time, speed)

        return "good!"

    def disconnect(self):
        # Must manually disconnect or you won't be able to reconnect.
        self.droid.disconnect()
        print 'Disconnected successfully!'

    # raw_input("Press Enter to exit...")


    def setShift(self, newShift):
        self.shift = newShift

    def moveUpDown(self):
        self.moveUp(0.5, 1)
        self.moveUp(0.5, 2)
        self.moveUp(0.5, 1)

        self.moveUp(1.5, 50)

        self.moveUp(0.5, 1)
        self.moveUp(0.5, 2)
        self.moveUp(0.5, 1)

        self.moveDown(0.5, 1)
        self.moveDown(0.5, 2)
        self.moveDown(0.5, 1)

        self.moveDown(1.5, 50)

        self.moveDown(0.5, 1)
        self.moveDown(0.5, 2)
        self.moveDown(0.5, 1)

    def determineShift(self):
        raw_input("Press Enter to start setting")
        while True:
            print "current shift: " + repr(self.shift)
            self.moveUpDown()
            newShift = input("enter new shift (or current value to finish settings): ")
            if newShift == self.shift:
                break
            self.setShift(newShift)
