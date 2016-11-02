var request = require("request");

function sleep(time, callback) {
    var stop = new Date().getTime();
    while(new Date().getTime() < stop + time) {
        ;
    }
    callback();
}

var path = "http://127.0.0.1:1337/ollie/"

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

var rotate = function(MAC, callback) {
	console.log("rotate" + MAC);
		request(path+"roll?MAC="+MAC+"&direction=0&speed=30", function(error, response, body) {
			sleep(250, function() {
				request(path+"roll?MAC="+MAC+"&direction=90&speed=30", function(error, response, body) {
					sleep(250, function() {
						request(path+"roll?MAC="+MAC+"&direction=180&speed=30", function(error, response, body) {
							sleep(250, function() {
								request(path+"roll?MAC="+MAC+"&direction=270&speed=30", function(error, response, body) {
									sleep(250, function() {
										callback(MAC, callback);
									});
								});
							});
						});
					});
				});
			});
		});
}

exports.test = function(MAC) {
	console.log("test " + MAC);
	connect(MAC, function() {
		rotate(MAC, rotate);
	});
}

//console.log("HI");
//test("f15cee63622d");