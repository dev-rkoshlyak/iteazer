// Wsl_F@ITeazer

var sphero = require("sphero");

ollie2 = sphero('D8:E3:8C:77:D0:5D', {timeout: 1000});
ollie2.connect(function() {
	console.log("Ollie2 connected");
	setInterval(function() {
			ollie2.randomColor(function(err, data) {
				console.log("Ollie2 set random color");
				console.log(err || "Random Color!");
			});

	}, 1000);
});