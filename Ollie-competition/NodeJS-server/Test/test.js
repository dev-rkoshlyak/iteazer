var request = require("request");

function sleep(time, callback) {
    var stop = new Date().getTime();
    while(new Date().getTime() < stop + time) {
        ;
    }
    callback();
}

var path = "http://127.0.0.1:1337/ollie/"
var duration_move = 200;
var duration_blink = 50;
var speed = 0;
var shift = 80;

var connect = function(MAC, callback) {
	console.log("connect");
	request(path+"connect?MAC="+MAC, function(error, response, body) {
		if (body == "connected") {
            sleep(3000,	function() {
                callback();
            });
        }
	});
}

var rotate = function(MAC, angle) {
	console.log("rotate" + MAC);
    angle %= 360;
	request(path+"roll?MAC="+MAC+"&direction="+angle+"&speed="+speed, function(error, response, body) {
	    sleep(duration_move, function() {
            rotate(MAC, angle+shift);
		});
	});
}

var blink = function(MAC, newColor) {
    console.log("blink: " + MAC);
    newColor %= 0x1000000;
    
    var urlBlink = path+"setColor?MAC="+MAC+"&color="+newColor;
    request(urlBlink, function(error, resopnse, body) {
        if (body=="set color") {
            sleep(duration_blink, function() {    
                blink(MAC, newColor + 0x050607);
            });
        } else {
            console.log("body: " + body);
            console.log("url: " + urlBlink);
        }
    });   
}

var upDown = function(MAC, angle) {
    console.log("up&down: " + MAC);
    angle %= 360;
	request(path+"roll?MAC="+MAC+"&direction="+angle+"&speed=0", function(error, response, body) {
	    sleep(duration_move/1.5, function() {
            request(path+"roll?MAC="+MAC+"&direction="+angle+"&speed="+speed, function(error, response, body) {
                sleep(duration_move, function() {
                    request(path+"roll?MAC="+MAC+"&direction="+angle+"&speed=0", function(error, response, body) {
                        sleep(duration_move/1.5, function() {
                            upDown(MAC, angle+180);
                        });
                    });
                });
            });
		});
	});
}


exports.test = function(MAC, type) {
	console.log("test " + MAC);
    console.log(type);
    
	connect(MAC, function() {
        switch (type) {
            case "rotate":
                rotate(MAC, 0);
                break;
            case "blink":
                blink(MAC, 0xFF0000);
                break;
            case "upDown":
                upDown(MAC, 0);
                break;
            default:
                console.log("There is no " + type + " tests");
        }
	});
}
