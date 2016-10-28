// Wsl_F@ITeazer

var http = require('http');
var url = require('url');
var sphero = require("sphero");
var ollies = {};

var isConnectedOllie = function(mac) {
	return ollies[mac] != null;
}

var connectOllie = function(mac, callback) {
   ollies[mac] = sphero(mac, {timeout: 1000});
   ollie = ollies[mac];
   ollie.connect(function() {
      console.log("connected to Ollie. MAC: " + mac);
      callback();
   });
}

var disconnectOllie = function(mac, callback) {
   ollies[mac] = sphero(mac, {timeout: 1000});
   ollie = ollies[mac];
   ollie.disconnect(function() {
      console.log("disconnected from Ollie. MAC: " + mac);
      callback();
   });
}

var rollOllie = function(mac, speed, direction, callback) {
   ollie = ollies[mac];
   ollie.roll(parseInt(speed), parseInt(direction), function() {
      console.log("roll Ollie. MAC: "+mac + " speed: " + speed + " direction: " + direction);
      callback();
   });
}

var setColorOllie = function(mac, newColor, callback) {
   ollie = ollies[mac];
   ollie.color(parseInt(newColor), function() {
      console.log("set color of Ollie. MAC: " + mac + " color: " + newColor);
      callback();
   });
}


var setHeadingOllie = function(mac, heading, callback) {
   ollie = ollies[mac];
   ollie.setHeading(heading, function() {
      console.log("set heading for Ollie. MAC: " + mac + " heading: " + heading);
      callback();
   });
}

var startCalibrationOllie = function(mac, callback) {
   ollie = ollies[mac];
   ollie.startCalibration(function() {
      console.log("start calibration Ollie. MAC: " + mac);
      callback();
   });
}

var finishCalibrationOllie = function(mac, callback) {
   ollie = ollies[mac];
   ollie.finishCalibration(function() {
      console.log("finish calibration Ollie. MAC: " + mac);
      callback();
   });
}

var server = new http.Server(function(req, res) {

   var urlParsed = url.parse(req.url, true);
   console.log("Received url: " + urlParsed.pathname + " q: " + urlParsed.query);
   var actionPerformed = 0;
	
   if (urlParsed.pathname.startsWith('/ollie/')) {
      if (urlParsed.pathname == '/ollie/isConnected' && urlParsed.query.MAC) {
         if (isConnectedOllie(urlParsed.query.MAC)) {
            res.end("connected");
         } else {
		    res.end("not found");
		 }
         actionPerformed = 1;
      }

      if (urlParsed.pathname == '/ollie/connect' && urlParsed.query.MAC) {
         connectOllie(urlParsed.query.MAC, function() {
            res.end("Connected");
         });
         actionPerformed = 1;
      }

      if (urlParsed.pathname == '/ollie/disconnect' && urlParsed.query.MAC) {
         disconnectOllie(urlParsed.query.MAC, function() {
		    res.end("Disconnected"); 
		 });
         actionPerformed = 1;
      }

      if (urlParsed.pathname == '/ollie/roll' && urlParsed.query.MAC 
            && urlParsed.query.speed && urlParsed.query.direction) {
         rollOllie(urlParsed.query.MAC, urlParsed.query.speed, urlParsed.query.direction, function() {
		    res.end("Rolled");
		 });
         actionPerformed = 1;
      }
		
      if (urlParsed.pathname == "/ollie/setColor" && urlParsed.query.MAC
            && urlParsed.query.color) {
         setColorOllie(urlParsed.query.MAC, urlParsed.query.color, function() {
		    res.end("Setted color");
		 });
         actionPerformed = 1;
      }
		
      if (urlParsed.pathname == "/ollie/setHeading" && urlParsed.query.MAC
            && urlParsed.query.heading) {
         setHeadingOllie(urlParsed.query.MAC, urlParsed.query.heading, function() {
		    res.end("Setted heading");
		 });
         actionPerformed = 1;
      }

      if (urlParsed.pathname == "/ollie/startCalibration" && urlParsed.query.MAC) {
         startCalibrationOllie(urlParsed.query.MAC, function() {
		    res.end("Sarted calibration");
		 });
         actionPerformed = 1;
      }

      if (urlParsed.pathname == "/ollie/finishCalibration" && urlParsed.query.MAC) {
         finishCalibrationOllie(urlParsed.query.MAC, function() {
		    res.end("Finished calibration");
		 });
         actionPerformed = 1;
      }

   }

   if (actionPerformed == 0)
   {
      res.statusCode = 404;
      res.end("Not found!");
   }
});

server.listen(1337, '127.0.0.1');
console.log("")
console.log("Sphero server has been started")
console.log("You could access server at address http://localhost:1337")							 
console.log("")
console.log("*******************************************")
console.log("*******************************************")
console.log("**             W A R N I N G             **")
console.log("*******************************************")
console.log("**                                       **")
console.log("**  This server supports only one droid  **")
console.log("**                                       **")
console.log("*******************************************")
console.log("*******************************************")
console.log("")