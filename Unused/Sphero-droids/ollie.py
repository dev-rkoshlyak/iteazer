# Wsl_F@ITeazer

from droid import Droid


class Ollie(Droid):
    pass


if __name__ == '__main__':

    ollie = Ollie('DC:71:2F:B5:B6:31')

    ollie.setColor(0, 250, 0)

    # ollie.determineShift()
    ollie.setShift(144)

    f = open("input.txt")
    commands = f.readlines()

    r = 250
    g = 0
    b = 0
    for command in commands:
        r ^= 250
        b ^= 250
        ollie.setColor(r, g, b)
        res = ollie.doCommand(command)
        print repr(res)

    ollie.disconnect()
