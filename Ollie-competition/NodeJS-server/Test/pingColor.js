var request = require("request");

function sleep(time, callback) {
    var stop = new Date().getTime();
    while(new Date().getTime() < stop + time) {
        ;
    }
    callback();
}

var path = "http://127.0.0.1:1337/ollie/"
var duration_blink = 10000;
var speed = 0;
var shift = 80;

var connect = function(MAC, callback) {
	console.log("connect");
	request(path+"isConnect?MAC="+MAC, function(error, response, body) {
		if (body != "connected") {
            request(path+"connect?MAC="+MAC, function(error2, response2, body2) {
                if (body2 == "connected") {
                    sleep(3000,	function() {
                        callback();
                    });
                }
            });
        } else {
            sleep(3000,	function() {
                callback();
            });        
        }
	});
}


var setColor = function(MAC, newColor) {
    console.log("setColor: " + MAC);
    newColor %= 0x1000000;
    
    var urlBlink = path+"setColor?MAC="+MAC+"&color="+newColor;
    request(urlBlink, function(error, resopnse, body) {
        if (body=="set color") {
            sleep(duration_blink, function() {    
                setColor(MAC, newColor);
            });
        } else {
            console.log("body: " + body);
            console.log("url: " + urlBlink);
        }
    });   
}



exports.ping = function(MAC, color) {
	console.log("ping " + MAC);
    
	connect(MAC, function() {
            setColor(MAC, color);
	});
}
