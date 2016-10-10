// Wsl_F@ITeazer

var sphero = require("sphero");

ollie1 = sphero('DC:71:2F:B5:B6:31', {timeout: 1000});
ollie1.connect(function() {
	console.log("Ollie1 connected");
	setInterval(function() {
			ollie1.randomColor(function(err, data) {
				console.log("Ollie1 set random color");
				console.log(err || "Random Color!");
			});

	}, 1000);
});