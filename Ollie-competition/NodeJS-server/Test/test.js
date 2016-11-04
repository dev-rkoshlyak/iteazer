var request = require("request");

function sleep(time, callback) {
    var stop = new Date().getTime();
    while(new Date().getTime() < stop + time) {
        ;
    }
    callback();
}

var path = "http://127.0.0.1:1337/ollie/"
var duration = 200;
var speed = 40;
var shift = 80;

var connect = function(MAC, callback) {
	console.log("connect");
	request(path+"connect?MAC="+MAC, function(error, response, body) {
		if (body == "connected") {
            sleep(duration*10,	function() {
                callback();
            });
        }
	});
}

var rotate = function(MAC, angle) {
	console.log("rotate" + MAC);
    angle %= 360;
	request(path+"roll?MAC="+MAC+"&direction="+angle+"&speed="+speed, function(error, response, body) {
	    sleep(duration, function() {
            rotate(MAC, angle+shift);
		});
	});
}

exports.test = function(MAC) {
	console.log("test " + MAC);
	connect(MAC, function() {
		rotate(MAC, 0);
	});
}
