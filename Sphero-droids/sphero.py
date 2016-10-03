from bluepy import btle
from time import sleep

class Sphero(btle.DefaultDelegate):
    def __init__(self, deviceAddress):
        btle.DefaultDelegate.__init__(self)

        # Address type must be "random" or it won't connect.
        self.peripheral = btle.Peripheral(deviceAddress, btle.ADDR_TYPE_RANDOM)
        print 'connected successfully!'
        print 'initializing...'
        self.peripheral.setDelegate(self)

        self.seq = 0
        print 'init 1 done!'

        # Attribute UUIDs are identical to Ollie.
        self.antidos = self.getSpheroCharacteristic('2bbd')
        self.wakecpu = self.getSpheroCharacteristic('2bbf')
        self.txpower = self.getSpheroCharacteristic('2bb2')
        self.roll = self.getSpheroCharacteristic('2ba1')
        self.notify = self.getSpheroCharacteristic('2ba6')

        print 'init 2 done!'

        print 'Sending antidos'
        self.antidos.write('011i3', withResponse=True)
        print 'Sending txpower'
        self.txpower.write('\x0007', withResponse=True)
        print 'Sending wakecpu'
        self.wakecpu.write('\x01', withResponse=True)

        print 'init 3 done!'

    def getSpheroCharacteristic(self, fragment):
        return self.peripheral.getCharacteristics(uuid='22bb746f' + fragment + '75542d6f726568705327')[0]

    def dumpCharacteristics(self):
        for s in self.peripheral.getServices():
            print s
            for c in s.getCharacteristics():
                print c, hex(c.handle)

    def cmd(self, did, cid, data=[], answer=True, resetTimeout=True):
        # Commands are as specified in Sphero API 1.50 PDF.
        # https://github.com/orbotix/DeveloperResources/
        seq = (self.seq & 255)
        # self.seq += 1
        sop2 = 0xfc
        sop2 |= 1 if answer else 0
        sop2 |= 2 if resetTimeout else 0
        dlen = len(data) + 1
        chk = (sum(data) + did + cid + seq + dlen) & 255
        chk ^= 255

        msg = [0xff, sop2, did, cid, seq, dlen] + data + [chk]
        # print 'cmd:', ' '.join([chr(c).encode('hex') for c in msg])
        # Note: withResponse is very important. Most commands won't work without it.
        repeatNumber = 10
        while repeatNumber>0:
            try:
                self.roll.write(''.join([chr(c) for c in msg]), withResponse=True)
                repeatNumber = 0
            except:
                repeatNumber -= 1
                sleep(0.05)


    def handleNotification(self, cHandle, data):
        # print 'Notification:', cHandle, data.encode('hex')
        pass

    def waitForNotifications(self, time):
        self.peripheral.waitForNotifications(time)

    def disconnect(self):
        self.peripheral.disconnect()
