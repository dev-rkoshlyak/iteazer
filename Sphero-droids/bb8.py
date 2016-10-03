# Wsl_F@ITeazer

from droid import Droid


class BB8(Droid):
    pass


if __name__ == '__main__':

    bb8 = BB8('F1:6F:DB:2B:3B:4F')

    bb8.setColor(0, 250, 0)

    bb8.determineShift()

    f = open("input.txt")
    commands = f.readlines()

    r = 250
    g = 0
    b = 0
    for command in commands:
        r ^= 250
        b ^= 250
        bb8.setColor(r, g, b)
        res = bb8.doCommand(command)
        print repr(res)

    bb8.disconnect()
