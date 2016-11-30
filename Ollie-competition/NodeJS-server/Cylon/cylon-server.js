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
        bluetooth_ollie_f16fdb2b3b4f: { adaptor: "central", uuid: "f16fdb2b3b4f", module: "cylon-ble"}
    }

,
    devices: {

    }

,
// connect functions to bb8 & ollie
    connect_ollie_d8e38c77d05d: function(callback) {
        this.device("ollie_d8e38c77d05d", {connection: "bluetooth_ollie_d8e38c77d05d", driver: "ollie", module: "cylon-sphero-ble"});
        this.startDevice(this.devices.ollie_d8e38c77d05d, function() {
            console.log("ollie_d8e38c77d05d connected");
            ollies["d8e38c77d05d"] = true;
            callback();
        });
    }

,

    connect_ollie_dc712fb5b631: function(callback) {
        this.device("ollie_dc712fb5b631", {connection: "bluetooth_ollie_dc712fb5b631", driver: "ollie", module: "cylon-sphero-ble"});
        this.startDevice(this.devices.ollie_dc712fb5b631, function() {
            console.log("ollie_dc712fb5b631 connected");
            ollies["dc712fb5b631"] = true;
            callback();
        });
    }

,

    connect_ollie_f15cee63622d: function(callback) {
        this.device("ollie_f15cee63622d", {connection: "bluetooth_ollie_f15cee63622d", driver: "ollie", module: "cylon-sphero-ble"});
        this.startDevice(this.devices.ollie_f15cee63622d, function() {
            console.log("ollie_f15cee63622d connected");
            ollies["f15cee63622d"] = true;
            callback();
        });
    }

,

    connect_ollie_c84982ebcc74: function(callback) {
        this.device("ollie_c84982ebcc74", {connection: "bluetooth_ollie_c84982ebcc74", driver: "ollie", module: "cylon-sphero-ble"});
        this.startDevice(this.devices.ollie_c84982ebcc74, function() {
            console.log("ollie_c84982ebcc74 connected");
            ollies["c84982ebcc74"] = true;
            callback();
        });
    }

,

    connect_ollie_ee42664940f4: function(callback) {
        this.device("ollie_ee42664940f4", {connection: "bluetooth_ollie_ee42664940f4", driver: "ollie", module: "cylon-sphero-ble"});
        this.startDevice(this.devices.ollie_ee42664940f4, function() {
            console.log("ollie_ee42664940f4 connected");
            ollies["ee42664940f4"] = true;
            callback();
        });
    }

,

    connect_ollie_f16fdb2b3b4f: function(callback) {
        this.device("ollie_f16fdb2b3b4f", {connection: "bluetooth_ollie_f16fdb2b3b4f", driver: "ollie", module: "cylon-sphero-ble"});
        this.startDevice(this.devices.ollie_f16fdb2b3b4f, function() {
            console.log("ollie_f16fdb2b3b4f connected");
            ollies["f16fdb2b3b4f"] = true;
            callback();
        });
    }

,



    // call connect cylon function
    connect_ollie(my, mac, callback) {
        existed = 1;
        switch (mac) {
            case "d8e38c77d05d":
                my.connect_ollie_d8e38c77d05d(callback);
                break;
            case "dc712fb5b631":
                my.connect_ollie_dc712fb5b631(callback);
                break;
            case "f15cee63622d":
                my.connect_ollie_f15cee63622d(callback);
                break;
            case "c84982ebcc74":
                my.connect_ollie_c84982ebcc74(callback);
                break;
            case "ee42664940f4":
                my.connect_ollie_ee42664940f4(callback);
                break;
            case "f16fdb2b3b4f":
                my.connect_ollie_f16fdb2b3b4f(callback);
                break;
            default:
                existed = 0;
        }

        if (!existed) {
            console.log("We couldn't find ollie with mac : " + mac);
        }
    }


,

// roll ollie
    roll_ollie_d8e38c77d05d: function(speed, direction, callback) {
        this.devices.ollie_d8e38c77d05d.roll(speed, direction, callback);
    },


    roll_ollie_dc712fb5b631: function(speed, direction, callback) {
        this.devices.ollie_dc712fb5b631.roll(speed, direction, callback);
    },


    roll_ollie_f15cee63622d: function(speed, direction, callback) {
        this.devices.ollie_f15cee63622d.roll(speed, direction, callback);
    },


    roll_ollie_c84982ebcc74: function(speed, direction, callback) {
        this.devices.ollie_c84982ebcc74.roll(speed, direction, callback);
    },


    roll_ollie_ee42664940f4: function(speed, direction, callback) {
        this.devices.ollie_ee42664940f4.roll(speed, direction, callback);
    },


    roll_ollie_f16fdb2b3b4f: function(speed, direction, callback) {
        this.devices.ollie_f16fdb2b3b4f.roll(speed, direction, callback);
    },




    // call roll cylon function
    roll_ollie(my, mac, speed, direction, callback) {
        existed = 1;
        switch (mac) {
            case "d8e38c77d05d":
                my.roll_ollie_d8e38c77d05d(speed, direction, callback);
                break;
            case "dc712fb5b631":
                my.roll_ollie_dc712fb5b631(speed, direction, callback);
                break;
            case "f15cee63622d":
                my.roll_ollie_f15cee63622d(speed, direction, callback);
                break;
            case "c84982ebcc74":
                my.roll_ollie_c84982ebcc74(speed, direction, callback);
                break;
            case "ee42664940f4":
                my.roll_ollie_ee42664940f4(speed, direction, callback);
                break;
            case "f16fdb2b3b4f":
                my.roll_ollie_f16fdb2b3b4f(speed, direction, callback);
                break;
            default:
                existed = 0;
        }

        if (!existed) {
            console.log("We couldn't find ollie with mac : " + mac);
        }
    }




,

// setColor ollie
    setColor_ollie_d8e38c77d05d: function(newColor, callback) {
        this.devices.ollie_d8e38c77d05d.color(newColor, callback);
    },


    setColor_ollie_dc712fb5b631: function(newColor, callback) {
        this.devices.ollie_dc712fb5b631.color(newColor, callback);
    },


    setColor_ollie_f15cee63622d: function(newColor, callback) {
        this.devices.ollie_f15cee63622d.color(newColor, callback);
    },


    setColor_ollie_c84982ebcc74: function(newColor, callback) {
        this.devices.ollie_c84982ebcc74.color(newColor, callback);
    },


    setColor_ollie_ee42664940f4: function(newColor, callback) {
        this.devices.ollie_ee42664940f4.color(newColor, callback);
    },


    setColor_ollie_f16fdb2b3b4f: function(newColor, callback) {
        this.devices.ollie_f16fdb2b3b4f.color(newColor, callback);
    },




    // call color cylon function
    setColor_ollie(my, mac, newColor, callback) {
        existed = 1;
        switch (mac) {
            case "d8e38c77d05d":
                my.setColor_ollie_d8e38c77d05d(newColor, callback);
                break;
            case "dc712fb5b631":
                my.setColor_ollie_dc712fb5b631(newColor, callback);
                break;
            case "f15cee63622d":
                my.setColor_ollie_f15cee63622d(newColor, callback);
                break;
            case "c84982ebcc74":
                my.setColor_ollie_c84982ebcc74(newColor, callback);
                break;
            case "ee42664940f4":
                my.setColor_ollie_ee42664940f4(newColor, callback);
                break;
            case "f16fdb2b3b4f":
                my.setColor_ollie_f16fdb2b3b4f(newColor, callback);
                break;
            default:
                existed = 0;
        }

        if (!existed) {
            console.log("We couldn't find ollie with mac : " + mac);
        }
    }




,

// setStabilization ollie
    setStabilization_ollie_d8e38c77d05d: function(stabilization, callback) {
        this.devices.ollie_d8e38c77d05d.setStabilization(stabilization, callback);
    },


    setStabilization_ollie_dc712fb5b631: function(stabilization, callback) {
        this.devices.ollie_dc712fb5b631.setStabilization(stabilization, callback);
    },


    setStabilization_ollie_f15cee63622d: function(stabilization, callback) {
        this.devices.ollie_f15cee63622d.setStabilization(stabilization, callback);
    },


    setStabilization_ollie_c84982ebcc74: function(stabilization, callback) {
        this.devices.ollie_c84982ebcc74.setStabilization(stabilization, callback);
    },


    setStabilization_ollie_ee42664940f4: function(stabilization, callback) {
        this.devices.ollie_ee42664940f4.setStabilization(stabilization, callback);
    },


    setStabilization_ollie_f16fdb2b3b4f: function(stabilization, callback) {
        this.devices.ollie_f16fdb2b3b4f.setStabilization(stabilization, callback);
    },




    // call setStabilization cylon function
    setStabilization_ollie(my, mac, stabilization, callback) {
        existed = 1;
        switch (mac) {
            case "d8e38c77d05d":
                my.setStabilization_ollie_d8e38c77d05d(stabilization, callback);
                break;
            case "dc712fb5b631":
                my.setStabilization_ollie_dc712fb5b631(stabilization, callback);
                break;
            case "f15cee63622d":
                my.setStabilization_ollie_f15cee63622d(stabilization, callback);
                break;
            case "c84982ebcc74":
                my.setStabilization_ollie_c84982ebcc74(stabilization, callback);
                break;
            case "ee42664940f4":
                my.setStabilization_ollie_ee42664940f4(stabilization, callback);
                break;
            case "f16fdb2b3b4f":
                my.setStabilization_ollie_f16fdb2b3b4f(stabilization, callback);
                break;
            default:
                existed = 0;
        }

        if (!existed) {
            console.log("We couldn't find ollie with mac : " + mac);
        }
    }




,
// get Velocity of bb8 & ollie
    getVelocity_ollie_d8e38c77d05d: function(sps, callback) {
        this.devices.ollie_d8e38c77d05d.streamVelocity(sps, false);
        this.devices.ollie_d8e38c77d05d.once("Velocity", callback);
    }

,

    getVelocity_ollie_dc712fb5b631: function(sps, callback) {
        this.devices.ollie_dc712fb5b631.streamVelocity(sps, false);
        this.devices.ollie_dc712fb5b631.once("Velocity", callback);
    }

,

    getVelocity_ollie_f15cee63622d: function(sps, callback) {
        this.devices.ollie_f15cee63622d.streamVelocity(sps, false);
        this.devices.ollie_f15cee63622d.once("Velocity", callback);
    }

,

    getVelocity_ollie_c84982ebcc74: function(sps, callback) {
        this.devices.ollie_c84982ebcc74.streamVelocity(sps, false);
        this.devices.ollie_c84982ebcc74.once("Velocity", callback);
    }

,

    getVelocity_ollie_ee42664940f4: function(sps, callback) {
        this.devices.ollie_ee42664940f4.streamVelocity(sps, false);
        this.devices.ollie_ee42664940f4.once("Velocity", callback);
    }

,

    getVelocity_ollie_f16fdb2b3b4f: function(sps, callback) {
        this.devices.ollie_f16fdb2b3b4f.streamVelocity(sps, false);
        this.devices.ollie_f16fdb2b3b4f.once("Velocity", callback);
    }

,



    getVelocity_bb8(my, mac, sps, callback) {
        existed = true;
        switch (mac) {
            default:
                existed = false;
        }
        if (!existed) {
            console.log("We couldn't find bb8 with mac : " + mac);
        }
    }
,

    getVelocity_ollie(my, mac, sps, callback) {
        existed = true;
        switch (mac) {
            case "d8e38c77d05d":
                my.getVelocity_ollie_d8e38c77d05d(sps, callback);
                break;
            case "dc712fb5b631":
                my.getVelocity_ollie_dc712fb5b631(sps, callback);
                break;
            case "f15cee63622d":
                my.getVelocity_ollie_f15cee63622d(sps, callback);
                break;
            case "c84982ebcc74":
                my.getVelocity_ollie_c84982ebcc74(sps, callback);
                break;
            case "ee42664940f4":
                my.getVelocity_ollie_ee42664940f4(sps, callback);
                break;
            case "f16fdb2b3b4f":
                my.getVelocity_ollie_f16fdb2b3b4f(sps, callback);
                break;
            default:
                existed = false;
        }
        if (!existed) {
            console.log("We couldn't find ollie with mac : " + mac);
        }
    }


,
// get AccelOne of bb8 & ollie
    getAccelOne_ollie_d8e38c77d05d: function(sps, callback) {
        this.devices.ollie_d8e38c77d05d.streamAccelOne(sps, false);
        this.devices.ollie_d8e38c77d05d.once("AccelOne", callback);
    }

,

    getAccelOne_ollie_dc712fb5b631: function(sps, callback) {
        this.devices.ollie_dc712fb5b631.streamAccelOne(sps, false);
        this.devices.ollie_dc712fb5b631.once("AccelOne", callback);
    }

,

    getAccelOne_ollie_f15cee63622d: function(sps, callback) {
        this.devices.ollie_f15cee63622d.streamAccelOne(sps, false);
        this.devices.ollie_f15cee63622d.once("AccelOne", callback);
    }

,

    getAccelOne_ollie_c84982ebcc74: function(sps, callback) {
        this.devices.ollie_c84982ebcc74.streamAccelOne(sps, false);
        this.devices.ollie_c84982ebcc74.once("AccelOne", callback);
    }

,

    getAccelOne_ollie_ee42664940f4: function(sps, callback) {
        this.devices.ollie_ee42664940f4.streamAccelOne(sps, false);
        this.devices.ollie_ee42664940f4.once("AccelOne", callback);
    }

,

    getAccelOne_ollie_f16fdb2b3b4f: function(sps, callback) {
        this.devices.ollie_f16fdb2b3b4f.streamAccelOne(sps, false);
        this.devices.ollie_f16fdb2b3b4f.once("AccelOne", callback);
    }

,



    getAccelOne_bb8(my, mac, sps, callback) {
        existed = true;
        switch (mac) {
            default:
                existed = false;
        }
        if (!existed) {
            console.log("We couldn't find bb8 with mac : " + mac);
        }
    }
,

    getAccelOne_ollie(my, mac, sps, callback) {
        existed = true;
        switch (mac) {
            case "d8e38c77d05d":
                my.getAccelOne_ollie_d8e38c77d05d(sps, callback);
                break;
            case "dc712fb5b631":
                my.getAccelOne_ollie_dc712fb5b631(sps, callback);
                break;
            case "f15cee63622d":
                my.getAccelOne_ollie_f15cee63622d(sps, callback);
                break;
            case "c84982ebcc74":
                my.getAccelOne_ollie_c84982ebcc74(sps, callback);
                break;
            case "ee42664940f4":
                my.getAccelOne_ollie_ee42664940f4(sps, callback);
                break;
            case "f16fdb2b3b4f":
                my.getAccelOne_ollie_f16fdb2b3b4f(sps, callback);
                break;
            default:
                existed = false;
        }
        if (!existed) {
            console.log("We couldn't find ollie with mac : " + mac);
        }
    }


,
// get ImuAngles of bb8 & ollie
    getImuAngles_ollie_d8e38c77d05d: function(sps, callback) {
        this.devices.ollie_d8e38c77d05d.streamImuAngles(sps, false);
        this.devices.ollie_d8e38c77d05d.once("ImuAngles", callback);
    }

,

    getImuAngles_ollie_dc712fb5b631: function(sps, callback) {
        this.devices.ollie_dc712fb5b631.streamImuAngles(sps, false);
        this.devices.ollie_dc712fb5b631.once("ImuAngles", callback);
    }

,

    getImuAngles_ollie_f15cee63622d: function(sps, callback) {
        this.devices.ollie_f15cee63622d.streamImuAngles(sps, false);
        this.devices.ollie_f15cee63622d.once("ImuAngles", callback);
    }

,

    getImuAngles_ollie_c84982ebcc74: function(sps, callback) {
        this.devices.ollie_c84982ebcc74.streamImuAngles(sps, false);
        this.devices.ollie_c84982ebcc74.once("ImuAngles", callback);
    }

,

    getImuAngles_ollie_ee42664940f4: function(sps, callback) {
        this.devices.ollie_ee42664940f4.streamImuAngles(sps, false);
        this.devices.ollie_ee42664940f4.once("ImuAngles", callback);
    }

,

    getImuAngles_ollie_f16fdb2b3b4f: function(sps, callback) {
        this.devices.ollie_f16fdb2b3b4f.streamImuAngles(sps, false);
        this.devices.ollie_f16fdb2b3b4f.once("ImuAngles", callback);
    }

,



    getImuAngles_bb8(my, mac, sps, callback) {
        existed = true;
        switch (mac) {
            default:
                existed = false;
        }
        if (!existed) {
            console.log("We couldn't find bb8 with mac : " + mac);
        }
    }
,

    getImuAngles_ollie(my, mac, sps, callback) {
        existed = true;
        switch (mac) {
            case "d8e38c77d05d":
                my.getImuAngles_ollie_d8e38c77d05d(sps, callback);
                break;
            case "dc712fb5b631":
                my.getImuAngles_ollie_dc712fb5b631(sps, callback);
                break;
            case "f15cee63622d":
                my.getImuAngles_ollie_f15cee63622d(sps, callback);
                break;
            case "c84982ebcc74":
                my.getImuAngles_ollie_c84982ebcc74(sps, callback);
                break;
            case "ee42664940f4":
                my.getImuAngles_ollie_ee42664940f4(sps, callback);
                break;
            case "f16fdb2b3b4f":
                my.getImuAngles_ollie_f16fdb2b3b4f(sps, callback);
                break;
            default:
                existed = false;
        }
        if (!existed) {
            console.log("We couldn't find ollie with mac : " + mac);
        }
    }


,
// get Accelerometer of bb8 & ollie
    getAccelerometer_ollie_d8e38c77d05d: function(sps, callback) {
        this.devices.ollie_d8e38c77d05d.streamAccelerometer(sps, false);
        this.devices.ollie_d8e38c77d05d.once("Accelerometer", callback);
    }

,

    getAccelerometer_ollie_dc712fb5b631: function(sps, callback) {
        this.devices.ollie_dc712fb5b631.streamAccelerometer(sps, false);
        this.devices.ollie_dc712fb5b631.once("Accelerometer", callback);
    }

,

    getAccelerometer_ollie_f15cee63622d: function(sps, callback) {
        this.devices.ollie_f15cee63622d.streamAccelerometer(sps, false);
        this.devices.ollie_f15cee63622d.once("Accelerometer", callback);
    }

,

    getAccelerometer_ollie_c84982ebcc74: function(sps, callback) {
        this.devices.ollie_c84982ebcc74.streamAccelerometer(sps, false);
        this.devices.ollie_c84982ebcc74.once("Accelerometer", callback);
    }

,

    getAccelerometer_ollie_ee42664940f4: function(sps, callback) {
        this.devices.ollie_ee42664940f4.streamAccelerometer(sps, false);
        this.devices.ollie_ee42664940f4.once("Accelerometer", callback);
    }

,

    getAccelerometer_ollie_f16fdb2b3b4f: function(sps, callback) {
        this.devices.ollie_f16fdb2b3b4f.streamAccelerometer(sps, false);
        this.devices.ollie_f16fdb2b3b4f.once("Accelerometer", callback);
    }

,



    getAccelerometer_bb8(my, mac, sps, callback) {
        existed = true;
        switch (mac) {
            default:
                existed = false;
        }
        if (!existed) {
            console.log("We couldn't find bb8 with mac : " + mac);
        }
    }
,

    getAccelerometer_ollie(my, mac, sps, callback) {
        existed = true;
        switch (mac) {
            case "d8e38c77d05d":
                my.getAccelerometer_ollie_d8e38c77d05d(sps, callback);
                break;
            case "dc712fb5b631":
                my.getAccelerometer_ollie_dc712fb5b631(sps, callback);
                break;
            case "f15cee63622d":
                my.getAccelerometer_ollie_f15cee63622d(sps, callback);
                break;
            case "c84982ebcc74":
                my.getAccelerometer_ollie_c84982ebcc74(sps, callback);
                break;
            case "ee42664940f4":
                my.getAccelerometer_ollie_ee42664940f4(sps, callback);
                break;
            case "f16fdb2b3b4f":
                my.getAccelerometer_ollie_f16fdb2b3b4f(sps, callback);
                break;
            default:
                existed = false;
        }
        if (!existed) {
            console.log("We couldn't find ollie with mac : " + mac);
        }
    }


,
// get Gyroscope of bb8 & ollie
    getGyroscope_ollie_d8e38c77d05d: function(sps, callback) {
        this.devices.ollie_d8e38c77d05d.streamGyroscope(sps, false);
        this.devices.ollie_d8e38c77d05d.once("Gyroscope", callback);
    }

,

    getGyroscope_ollie_dc712fb5b631: function(sps, callback) {
        this.devices.ollie_dc712fb5b631.streamGyroscope(sps, false);
        this.devices.ollie_dc712fb5b631.once("Gyroscope", callback);
    }

,

    getGyroscope_ollie_f15cee63622d: function(sps, callback) {
        this.devices.ollie_f15cee63622d.streamGyroscope(sps, false);
        this.devices.ollie_f15cee63622d.once("Gyroscope", callback);
    }

,

    getGyroscope_ollie_c84982ebcc74: function(sps, callback) {
        this.devices.ollie_c84982ebcc74.streamGyroscope(sps, false);
        this.devices.ollie_c84982ebcc74.once("Gyroscope", callback);
    }

,

    getGyroscope_ollie_ee42664940f4: function(sps, callback) {
        this.devices.ollie_ee42664940f4.streamGyroscope(sps, false);
        this.devices.ollie_ee42664940f4.once("Gyroscope", callback);
    }

,

    getGyroscope_ollie_f16fdb2b3b4f: function(sps, callback) {
        this.devices.ollie_f16fdb2b3b4f.streamGyroscope(sps, false);
        this.devices.ollie_f16fdb2b3b4f.once("Gyroscope", callback);
    }

,



    getGyroscope_bb8(my, mac, sps, callback) {
        existed = true;
        switch (mac) {
            default:
                existed = false;
        }
        if (!existed) {
            console.log("We couldn't find bb8 with mac : " + mac);
        }
    }
,

    getGyroscope_ollie(my, mac, sps, callback) {
        existed = true;
        switch (mac) {
            case "d8e38c77d05d":
                my.getGyroscope_ollie_d8e38c77d05d(sps, callback);
                break;
            case "dc712fb5b631":
                my.getGyroscope_ollie_dc712fb5b631(sps, callback);
                break;
            case "f15cee63622d":
                my.getGyroscope_ollie_f15cee63622d(sps, callback);
                break;
            case "c84982ebcc74":
                my.getGyroscope_ollie_c84982ebcc74(sps, callback);
                break;
            case "ee42664940f4":
                my.getGyroscope_ollie_ee42664940f4(sps, callback);
                break;
            case "f16fdb2b3b4f":
                my.getGyroscope_ollie_f16fdb2b3b4f(sps, callback);
                break;
            default:
                existed = false;
        }
        if (!existed) {
            console.log("We couldn't find ollie with mac : " + mac);
        }
    }


,
// get MotorsBackEmf of bb8 & ollie
    getMotorsBackEmf_ollie_d8e38c77d05d: function(sps, callback) {
        this.devices.ollie_d8e38c77d05d.streamMotorsBackEmf(sps, false);
        this.devices.ollie_d8e38c77d05d.once("MotorsBackEmf", callback);
    }

,

    getMotorsBackEmf_ollie_dc712fb5b631: function(sps, callback) {
        this.devices.ollie_dc712fb5b631.streamMotorsBackEmf(sps, false);
        this.devices.ollie_dc712fb5b631.once("MotorsBackEmf", callback);
    }

,

    getMotorsBackEmf_ollie_f15cee63622d: function(sps, callback) {
        this.devices.ollie_f15cee63622d.streamMotorsBackEmf(sps, false);
        this.devices.ollie_f15cee63622d.once("MotorsBackEmf", callback);
    }

,

    getMotorsBackEmf_ollie_c84982ebcc74: function(sps, callback) {
        this.devices.ollie_c84982ebcc74.streamMotorsBackEmf(sps, false);
        this.devices.ollie_c84982ebcc74.once("MotorsBackEmf", callback);
    }

,

    getMotorsBackEmf_ollie_ee42664940f4: function(sps, callback) {
        this.devices.ollie_ee42664940f4.streamMotorsBackEmf(sps, false);
        this.devices.ollie_ee42664940f4.once("MotorsBackEmf", callback);
    }

,

    getMotorsBackEmf_ollie_f16fdb2b3b4f: function(sps, callback) {
        this.devices.ollie_f16fdb2b3b4f.streamMotorsBackEmf(sps, false);
        this.devices.ollie_f16fdb2b3b4f.once("MotorsBackEmf", callback);
    }

,



    getMotorsBackEmf_bb8(my, mac, sps, callback) {
        existed = true;
        switch (mac) {
            default:
                existed = false;
        }
        if (!existed) {
            console.log("We couldn't find bb8 with mac : " + mac);
        }
    }
,

    getMotorsBackEmf_ollie(my, mac, sps, callback) {
        existed = true;
        switch (mac) {
            case "d8e38c77d05d":
                my.getMotorsBackEmf_ollie_d8e38c77d05d(sps, callback);
                break;
            case "dc712fb5b631":
                my.getMotorsBackEmf_ollie_dc712fb5b631(sps, callback);
                break;
            case "f15cee63622d":
                my.getMotorsBackEmf_ollie_f15cee63622d(sps, callback);
                break;
            case "c84982ebcc74":
                my.getMotorsBackEmf_ollie_c84982ebcc74(sps, callback);
                break;
            case "ee42664940f4":
                my.getMotorsBackEmf_ollie_ee42664940f4(sps, callback);
                break;
            case "f16fdb2b3b4f":
                my.getMotorsBackEmf_ollie_f16fdb2b3b4f(sps, callback);
                break;
            default:
                existed = false;
        }
        if (!existed) {
            console.log("We couldn't find ollie with mac : " + mac);
        }
    }


,
// get Odometer of bb8 & ollie
    getOdometer_ollie_d8e38c77d05d: function(sps, callback) {
        this.devices.ollie_d8e38c77d05d.streamOdometer(sps, false);
        this.devices.ollie_d8e38c77d05d.once("Odometer", callback);
    }

,

    getOdometer_ollie_dc712fb5b631: function(sps, callback) {
        this.devices.ollie_dc712fb5b631.streamOdometer(sps, false);
        this.devices.ollie_dc712fb5b631.once("Odometer", callback);
    }

,

    getOdometer_ollie_f15cee63622d: function(sps, callback) {
        this.devices.ollie_f15cee63622d.streamOdometer(sps, false);
        this.devices.ollie_f15cee63622d.once("Odometer", callback);
    }

,

    getOdometer_ollie_c84982ebcc74: function(sps, callback) {
        this.devices.ollie_c84982ebcc74.streamOdometer(sps, false);
        this.devices.ollie_c84982ebcc74.once("Odometer", callback);
    }

,

    getOdometer_ollie_ee42664940f4: function(sps, callback) {
        this.devices.ollie_ee42664940f4.streamOdometer(sps, false);
        this.devices.ollie_ee42664940f4.once("Odometer", callback);
    }

,

    getOdometer_ollie_f16fdb2b3b4f: function(sps, callback) {
        this.devices.ollie_f16fdb2b3b4f.streamOdometer(sps, false);
        this.devices.ollie_f16fdb2b3b4f.once("Odometer", callback);
    }

,



    getOdometer_bb8(my, mac, sps, callback) {
        existed = true;
        switch (mac) {
            default:
                existed = false;
        }
        if (!existed) {
            console.log("We couldn't find bb8 with mac : " + mac);
        }
    }
,

    getOdometer_ollie(my, mac, sps, callback) {
        existed = true;
        switch (mac) {
            case "d8e38c77d05d":
                my.getOdometer_ollie_d8e38c77d05d(sps, callback);
                break;
            case "dc712fb5b631":
                my.getOdometer_ollie_dc712fb5b631(sps, callback);
                break;
            case "f15cee63622d":
                my.getOdometer_ollie_f15cee63622d(sps, callback);
                break;
            case "c84982ebcc74":
                my.getOdometer_ollie_c84982ebcc74(sps, callback);
                break;
            case "ee42664940f4":
                my.getOdometer_ollie_ee42664940f4(sps, callback);
                break;
            case "f16fdb2b3b4f":
                my.getOdometer_ollie_f16fdb2b3b4f(sps, callback);
                break;
            default:
                existed = false;
        }
        if (!existed) {
            console.log("We couldn't find ollie with mac : " + mac);
        }
    }


,
    isConnected(my, mac) {
        return ollies[mac] == true;
    }
,
    work: function(my) {

        var http = require('http');
        var url = require('url');

        var server = new http.Server(function(req, res) {
            var urlParsed = url.parse(req.url, true);
            console.log("Received url: " + urlParsed.pathname + " q: " + urlParsed.query);
            actionPerformed = 0;

            if (urlParsed.pathname.startsWith("/ollie/")) {

                if (urlParsed.pathname == "/ollie/isConnected" && urlParsed.query.MAC) {
                    actionPerformed = 1;
                    mac = urlParsed.query.MAC;
                    console.log("isConnected to ollie, mac : " + mac);
                    if (my.isConnected(my, mac)) {
                        res.end("connected");
                    } else {
                        res.end("not");
                    }
                }
                if (urlParsed.pathname == "/ollie/connect" && urlParsed.query.MAC) {
                    actionPerformed = 1;
                    mac = urlParsed.query.MAC;
                    console.log("ollie to connect: " + mac);
                    my.connect_ollie(my, mac, function() {
                        console.log("connected to ollie, mac : " + mac);
                        res.end("connected");
                    });	
                }

                if (urlParsed.pathname == "/ollie/roll" && urlParsed.query.MAC
                        && urlParsed.query.speed && urlParsed.query.direction) {
                    actionPerformed = 1;
                    mac = urlParsed.query.MAC;
                    newSpeed = parseInt(urlParsed.query.speed);
                    newDirection = parseInt(urlParsed.query.direction);
                    console.log("ollie: " + mac + " roll with speed: " + newSpeed + " & direction: " + newDirection);
                    my.roll_ollie(my, mac, newSpeed, newDirection, function() {
                        console.log("rolled ollie, mac : " + mac);
                        res.end("rolled");
                    });	
                }

                if (urlParsed.pathname == "/ollie/setColor" && urlParsed.query.MAC
                        && urlParsed.query.color) {
                    actionPerformed = 1;
                    mac = urlParsed.query.MAC;
                    newColor = parseInt(urlParsed.query.color);
                    console.log("ollie: " + mac + " set new color: " + newColor);
                    
                    my.setColor_ollie(my, mac, newColor, function() {
                        console.log("set color of ollie, mac : " + mac);
                        res.end("set color");
                    });	
                }

                if (urlParsed.pathname == "/ollie/setStabilization" && urlParsed.query.MAC
                        && urlParsed.query.stabilization) {
                    actionPerformed = 1;
                    mac = urlParsed.query.MAC;
                    st = Boolean(parseInt(urlParsed.query.stabilization));
                    console.log("ollie: " + mac + " set stabilization: " + st);
                    
                    my.setStabilization_ollie(my, mac, st, function() {
                        console.log("set stabilization of ollie, mac : " + mac);
                        res.end("set stabilization");
                    });	
                }

                if (urlParsed.pathname == "/ollie/getVelocity" && urlParsed.query.MAC) {
                    actionPerformed = 1;
                    mac = urlParsed.query.MAC;
                    console.log("ollie: " + mac + " get velocity ");
                    
                    my.getVelocity_ollie(my, mac, 5, function(data) {
                        console.log("velocity: " + JSON.stringify(data) + "\n");
                        res.end("xVelocity: " + data.xVelocity.value[0] + "\n" + "yVelocity: " + data.yVelocity.value[0] + "\n");
                    });	
                }

                if (urlParsed.pathname == "/ollie/getAccelOne" && urlParsed.query.MAC) {
                    actionPerformed = 1;
                    mac = urlParsed.query.MAC;
                    console.log("ollie: " + mac + " get accelone ");
                    
                    my.getAccelOne_ollie(my, mac, 5, function(data) {
                        console.log("accelone: " + JSON.stringify(data) + "\n");
                        res.end("accelOne: " + data.accelOne.value[0] + "\n");
                    });	
                }

                if (urlParsed.pathname == "/ollie/getImuAngles" && urlParsed.query.MAC) {
                    actionPerformed = 1;
                    mac = urlParsed.query.MAC;
                    console.log("ollie: " + mac + " get imuangles ");
                    
                    my.getImuAngles_ollie(my, mac, 5, function(data) {
                        console.log("imuangles: " + JSON.stringify(data) + "\n");
                        res.end("pitchAngle: " + data.pitchAngle.value[0] + "\n"+ "rollAngle: " + data.rollAngle.value[0] + "\n"+ "yawAngle: " + data.yawAngle.value[0] + "\n");
                    });	
                }

                if (urlParsed.pathname == "/ollie/getAccelerometer" && urlParsed.query.MAC) {
                    actionPerformed = 1;
                    mac = urlParsed.query.MAC;
                    console.log("ollie: " + mac + " get accelerometer ");
                    
                    my.getAccelerometer_ollie(my, mac, 5, function(data) {
                        console.log("accelerometer: " + JSON.stringify(data) + "\n");
                        res.end("xAccel: " + data.xAccel.value[0] + "\n"+ "yAccel: " + data.yAccel.value[0] + "\n"+ "zAccel: " + data.zAccel.value[0] + "\n");
                    });	
                }

                if (urlParsed.pathname == "/ollie/getGyroscope" && urlParsed.query.MAC) {
                    actionPerformed = 1;
                    mac = urlParsed.query.MAC;
                    console.log("ollie: " + mac + " get gyroscope ");
                    
                    my.getGyroscope_ollie(my, mac, 5, function(data) {
                        console.log("gyroscope: " + JSON.stringify(data) + "\n");
                        res.end("xGyro: " + data.xGyro.value[0] + "\n"+ "yGyro: " + data.yGyro.value[0] + "\n"+ "zGyro: " + data.zGyro.value[0] + "\n");
                    });	
                }

                if (urlParsed.pathname == "/ollie/getMotorsBackEmf" && urlParsed.query.MAC) {
                    actionPerformed = 1;
                    mac = urlParsed.query.MAC;
                    console.log("ollie: " + mac + " get motorsbackemf ");
                    
                    my.getMotorsBackEmf_ollie(my, mac, 5, function(data) {
                        console.log("motorsbackemf: " + JSON.stringify(data) + "\n");
                        res.end("rMotorBackEmf: " + data.rMotorBackEmf.value[0] + "\n"+ "lMotorBackEmf: " + data.lMotorBackEmf.value[0] + "\n");
                    });	
                }

                if (urlParsed.pathname == "/ollie/getOdometer" && urlParsed.query.MAC) {
                    actionPerformed = 1;
                    mac = urlParsed.query.MAC;
                    console.log("ollie: " + mac + " get odometer ");
                    
                    my.getOdometer_ollie(my, mac, 5, function(data) {
                        console.log("odometer: " + JSON.stringify(data) + "\n");
                        res.end("xOdometer: " + data.xOdometer.value[0] + "\n"+ "yOdometer: " + data.yOdometer.value[0] + "\n");
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
