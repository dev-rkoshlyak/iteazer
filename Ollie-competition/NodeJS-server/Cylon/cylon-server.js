'use strict';

var Cylon = require("cylon");

class Robot {
  constructor(uuid) {
    this.uuid = uuid;
    this.connected = false;
    this.cylonDriver = null;
    this.Velocity = JSON.parse('{"xVelocity":{"value":[0]},"yVelocity":{"value":[0]}}');
    this.Accelerometer = JSON.parse('{"xAccel":{"value":[0]},"yAccel":{"value":[0]},"zAccel":{"value":[0]}}');
    this.AccelOne = JSON.parse('{"accelOne":{"value":[100]}}');
    this.Gyroscope = JSON.parse('{"xGyro":{"value":[0]},"yGyro":{"value":[0]},"zGyro":{"value":[0]}}');
    this.ImuAngles = JSON.parse('{"pitchAngle":{"value":[0]},"rollAngle":{"value":[0]},"yawAngle":{"value":[0]}}');
    this.Motorsbackemf = JSON.parse('{"rMotorBackEmf":{"value":[0]},"lMotorBackEmf":{"value":[0]}}');
    this.Odometer = JSON.parse('{"xOdometer":{"value":[0]},"yOdometer":{"value":[0]}}');
    this.Collisions = JSON.parse('{"desc":"0"}');
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
  getSensor(sensor) {
    var res = "";
    var self = this;
    if (self[sensor] != null) {
      for(var attributename in self[sensor]){
        if (self[sensor][attributename] != null)
        	if (attributename == "desc") res += "desc: 1\n";
          	else if (self[sensor][attributename]["value"] != null)
          			res += attributename+": "+self[sensor][attributename]["value"][0] + "\n";
      }
    }

    res += "Time: " + this.getSensorInterval(sensor) + "\n";
    
    return res;
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

  roll(speed, direction, callback) {
    this.getDevice().roll(speed, direction, callback);
  }

  getCommand(power) {
    return power >= 0 ? "forward" : "reverse";
  }

  setRawMotors(newLeft, newRight, callback) {
    const leftCommand = this.getCommand(newLeft);
    const rightCommand = this.getCommand(newRight);
    const leftPower = Math.abs(newLeft);
    const rightPower = Math.abs(newRight);
    this.getDevice().setRawMotors(leftCommand, leftPower, rightCommand, rightPower, callback);
  }

  setColor(newColor, callback) {
    this.getDevice().color(newColor, callback);
  }

  setStabilization(stabilization, callback) {
    this.getDevice().setStabilization(stabilization, callback);
  }

  subscribesSensor(sensor, unsub) {
    this[sensor+"Time"] = 0;
    
    const device = this.getDevice();
    var self = this;
    
    
    if (!unsub) {
      device["stream"+sensor](Robot.SPS, unsub);
      device.on(sensor, (data) => {
        self.onSensor(sensor, data);
      });
    } else {
      device["stop"+sensor]();
    }
  }
  
  subscribeSensor(sensor) {
    console.log("subscribe: " + sensor);
    this.subscribesSensor(sensor, false);
  }
  
  unsubscribeSensor(sensor) {
    console.log("unsubscribe: " + sensor);
    this.subscribesSensor(sensor, true);
  }
  
  subscribe() {
    console.log("subscribe all");
    
    this.subscribeSensor("Velocity");
    this.subscribeSensor("Accelerometer");
    this.subscribeSensor("AccelOne");
    this.subscribeSensor("ImuAngles");
    this.subscribeSensor("Gyroscope");
    this.subscribeSensor("MotorsBackEmf");
    this.subscribeSensor("Odometer");
    
  }
  
  unsubscribe() {
    console.log("unsubscribe all");
    
    this.unsubscribeSensor("Velocity");
    this.unsubscribeSensor("Accelerometer");
    this.unsubscribeSensor("AccelOne");
    this.unsubscribeSensor("ImuAngles");
    this.unsubscribeSensor("Gyroscope");
    this.unsubscribeSensor("MotorsBackEmf");
    this.unsubscribeSensor("Odometer");
    
  }
  
  onSensor(sensor, data) {
    this[sensor] = data;
    this[sensor+"Time"] = this.getCurTime();
  }
    
  getCurTime() {
    var hrTime = process.hrtime();
    var timeMS = hrTime[0] * 1000 + hrTime[1] / 1000000;
    return parseInt(timeMS);
  }
  
  getSensorInterval(sensor) {
    return this.getCurTime() - this[sensor+"Time"];
  }
};
Robot.SPS = 30;

const uuids = [
  "44a7dd0ca730458f979d78d95a75038c", // SPRK
  //"dc712fb5b631", "f15cee63622d", "c84982ebcc74", "ee42664940f4", // Ollie
  //"f16fdb2b3b4f", // BB-8
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
                        //robot.unsubscribe();
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
                case "/ollie/setRawMotors":
                  const newLeft = parseInt(urlParsed.query.left);
                  const newRight = parseInt(urlParsed.query.right);
                  console.log('left right ' + newLeft + ' ' + newRight);
                  robot.setRawMotors(newLeft, newRight, () => {
                    console.log("set raw motors of ollie, mac : " + mac);
                    res.end("setRawMotors");
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
                case "/ollie/getAccelOne":
                case "/ollie/getImuAngles":
                case "/ollie/getAccelerometer":
                case "/ollie/getGyroscope":
                case "/ollie/getMotorsBackEmf":
                case "/ollie/getOdometer":
                case "/ollie/getCollisions":
                  var index = urlParsed.pathname.lastIndexOf("/get");
                  var sensor = urlParsed.pathname.substr(index+4);
                  console.log("get data from sensor: " + sensor);
                  const data = robot.getSensor(sensor);
                  res.end(data);
                  break;
                case "/ollie/subscribe":
                  var sensor = urlParsed.query.sensor;
                  robot.subscribeSensor(sensor);
                  res.end("Subscribed " + sensor);
                  break;
                case "/ollie/unsubscribe":
                  var sensor = urlParsed.query.sensor;
                  robot.unsubscribeSensor(sensor);
                  res.end("Unsubscribed " + sensor);
                  break;
                default:
                  console.log("Unknown path: " + urlParsed.pathname);
                  res.end("Unknown pathname: " + urlParsed.pathname);
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
