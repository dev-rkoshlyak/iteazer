// Wsl_F@ITeazer

var http = require('http');
var url = require('url');
var sphero = require("sphero");
var ollies = {};

var server = new http.Server(function(req, res) {

	var urlParsed = url.parse(req.url, true);
	console.log("Received url: " + urlParsed.pathname + " q: " + urlParsed.query);
	if (urlParsed.pathname.startsWith('/ollie/')) {
		if (urlParsed.pathname == '/ollie/connect' && urlParsed.query.MAC) {
			ollies[urlParsed.query.MAC] = sphero(urlParsed.query.MAC, {timeout: 1000});
			ollie = ollies[urlParsed.query.MAC];
			ollie.connect(function() {
				console.log("connected!");
			});
			res.end("Connecting");
		}

		if (urlParsed.pathname == '/ollie/disconnect' && urlParsed.query.MAC) {
			ollies[urlParsed.query.MAC] = sphero(urlParsed.query.MAC);
			ollie = ollies[urlParsed.query.MAC];
			ollie.disconnect(function() {
				console.log("disconnected!");
			});
			res.end("Disconnecting");
		}

		if (urlParsed.pathname == '/ollie/roll' && urlParsed.query.MAC 
			&& urlParsed.query.speed && urlParsed.query.direction) {
			ollie = ollies[urlParsed.query.MAC];
			ollie.roll(urlParsed.query.speed , urlParsed.query.direction);
			res.end("Connecting");
		}
		
		if (urlParsed.pathname == '/ollie/setColor' && urlParsed.query.MAC 
			&& urlParsed.query.r && urlParsed.query.g && urlParsed.query.b) {
			ollie = ollies[urlParsed.query.MAC];
			ollie.color({ r: urlParsed.query.r , g : urlParsed.query.g, b : urlParsed.query.b });
			res.end("setColor");
		}
		
		if (urlParsed.pathname == '/ollie/setName'  && urlParsed.query.MAC
		   && urlParsed.query.name) {
			ollie = ollies[urlParsed.query.MAC];
			ollie.setDeviceName(urlParsed.query.name, function(err, data) {
				console.log(err || "data: " + data);
				console.log("name: " + urlParsed.query.name);
			});
			res.end("setName");
		}
	}
	else
	{
		res.statusCode = 404;
		res.end("Not found!");
	}
});

server.listen(1337, '127.0.0.1');
							 