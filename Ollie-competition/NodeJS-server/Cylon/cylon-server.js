'use strict';

var Cylon = require("cylon");

class Robot {
  constructor(uuid) {
    this.uuid = uuid;
    this.connected = false;
    this.cylonDriver = null;
  }
  setCylonDriver(cylonDriver) {
    this.cylonDriver = cylonDriver;
  }
  isConnected() {
    return this.connected;
  }
  getDevice() {
    return this.cylonDriver.devices["ollie_" + this.uuid];
  }
  connect(callback) {
    this.cylonDriver.device("ollie_" + this.uuid, {
      connection: "bluetooth_ollie_" + this.uuid,
      driver: "ollie",
      module: "cylon-sphero-ble",
    });
    this.cylonDriver.startDevice(this.getDevice(), () => {
      console.log("ollie_" + this.uuid + " connected");
      this.connected = true;
      callback();
    });
  }

  roll(spped, direction, callback) {
    this.getDevice().roll(speed, direction, callback);
  }

  setColor(newColor, callback) {
    this.getDevice().color(newColor, callback);
  }

  setStabilization(stabilization, callback) {
    this.getDevice().setStabilization(stabilization, callback);
  }

  getVelocity(callback) {
    const device = this.getDevice();
    device.streamVelocity(Robot.SPS, false);
    device.once("Velocity", callback);
  }

  getAccelOne(callback) {
    const device = this.getDevice();
    device.streamAccelOne(Robot.SPS, false);
    device.once("AccelOne", callback);
  }
  getImuAngles(callback) {
    const device = this.getDevice();
    device.streamImuAngles(Robot.SPS, false);
    device.once("ImuAngles", callback);
  }
  getAccelerometer(callback) {
    const device = this.getDevice();
    device.treamAccelerometer(Robot.SPS, false);
    device.once("Accelerometer", callback);
  }
  getGyroscope(callback) {
    const device = this.getDevice();
    device.streamGyroscope(Robot.SPS, false);
    device.once("Gyroscope", callback);
  }
  getMotorsBackEmf(callback) {
    const device = this.getDevice();
    device.streamMotorsBackEmf(Robot.SPS, false);
    device.once("MotorsBackEmf", callback);
  }
};
Robot.SPS = 5;

const uuids = [
  "44a7dd0ca730458f979d78d95a75038c",
];
const robots = uuids.reduce((map, uuid) => {
  map[uuid] = new Robot(uuid);
  return map;
},
{});
const connections = uuids.reduce((map, uuid) => {
  map["bluetooth_ollie_" + uuid] = {
    adaptor: "central",
    uuid: uuid,
    module: "cylon-ble",
  };
  return map;
},
{});

Cylon.robot({
    connections: connections,
    devices: {},
    work: function(cylonDriver) {
        var http = require('http');
        var url = require('url');

        var server = new http.Server(function(req, res) {
            var urlParsed = url.parse(req.url, true);
            console.log("Received url: " + urlParsed.pathname + " q: " + urlParsed.query);

            const mac = urlParsed.query.MAC;
            const robot = robots[mac];
            if (robot != null) {
              robot.setCylonDriver(cylonDriver);
              switch (urlParsed.pathname) {
                case  "/ollie/connect":
                  if (robot.isConnected()) {
                    res.end("connected");
                  } else {
                    robot.connect(() => {
                        console.log("connected to ollie, mac : " + mac);
                        res.end("connected");
                    });	
                  }
                  break;
                case "/ollie/isConnected":
                  if (robot.isConnected()) {
                      res.end("connected");
                  } else {
                      res.end("not");
                  }
                  break;
                case "/ollie/roll":
                  const newSpeed = parseInt(urlParsed.query.speed);
                  const newDirection = parseInt(urlParsed.query.direction);
                  robot.roll(newSpeed, newDirection, () => {
                    console.log("rolled ollie, mac : " + mac);
                    res.end("rolled");
                  });
                  break;
                case "/ollie/setColor":
                  const newColor = parseInt(urlParsed.query.color);
                  console.log('setColor ' + newColor);
                  robot.setColor(newColor, () => {
                    console.log("set color of ollie, mac : " + mac);
                    res.end("set color");
                  });
                  break;
                case "/ollie/setStabilization":
                  const st = Boolean(parseInt(urlParsed.query.stabilization));
                  robot.setStabilization(st, () => {
                      console.log("set stabilization of ollie, mac : " + mac);
                      res.end("set stabilization");
                  });	
                  break;
                case "/ollie/getVelocity":
                  robot.getVelocity((data) => {
                    console.log("velocity: " + JSON.stringify(data) + "\n");
                    res.end("xVelocity: " + data.xVelocity.value[0] + "\n" + "yVelocity: " + data.yVelocity.value[0] + "\n");
                  });	
                  break;
                case "/ollie/getAccelOne":
                  robot.getAccelOne((data) => {
                    console.log("accelone: " + JSON.stringify(data) + "\n");
                    res.end("accelOne: " + data.accelOne.value[0] + "\n");
                  });	
                  break;
                case "/ollie/getImuAngles":
                  reobot.getImuAngles((data) => {
                    console.log("imuangles: " + JSON.stringify(data) + "\n");
                    res.end("pitchAngle: " + data.pitchAngle.value[0] + "\n"+ "rollAngle: " + data.rollAngle.value[0] + "\n"+ "yawAngle: " + data.yawAngle.value[0] + "\n");
                  });	
                case "/ollie/getAccelerometer":
                  robot.getAccelerometer((data) => {
                    console.log("accelerometer: " + JSON.stringify(data) + "\n");
                    res.end("xAccel: " + data.xAccel.value[0] + "\n"+ "yAccel: " + data.yAccel.value[0] + "\n"+ "zAccel: " + data.zAccel.value[0] + "\n");
                  });	
                  break;
                case "/ollie/getGyroscope":
                  robot.getGyroscope((data) => {
                    console.log("gyroscope: " + JSON.stringify(data) + "\n");
                    res.end("xGyro: " + data.xGyro.value[0] + "\n"+ "yGyro: " + data.yGyro.value[0] + "\n"+ "zGyro: " + data.zGyro.value[0] + "\n");
                  });	
                  break;
                case "/ollie/getMotorsBackEmf":
                  robot.getMotorsBackEmf((data) => {
                    console.log("motorsbackemf: " + JSON.stringify(data) + "\n");
                    res.end("rMotorBackEmf: " + data.rMotorBackEmf.value[0] + "\n"+ "lMotorBackEmf: " + data.lMotorBackEmf.value[0] + "\n");
                  });	
                  break;
                case "/ollie/getOdometer":
                  robot.getOdometer((data) => {
                    console.log("odometer: " + JSON.stringify(data) + "\n");
                    res.end("xOdometer: " + data.xOdometer.value[0] + "\n"+ "yOdometer: " + data.yOdometer.value[0] + "\n");
                  });	
                  break;
                default:
                  console.log("Unknown path: " + urlParsed.pathname);
                  res.end("Unknown pathname: " + urlParser.pathname);
                  break;
              }
            } else {
              console.log("Robot not found with mac: " + mac)
              res.end("Robot not found with mac: " + mac);
            }
        });
        console.log('listening...');
        server.listen(1337, "127.0.0.1");
    }
}).start();
