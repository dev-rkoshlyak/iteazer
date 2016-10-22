//Wsl_F@ITeazer
//this file genareted by Server-Cylon-Generator

var Cylon = require("cylon");
var ollies = {};

Cylon.robot({

   connections: {
      bluetooth_ollie_d8e38c77d05d: { adaptor: "central", uuid: "d8e38c77d05d", module: "cylon-ble"},
      bluetooth_ollie_dc712fb5b631: { adaptor: "central", uuid: "dc712fb5b631", module: "cylon-ble"},
      bluetooth_ollie_f15cee63622d: { adaptor: "central", uuid: "f15cee63622d", module: "cylon-ble"},
      bluetooth_ollie_c84982ebcc74: { adaptor: "central", uuid: "c84982ebcc74", module: "cylon-ble"},
      bluetooth_ollie_ee42664940f4: { adaptor: "central", uuid: "ee42664940f4", module: "cylon-ble"},
      bluetooth_bb8_f16fdb2b3b4f: { adaptor: "central", uuid: "f16fdb2b3b4f", module: "cylon-ble"}
   }

,
   devices: {

   }

,
// connect functions to bb8 & ollie
   connect_ollie_d8e38c77d05d: function() {
      this.device("ollie_d8e38c77d05d", {connection: "bluetooth_ollie_d8e38c77d05d", driver: "ollie", module: "cylon-sphero-ble"});
      this.startDevice(this.devices.ollie_d8e38c77d05d, function() {
         console.log("ollie_d8e38c77d05d connected");
      });
   }

,

   connect_ollie_dc712fb5b631: function() {
      this.device("ollie_dc712fb5b631", {connection: "bluetooth_ollie_dc712fb5b631", driver: "ollie", module: "cylon-sphero-ble"});
      this.startDevice(this.devices.ollie_dc712fb5b631, function() {
         console.log("ollie_dc712fb5b631 connected");
      });
   }

,

   connect_ollie_f15cee63622d: function() {
      this.device("ollie_f15cee63622d", {connection: "bluetooth_ollie_f15cee63622d", driver: "ollie", module: "cylon-sphero-ble"});
      this.startDevice(this.devices.ollie_f15cee63622d, function() {
         console.log("ollie_f15cee63622d connected");
      });
   }

,

   connect_ollie_c84982ebcc74: function() {
      this.device("ollie_c84982ebcc74", {connection: "bluetooth_ollie_c84982ebcc74", driver: "ollie", module: "cylon-sphero-ble"});
      this.startDevice(this.devices.ollie_c84982ebcc74, function() {
         console.log("ollie_c84982ebcc74 connected");
      });
   }

,

   connect_ollie_ee42664940f4: function() {
      this.device("ollie_ee42664940f4", {connection: "bluetooth_ollie_ee42664940f4", driver: "ollie", module: "cylon-sphero-ble"});
      this.startDevice(this.devices.ollie_ee42664940f4, function() {
         console.log("ollie_ee42664940f4 connected");
      });
   }

,

   connect_bb8_f16fdb2b3b4f: function() {
      this.device("bb8_f16fdb2b3b4f", {connection: "bluetooth_bb8_f16fdb2b3b4f", driver: "bb8", module: "cylon-sphero-ble"});
      this.startDevice(this.devices.bb8_f16fdb2b3b4f, function() {
         console.log("bb8_f16fdb2b3b4f connected");
      });
   }

,



   connect_bb8(my, mac, callback) {
      existed = 1;
      switch (mac) {
         case "f16fdb2b3b4f":
            my.connect_bb8_f16fdb2b3b4f();
            ollies["f16fdb2b3b4f"] = true;
            break;
         default:
            existed = 0;
      }
      if (existed) {
      callback();
      } else {
         console.log("We couldn't find bb8 with mac : " + mac);
      }
   }
,

   connect_ollie(my, mac, callback) {
      existed = 1;
      switch (mac) {
         case "d8e38c77d05d":
            my.connect_ollie_d8e38c77d05d();
            ollies["d8e38c77d05d"] = true;
            break;
         case "dc712fb5b631":
            my.connect_ollie_dc712fb5b631();
            ollies["dc712fb5b631"] = true;
            break;
         case "f15cee63622d":
            my.connect_ollie_f15cee63622d();
            ollies["f15cee63622d"] = true;
            break;
         case "c84982ebcc74":
            my.connect_ollie_c84982ebcc74();
            ollies["c84982ebcc74"] = true;
            break;
         case "ee42664940f4":
            my.connect_ollie_ee42664940f4();
            ollies["ee42664940f4"] = true;
            break;
         default:
            existed = 0;
      }
      if (existed) {
      callback();
      } else {
         console.log("We couldn't find ollie with mac : " + mac);
      }
   }


,

// roll bb8 & ollie
   roll_ollie_d8e38c77d05d: function(speed, direction) {
      this.devices.ollie_d8e38c77d05d.roll(speed, direction, function() {
         console.log("ollie_d8e38c77d05d roll with speed: " + speed + " direction: " + direction);
      });
   },


   roll_ollie_dc712fb5b631: function(speed, direction) {
      this.devices.ollie_dc712fb5b631.roll(speed, direction, function() {
         console.log("ollie_dc712fb5b631 roll with speed: " + speed + " direction: " + direction);
      });
   },


   roll_ollie_f15cee63622d: function(speed, direction) {
      this.devices.ollie_f15cee63622d.roll(speed, direction, function() {
         console.log("ollie_f15cee63622d roll with speed: " + speed + " direction: " + direction);
      });
   },


   roll_ollie_c84982ebcc74: function(speed, direction) {
      this.devices.ollie_c84982ebcc74.roll(speed, direction, function() {
         console.log("ollie_c84982ebcc74 roll with speed: " + speed + " direction: " + direction);
      });
   },


   roll_ollie_ee42664940f4: function(speed, direction) {
      this.devices.ollie_ee42664940f4.roll(speed, direction, function() {
         console.log("ollie_ee42664940f4 roll with speed: " + speed + " direction: " + direction);
      });
   },


   roll_bb8_f16fdb2b3b4f: function(speed, direction) {
      this.devices.bb8_f16fdb2b3b4f.roll(speed, direction, function() {
         console.log("bb8_f16fdb2b3b4f roll with speed: " + speed + " direction: " + direction);
      });
   },




   roll_bb8(my, mac, speed, direction, callback) {
      existed = 1;
      switch (mac) {
         case "f16fdb2b3b4f":
            my.roll_bb8_f16fdb2b3b4f(speed, direction);
            break;
         default:
            existed=0;
      }

      if (existed) {
         callback();
      } else {
         console.log("We couldn't find bb8 with mac : " + mac);
      }
   }
,

   roll_ollie(my, mac, speed, direction, callback) {
      existed = 1;
      switch (mac) {
         case "d8e38c77d05d":
            my.roll_ollie_d8e38c77d05d(speed, direction);
            break;
         case "dc712fb5b631":
            my.roll_ollie_dc712fb5b631(speed, direction);
            break;
         case "f15cee63622d":
            my.roll_ollie_f15cee63622d(speed, direction);
            break;
         case "c84982ebcc74":
            my.roll_ollie_c84982ebcc74(speed, direction);
            break;
         case "ee42664940f4":
            my.roll_ollie_ee42664940f4(speed, direction);
            break;
         default:
            existed=0;
      }

      if (existed) {
         callback();
      } else {
         console.log("We couldn't find ollie with mac : " + mac);
      }
   }




,
// set new color bb8
   setColor_ollie_d8e38c77d05d: function(newColor) {
      this.devices.ollie_d8e38c77d05d.color(newColor, function() {
         console.log("ollie_d8e38c77d05d set new color: " + newColor);
      });
},
   setColor_ollie_dc712fb5b631: function(newColor) {
      this.devices.ollie_dc712fb5b631.color(newColor, function() {
         console.log("ollie_dc712fb5b631 set new color: " + newColor);
      });
},
   setColor_ollie_f15cee63622d: function(newColor) {
      this.devices.ollie_f15cee63622d.color(newColor, function() {
         console.log("ollie_f15cee63622d set new color: " + newColor);
      });
},
   setColor_ollie_c84982ebcc74: function(newColor) {
      this.devices.ollie_c84982ebcc74.color(newColor, function() {
         console.log("ollie_c84982ebcc74 set new color: " + newColor);
      });
},
   setColor_ollie_ee42664940f4: function(newColor) {
      this.devices.ollie_ee42664940f4.color(newColor, function() {
         console.log("ollie_ee42664940f4 set new color: " + newColor);
      });
},
   setColor_bb8_f16fdb2b3b4f: function(newColor) {
      this.devices.bb8_f16fdb2b3b4f.color(newColor, function() {
         console.log("bb8_f16fdb2b3b4f set new color: " + newColor);
      });
},


   setColor_bb8(my, mac, newColor, callback) {
      existed = 1;
      switch (mac) {
         case "f16fdb2b3b4f":
            my.setColor_bb8_f16fdb2b3b4f(newColor);
            break;
         default:
            existed=0;
      }

      if (existed) {
         callback();
      } else {
         console.log("We couldn't find bb8 with mac : " + mac);
      }
   }
,


   setColor_ollie(my, mac, newColor, callback) {
      existed = 1;
      switch (mac) {
         case "d8e38c77d05d":
            my.setColor_ollie_d8e38c77d05d(newColor);
            break;
         case "dc712fb5b631":
            my.setColor_ollie_dc712fb5b631(newColor);
            break;
         case "f15cee63622d":
            my.setColor_ollie_f15cee63622d(newColor);
            break;
         case "c84982ebcc74":
            my.setColor_ollie_c84982ebcc74(newColor);
            break;
         case "ee42664940f4":
            my.setColor_ollie_ee42664940f4(newColor);
            break;
         default:
            existed=0;
      }

      if (existed) {
         callback();
      } else {
         console.log("We couldn't find ollie with mac : " + mac);
      }
   }


,
   isConnected(my, mac) {
      return ollies[mac] != null;
   }
,
   work: function(my) {

      var http = require('http');
      var url = require('url');

      var server = new http.Server(function(req, res) {
         var urlParsed = url.parse(req.url, true);
         console.log("Received url: " + urlParsed.pathname + " q: " + urlParsed.query);
         actionPerformed = 0;

         if (urlParsed.pathname.startsWith("/bb8/")) {

            if (urlParsed.pathname == "/bb8/isConnected" && urlParsed.query.MAC) {
               actionPerformed = 1;
               mac = urlParsed.query.MAC;
               console.log("ollie to connect: " + mac);
               console.log("isConnected to ollie, mac : " + mac);
               if (my.isConnected(my, mac)) {
                  res.end("connected");
               } else {
                  res.end("not");
               }
            }
            if (urlParsed.pathname == "/bb8/connect" && urlParsed.query.MAC) {
               mac = urlParsed.query.MAC;
               console.log("bb8 to connect: " + mac);
               my.connect_bb8(my, mac, function() {
                  console.log("connected to bb8, mac : " + mac);
                  res.end("connected");
                  actionPerformed = 1;
               });	
            }

            if (urlParsed.pathname == "/bb8/roll" && urlParsed.query.MAC
                  && urlParsed.query.speed && urlParsed.query.direction) {
               mac = urlParsed.query.MAC;
               newSpeed = parseInt(urlParsed.query.speed);
               newDirection = parseInt(urlParsed.query.direction);
               console.log("bb8: " + mac + " roll with speed: " + newSpeed + " & direction: " + newDirection);
               my.roll_bb8(my, mac, newSpeed, newDirection, function() {
                  console.log("rolled bb8, mac : " + mac);
                  res.end("rolled");
                  actionPerformed = 1;
               });	
            }

            if (urlParsed.pathname == "/bb8/setColor" && urlParsed.query.MAC
                  && urlParsed.query.color) {
               mac = urlParsed.query.MAC;
               newColor = parseInt(urlParsed.query.color);
               console.log("bb8: " + mac + " set new color: " + newColor);
               
               my.setColor_bb8(my, mac, newColor, function() {
                  console.log("set color of bb8, mac : " + mac);
                  res.end("set color");
                  actionPerformed = 1;
               });	
            }
         }

         if (urlParsed.pathname.startsWith("/ollie/")) {

            if (urlParsed.pathname == "/ollie/isConnected" && urlParsed.query.MAC) {
               actionPerformed = 1;
               mac = urlParsed.query.MAC;
               console.log("ollie to connect: " + mac);
               console.log("isConnected to ollie, mac : " + mac);
               if (my.isConnected(my, mac)) {
                  res.end("connected");
               } else {
                  res.end("not");
               }
            }
            if (urlParsed.pathname == "/ollie/connect" && urlParsed.query.MAC) {
               mac = urlParsed.query.MAC;
               console.log("ollie to connect: " + mac);
               my.connect_ollie(my, mac, function() {
                  console.log("connected to ollie, mac : " + mac);
                  res.end("connected");
                  actionPerformed = 1;
               });	
            }

            if (urlParsed.pathname == "/ollie/roll" && urlParsed.query.MAC
                  && urlParsed.query.speed && urlParsed.query.direction) {
               mac = urlParsed.query.MAC;
               newSpeed = parseInt(urlParsed.query.speed);
               newDirection = parseInt(urlParsed.query.direction);
               console.log("ollie: " + mac + " roll with speed: " + newSpeed + " & direction: " + newDirection);
               my.roll_ollie(my, mac, newSpeed, newDirection, function() {
                  console.log("rolled ollie, mac : " + mac);
                  res.end("rolled");
                  actionPerformed = 1;
               });	
            }

            if (urlParsed.pathname == "/ollie/setColor" && urlParsed.query.MAC
                  && urlParsed.query.color) {
               mac = urlParsed.query.MAC;
               newColor = parseInt(urlParsed.query.color);
               console.log("ollie: " + mac + " set new color: " + newColor);
               
               my.setColor_ollie(my, mac, newColor, function() {
                  console.log("set color of ollie, mac : " + mac);
                  res.end("set color");
                  actionPerformed = 1;
               });	
            }
         }

         if (actionPerformed==0) {
            res.statusCode = 404;
            res.end("Not found!");
         }
      });
      server.listen(1337, "127.0.0.1");
   }
}).start();
