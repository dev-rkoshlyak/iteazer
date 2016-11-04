package com.iteazer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class ServerCylonGenerator {

    static final String ENDL = "\r\n";
    static final String SPACE = "    ";
    static final int MAX_TAB = 10;
    static final String[] TAB;

    static {
        TAB = new String[MAX_TAB + 1];
        for (int i = 0; i <= MAX_TAB; i++) {
            TAB[i] = new String(new char[i]).replace("\0", SPACE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Droid> droids = getDroids("droids.txt");
        generateFile("cylon-server-generated.js", droids);
    }

    private static ArrayList<Droid> getDroids(String inputFileName) {
        ArrayList<Droid> droids = new ArrayList<>();
        try {
            Path filePath = Paths.get(inputFileName);
            List<String> lines = Files.readAllLines(filePath);
            for (String s : lines) {
                try {
                    droids.add(new Droid(s));
                } catch (Exception ex) {
                    System.err.println("Couldn't create droid: \"" + s + "\"");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerCylonGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return droids;
    }

    private static void generateFile(String fileName, ArrayList<Droid> droids) {

        try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {
            ArrayList<String> blocks = generate(droids);
            for (String block : blocks) {
                writer.write(block + ENDL);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(ServerCylonGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String generateHeader() {
        return "//Wsl_F@ITeazer" + ENDL
                + "//this file genareted by Server-Cylon-Generator" + ENDL
                + ENDL + "var Cylon = require(\"cylon\");" + ENDL
                + "var ollies = {};" + ENDL + ENDL
                + "Cylon.robot({" + ENDL;
    }

    private static String generateBluetoothConnections(ArrayList<Droid> droids) {
        StringBuilder connections = new StringBuilder(TAB[1] + "connections: {");
        connections.append(ENDL);

        for (Droid droid : droids) {
            //bluetooth_bb8_f16fdb2b3b4f: { adaptor: "central", uuid: "f16fdb2b3b4f", module: "cylon-ble"}
            String connection = String.format(TAB[2] + "bluetooth_%1$s_%2$s: { adaptor: \"central\", uuid: \"%2$s\", module: \"cylon-ble\"}", droid.type, droid.mac);
            connections.append(connection).append("," + ENDL);
        }
        connections.deleteCharAt(connections.lastIndexOf(","));
        connections.append(TAB[1] + "}").append(ENDL);
        return connections.toString();
    }

    private static String generateDiveces(ArrayList<Droid> droids) {
        return TAB[1] + "devices: {" + ENDL + ENDL + TAB[1] + "}" + ENDL;
    }

    private static String generateConnection(Droid droid) {
        StringBuilder connection = new StringBuilder();
        String s;

        s = TAB[1] + String.format("connect_%1$s_%2$s: function(callback) {", droid.type, droid.mac);
        connection.append(s).append(ENDL);

        s = TAB[2] + String.format("this.device(\"%1$s\", {connection: \"bluetooth_%1$s\", driver: \"%2$s\", module: \"cylon-sphero-ble\"});", droid.name, droid.type);
        connection.append(s).append(ENDL);

        s = TAB[2] + String.format("this.startDevice(this.devices.%1$s, function() {", droid.name);
        connection.append(s).append(ENDL);

        s = TAB[3] + String.format("console.log(\"%1$s connected\");", droid.name);
        connection.append(s).append(ENDL);

        s = String.format(TAB[3] + "ollies[\"%1$s\"] = true;", droid.mac);
        connection.append(s).append(ENDL);

        s = TAB[3] + "callback();";
        connection.append(s).append(ENDL);

        connection.append(TAB[2] + "});").append(ENDL);
        connection.append(TAB[1] + "}").append(ENDL);

        return connection.toString();
    }

    private static ArrayList<String> generateConnection(String type, ArrayList<Droid> droids) {
        ArrayList<String> connection = new ArrayList<>();
        String s;

        s = TAB[1] + String.format("connect_%1$s(my, mac, callback) {", type);
        connection.add(s);

        connection.add(TAB[2] + "existed = 1;");
        connection.add(TAB[2] + "switch (mac) {");

        for (Droid droid : droids) {
            if (droid.type.equals(type)) {
                connection.add(String.format(TAB[3] + "case \"%1$s\":", droid.mac));
                connection.add(String.format(TAB[4] + "my.connect_%1$s(callback);", droid.name));
                connection.add(TAB[4] + "break;");
            }
        }

        connection.add(TAB[3] + "default:");
        connection.add(TAB[4] + "existed = 0;");
        connection.add(TAB[2] + "}");

        connection.add(TAB[2] + "if (existed) {");
        connection.add(TAB[2] + "console.log(\"Start connecting to droid\");");
        connection.add(TAB[2] + "} else {");
        connection.add(TAB[3] + String.format("console.log(\"We couldn't find %1$s with mac : \" + mac);", type));
        connection.add(TAB[2] + "}");
        connection.add(TAB[1] + "}");
        return connection;
    }

    private static ArrayList<String> generateConnections(ArrayList<Droid> droids) {
        ArrayList<String> functions = new ArrayList<>();
        functions.add("// connect functions to bb8 & ollie");

        for (Droid droid : droids) {
            functions.add(generateConnection(droid));
            functions.add("," + ENDL);
        }

        functions.add(ENDL);

        functions.addAll(generateConnection("bb8", droids));
        functions.add("," + ENDL);

        functions.addAll(generateConnection("ollie", droids));
        functions.add(ENDL);

        return functions;
    }

    private static ArrayList<String> generateRoll(String type, ArrayList<Droid> droids) {
        ArrayList<String> function = new ArrayList<>();
        function.add(TAB[1] + String.format("roll_%1$s(my, mac, speed, direction, callback) {", type));
        function.add(TAB[2] + "existed = 1;");
        function.add(TAB[2] + "switch (mac) {");

        for (Droid droid : droids) {
            if (droid.type.equals(type)) {
                function.add(TAB[3] + String.format("case \"%1$s\":", droid.mac));
                function.add(TAB[4] + String.format("my.roll_%1$s(speed, direction);", droid.name));
                function.add(TAB[4] + "break;");
            }
        }

        function.add(TAB[3] + "default:");
        function.add(TAB[4] + "existed=0;");
        function.add(TAB[2] + "}");
        function.add("");

        function.add(TAB[2] + "if (existed) {");
        function.add(TAB[3] + "callback();");
        function.add(TAB[2] + "} else {");
        function.add(TAB[3] + String.format("console.log(\"We couldn't find %1$s with mac : \" + mac);", type));
        function.add(TAB[2] + "}");
        function.add(TAB[1] + "}");

        return function;
    }

    private static ArrayList<String> generateRoll(ArrayList<Droid> droids) {
        ArrayList<String> functions = new ArrayList<>();
        functions.add(ENDL + "// roll bb8 & ollie");

        for (Droid droid : droids) {
            functions.add(TAB[1] + String.format("roll_%1$s: function(speed, direction) {", droid.name));
            functions.add(TAB[2] + String.format("this.devices.%1$s.roll(speed, direction, function() {", droid.name));
            functions.add(TAB[3] + String.format("console.log(\"%1$s roll with speed: \" + speed + \" direction: \" + direction);", droid.name));
            functions.add(TAB[2] + "});");
            functions.add(TAB[1] + "},");
            functions.add(ENDL);
        }

        functions.add(ENDL);
        functions.addAll(generateRoll("bb8", droids));
        functions.add("," + ENDL);

        functions.addAll(generateRoll("ollie", droids));
        functions.add(ENDL);
        functions.add(ENDL);

        return functions;
    }

    private static ArrayList<String> generateSetColor(String type, ArrayList<Droid> droids) {
        ArrayList<String> function = new ArrayList<>();
        function.add(TAB[1] + String.format("setColor_%1$s(my, mac, newColor, callback) {", type));
        function.add(TAB[2] + "existed = 1;");
        function.add(TAB[2] + "switch (mac) {");

        for (Droid droid : droids) {
            if (droid.type.equals(type)) {
                function.add(TAB[3] + String.format("case \"%1$s\":", droid.mac));
                function.add(TAB[4] + String.format("my.setColor_%1$s(newColor);", droid.name));
                function.add(TAB[4] + "break;");
            }
        }

        function.add(TAB[3] + "default:");
        function.add(TAB[4] + "existed=0;");
        function.add(TAB[2] + "}");
        function.add("");

        function.add(TAB[2] + "if (existed) {");
        function.add(TAB[3] + "callback();");
        function.add(TAB[2] + "} else {");
        function.add(TAB[3] + String.format("console.log(\"We couldn't find %1$s with mac : \" + mac);", type));
        function.add(TAB[2] + "}");
        function.add(TAB[1] + "}");

        return function;
    }

    private static ArrayList<String> generateSetColor(ArrayList<Droid> droids) {
        ArrayList<String> functions = new ArrayList<>();
        functions.add("// set new color bb8");

        for (Droid droid : droids) {
            functions.add(TAB[1] + String.format("setColor_%1$s: function(newColor) {", droid.name));
            functions.add(TAB[2] + String.format("this.devices.%1$s.color(newColor, function() {", droid.name));
            functions.add(TAB[3] + String.format("console.log(\"%1$s set new color: \" + newColor);", droid.name));
            functions.add(TAB[2] + "});");
            functions.add(TAB[1] + "},");
        }

        functions.add(ENDL);
        functions.addAll(generateSetColor("bb8", droids));
        functions.add(",");
        functions.add(ENDL);

        functions.addAll(generateSetColor("ollie", droids));
        functions.add(ENDL);

        return functions;
    }

    private static String generateGetIfForHttpServer(String type, String smth, String printToWeb) {
        return String.format("\n"
                + TAB[4] + "if (urlParsed.pathname == \"/%1$s/get%2$s\" && urlParsed.query.MAC) {\n"
                + TAB[5] + "actionPerformed = 1;\n"
                + TAB[5] + "mac = urlParsed.query.MAC;\n"
                + TAB[5] + "console.log(\"%1$s: \" + mac + \" get %4$s \");\n"
                + TAB[5] + "\n"
                + TAB[5] + "my.get%2$s_%1$s(my, mac, 5, function(data) {\n"
                + TAB[6] + "console.log(\"%4$s: \" + JSON.stringify(data) + \"\\n\");\n"
                + TAB[6] + "res.end(%3$s);\n"
                + TAB[5] + "});	\n"
                + TAB[4] + "}\n",
                 type, smth, printToWeb, smth.toLowerCase());
    }

    private static String generateForHttpServer(String type) {
        return String.format(""
                + TAB[3] + "if (urlParsed.pathname.startsWith(\"/%1$s/\")) {\n"
                + "\n"
                + TAB[4] + "if (urlParsed.pathname == \"/%1$s/isConnected\" && urlParsed.query.MAC) {\n"
                + TAB[5] + "actionPerformed = 1;\n"
                + TAB[5] + "mac = urlParsed.query.MAC;\n"
                + TAB[5] + "console.log(\"isConnected to %1$s, mac : \" + mac);\n"
                + TAB[5] + "if (my.isConnected(my, mac)) {\n"
                + TAB[6] + "res.end(\"connected\");\n"
                + TAB[5] + "} else {\n"
                + TAB[6] + "res.end(\"not\");\n"
                + TAB[5] + "}\n"
                + TAB[4] + "}"
                + "\n"
                + TAB[4] + "if (urlParsed.pathname == \"/%1$s/connect\" && urlParsed.query.MAC) {\n"
                + TAB[5] + "actionPerformed = 1;\n"
                + TAB[5] + "mac = urlParsed.query.MAC;\n"
                + TAB[5] + "console.log(\"%1$s to connect: \" + mac);\n"
                + TAB[5] + "my.connect_%1$s(my, mac, function() {\n"
                + TAB[6] + "console.log(\"connected to %1$s, mac : \" + mac);\n"
                + TAB[6] + "res.end(\"connected\");\n"
                + TAB[5] + "});	\n"
                + TAB[4] + "}\n"
                + "\n"
                + TAB[4] + "if (urlParsed.pathname == \"/%1$s/roll\" && urlParsed.query.MAC\n"
                + TAB[6] + "&& urlParsed.query.speed && urlParsed.query.direction) {\n"
                + TAB[5] + "mac = urlParsed.query.MAC;\n"
                + TAB[5] + "newSpeed = parseInt(urlParsed.query.speed);\n"
                + TAB[5] + "newDirection = parseInt(urlParsed.query.direction);\n"
                + TAB[5] + "console.log(\"%1$s: \" + mac + \" roll with speed: \" + newSpeed + \" & direction: \" + newDirection);\n"
                + TAB[5] + "my.roll_%1$s(my, mac, newSpeed, newDirection, function() {\n"
                + TAB[6] + "console.log(\"rolled %1$s, mac : \" + mac);\n"
                + TAB[6] + "res.end(\"rolled\");\n"
                + TAB[6] + "actionPerformed = 1;\n"
                + TAB[5] + "});	\n"
                + TAB[4] + "}\n"
                + "\n"
                + TAB[4] + "if (urlParsed.pathname == \"/%1$s/setColor\" && urlParsed.query.MAC\n"
                + TAB[6] + "&& urlParsed.query.color) {\n"
                + TAB[5] + "mac = urlParsed.query.MAC;\n"
                + TAB[5] + "newColor = parseInt(urlParsed.query.color);\n"
                + TAB[5] + "console.log(\"%1$s: \" + mac + \" set new color: \" + newColor);\n"
                + TAB[5] + "\n"
                + TAB[5] + "my.setColor_%1$s(my, mac, newColor, function() {\n"
                + TAB[6] + "console.log(\"set color of %1$s, mac : \" + mac);\n"
                + TAB[6] + "res.end(\"set color\");\n"
                + TAB[6] + "actionPerformed = 1;\n"
                + TAB[5] + "});	\n"
                + TAB[4] + "}\n"
                + generateGetIfForHttpServer(type, "Velocity", "\"xVelocity: \" + data.xVelocity.value[0] + \"\\n\" + \"yVelocity: \" + data.yVelocity.value[0] + \"\\n\"")
                + generateGetIfForHttpServer(type, "AccelOne", "\"accelOne: \" + data.accelOne.value[0] + \"\\n\"")
                + generateGetIfForHttpServer(type, "ImuAngles", "\"pitchAngle: \" + data.pitchAngle.value[0] + \"\\n\""
                        + "+ \"rollAngle: \" + data.rollAngle.value[0] + \"\\n\""
                        + "+ \"yawAngle: \" + data.yawAngle.value[0] + \"\\n\"")
                + generateGetIfForHttpServer(type, "Accelerometer", "\"xAccel: \" + data.xAccel.value[0] + \"\\n\""
                        + "+ \"yAccel: \" + data.yAccel.value[0] + \"\\n\""
                        + "+ \"zAccel: \" + data.zAccel.value[0] + \"\\n\"")
                + generateGetIfForHttpServer(type, "Gyroscope", "\"xGyro: \" + data.xGyro.value[0] + \"\\n\""
                        + "+ \"yGyro: \" + data.yGyro.value[0] + \"\\n\""
                        + "+ \"zGyro: \" + data.zGyro.value[0] + \"\\n\"")
                + TAB[3] + "}", type);
    }

    private static String generateIsConnected() {
        return TAB[1] + "isConnected(my, mac) {\n"
                + TAB[2] + "return ollies[mac] == true;\n"
                + TAB[1] + "}";
    }

    private static ArrayList<String> generateWork() {
        ArrayList<String> function = new ArrayList<>();
        function.add(TAB[1] + "work: function(my) {");
        function.add("");
        function.add(TAB[2] + "var http = require('http');");
        function.add(TAB[2] + "var url = require('url');");
        function.add("");
        function.add(TAB[2] + "var server = new http.Server(function(req, res) {");
        function.add(TAB[3] + "var urlParsed = url.parse(req.url, true);");
        function.add(TAB[3] + "console.log(\"Received url: \" + urlParsed.pathname + \" q: \" + urlParsed.query);");
        function.add(TAB[3] + "actionPerformed = 0;");
        function.add("");

        function.add(generateForHttpServer("bb8"));
        function.add("");
        function.add(generateForHttpServer("ollie"));
        function.add("");

        function.add(TAB[3] + "if (actionPerformed==0) {");
        function.add(TAB[4] + "res.statusCode = 404;");
        function.add(TAB[4] + "res.end(\"Not found!\");");
        function.add(TAB[3] + "}");
        function.add(TAB[2] + "});");

        function.add(TAB[2] + "server.listen(1337, \"127.0.0.1\");");
        function.add(TAB[1] + "}");
        return function;
    }

    private static String generateEnd() {
        return "}).start();";
    }

    private static ArrayList<String> generate(ArrayList<Droid> droids) {
        ArrayList<String> result = new ArrayList<>();

        result.add(generateHeader());
        result.add(generateBluetoothConnections(droids));
        result.add(",");
        result.add(generateDiveces(droids));
        result.add(",");
        result.addAll(generateConnections(droids));
        result.add(",");
        result.addAll(generateRoll(droids));
        result.add(",");
        result.addAll(generateSetColor(droids));
        result.add(",");
        result.addAll(generateGetSmth(droids, "Velocity"));
        result.add(",");
        result.addAll(generateGetSmth(droids, "AccelOne"));
        result.add(",");
        result.addAll(generateGetSmth(droids, "ImuAngles"));
        result.add(",");
        result.addAll(generateGetSmth(droids, "Accelerometer"));
        result.add(",");
        result.addAll(generateGetSmth(droids, "Gyroscope"));
        result.add(",");
        result.add(generateIsConnected());
        result.add(",");
        result.addAll(generateWork());

        result.add(generateEnd());

        return result;
    }

    private static ArrayList<String> generateGetSmth(String type, ArrayList<Droid> droids, String smth) {
        ArrayList<String> connection = new ArrayList<>();
        String s;

        s = TAB[1] + String.format("get%2$s_%1$s(my, mac, sps, callback) {", type, smth);
        connection.add(s);

        connection.add(TAB[2] + "existed = true;");
        connection.add(TAB[2] + "switch (mac) {");

        for (Droid droid : droids) {
            if (droid.type.equals(type)) {
                connection.add(String.format(TAB[3] + "case \"%1$s\":", droid.mac));
                connection.add(String.format(TAB[4] + "my.get%2$s_%1$s(sps, callback);", droid.name, smth));
                connection.add(TAB[4] + "break;");
            }
        }

        connection.add(TAB[3] + "default:");
        connection.add(TAB[4] + "existed = false;");
        connection.add(TAB[2] + "}");

        connection.add(TAB[2] + "if (!existed) {");
        connection.add(TAB[3] + String.format("console.log(\"We couldn't find %1$s with mac : \" + mac);", type));
        connection.add(TAB[2] + "}");
        connection.add(TAB[1] + "}");
        return connection;
    }

    private static ArrayList<String> generateGetSmth(ArrayList<Droid> droids, String smth) {
        ArrayList<String> functions = new ArrayList<>();
        functions.add("// get " + smth + " of bb8 & ollie");

        for (Droid droid : droids) {
            functions.add(generateGetSmth(droid, smth));
            functions.add("," + ENDL);
        }

        functions.add(ENDL);

        functions.addAll(generateGetSmth("bb8", droids, smth));
        functions.add("," + ENDL);

        functions.addAll(generateGetSmth("ollie", droids, smth));
        functions.add(ENDL);

        return functions;
    }

    private static String generateGetSmth(Droid droid, String smth) {
        StringBuilder connection = new StringBuilder();
        String s;

        s = TAB[1] + String.format("get%1$s_%2$s: function(sps, callback) {", smth, droid.name);
        connection.append(s).append(ENDL);

        s = TAB[2] + String.format("this.devices.%1$s.stream%2$s(sps, false);", droid.name, smth);
        connection.append(s).append(ENDL);

        s = TAB[2] + String.format("this.devices.%1$s.once(\"%2$s\", callback);", droid.name, smth);
        connection.append(s).append(ENDL);

        connection.append(TAB[1] + "}").append(ENDL);

        return connection.toString();
    }

}
